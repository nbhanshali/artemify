package com.artemifyMusicStudio.controller.queueServiceCommand;

import com.artemifyMusicStudio.ActivityServiceCache;
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
    public void execute() {
        if (activityServiceCache.getQueueManager().getRecentlyPlayedSongs().size() == 0) {
            languagePresenter.
                    display("You have not played any songs previously. \n");
        } else {
            ArrayList<Integer> recentlyPlayedSongs = activityServiceCache.getQueueManager().
                    getRecentlyPlayedSongs();
            int prevSong = recentlyPlayedSongs.get(0);
            recentlyPlayedSongs.remove(0);
            int currSong = activityServiceCache.getQueueManager().getNowPlaying();
            activityServiceCache.getQueueManager().addToQueue(currSong, 0);
            activityServiceCache.getQueueManager().setNowPlaying(prevSong);
            activityServiceCache.getQueueManager().setRecentlyPlayedSongs(recentlyPlayedSongs);
            languagePresenter.
                    display("Previous song is being played. \n");
        }
    }
}
