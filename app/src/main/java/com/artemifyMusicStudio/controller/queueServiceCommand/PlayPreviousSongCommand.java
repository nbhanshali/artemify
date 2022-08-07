package com.artemifyMusicStudio.controller.queueServiceCommand;

import android.view.View;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.presenters.LanguagePresenter;
import com.useCase.Queue;

import java.util.ArrayList;

public class PlayPreviousSongCommand extends QueueServiceCommand{

    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;

    public PlayPreviousSongCommand(ActivityServiceCache activityServiceCache,
                           LanguagePresenter languagePresenter, Queue queueService) {
        super(queueService);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
    }

    @Override
    public void onClick (View view) {
        PageActivity currentPageActivity = activityServiceCache.getCurrentPageActivity();
        if (activityServiceCache.getQueueManager().getRecentlyPlayedSongs().size() == 0) {
            String warningMsg =  this.languagePresenter.
                    translateString("No song has been played previously.") ;
            Toast.makeText(currentPageActivity, warningMsg, Toast.LENGTH_LONG).show();
        } else {
            ArrayList<Integer> recentlyPlayedSongs = activityServiceCache.getQueueManager().
                    getRecentlyPlayedSongs();
            int prevSong = recentlyPlayedSongs.get(0);
            recentlyPlayedSongs.remove(0);
            int currSong = activityServiceCache.getQueueManager().getNowPlaying();
            activityServiceCache.getQueueManager().addToQueue(currSong, 0);
            activityServiceCache.getQueueManager().setNowPlaying(prevSong);
            activityServiceCache.getQueueManager().setRecentlyPlayedSongs(recentlyPlayedSongs);
            String warningMsg =  this.languagePresenter.
                    translateString("Previous is song is being played.") ;
            Toast.makeText(currentPageActivity, warningMsg, Toast.LENGTH_LONG).show();
        }
    }

}


