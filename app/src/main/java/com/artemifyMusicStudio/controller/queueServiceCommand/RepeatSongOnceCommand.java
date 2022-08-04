package com.artemifyMusicStudio.controller.queueServiceCommand;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;
import com.useCase.Queue;

public class RepeatSongOnceCommand extends  QueueServiceCommand{

    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;


    public RepeatSongOnceCommand(ActivityServiceCache activityServiceCache,
                                  LanguagePresenter languagePresenter, Queue queueService) {
        super(queueService);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
    }

    @Override
    public void execute() {
        int nowPlaying = activityServiceCache.getQueueManager().getNowPlaying();

        if(activityServiceCache.getSongManager().exists(nowPlaying)) {
            activityServiceCache.getQueueManager().addToQueue(nowPlaying, 0);
            languagePresenter.
                    display("Current song will be repeated once. \n");
        } else {
            languagePresenter.display("No song is currently being played. \n");
        }

    }
}
