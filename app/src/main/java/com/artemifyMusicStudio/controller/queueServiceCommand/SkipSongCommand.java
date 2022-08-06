package com.artemifyMusicStudio.controller.queueServiceCommand;

import android.view.View;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.presenters.LanguagePresenter;
import com.useCase.Queue;

public class SkipSongCommand extends QueueServiceCommand{

    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;


    public SkipSongCommand(ActivityServiceCache activityServiceCache,
                           LanguagePresenter languagePresenter, Queue queueService) {
        super(queueService);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
    }

    @Override
    public void onClick(View view){
        PageActivity currentPageActivity = activityServiceCache.getCurrentPageActivity();
        int nowPlaying = activityServiceCache.getQueueManager().getNowPlaying();

        if(activityServiceCache.getSongManager().exists(nowPlaying)) {
            activityServiceCache.getQueueManager().popFromQueue();
            String warningMsg =  this.languagePresenter.
                    translateString("Current song has been skipped, " +
                            "next song is being played.") ;
            Toast.makeText(currentPageActivity, warningMsg, Toast.LENGTH_LONG).show();
        } else {
            String warningMsg =  this.languagePresenter.
                    translateString("No song is currently being played.") ;
            Toast.makeText(currentPageActivity, warningMsg, Toast.LENGTH_LONG).show();
        }
    }

}
