package com.artemifyMusicStudio.controller.queueServiceCommand;

import com.artemifyMusicStudio.ActivityServiceCache;
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
    public void execute() {
        int nowPlaying = activityServiceCache.getQueueManager().getNowPlaying();

        if(activityServiceCache.getSongManager().exists(nowPlaying)) {
            activityServiceCache.getQueueManager().popFromQueue();
            languagePresenter.
                    display("Current song has been skipped, next song is being played. \n");
        } else {
            languagePresenter.display("No song is currently being played. \n");
        }
    }
}
