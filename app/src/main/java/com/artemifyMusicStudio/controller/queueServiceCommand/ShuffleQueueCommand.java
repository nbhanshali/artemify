package com.artemifyMusicStudio.controller.queueServiceCommand;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;
import com.useCase.Queue;

public class ShuffleQueueCommand extends QueueServiceCommand{

    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;


    public ShuffleQueueCommand(ActivityServiceCache activityServiceCache,
                                  LanguagePresenter languagePresenter, Queue queueService) {
        super(queueService);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
    }

    @Override
    public void execute() {
        if (activityServiceCache.getQueueManager().getUpcomingSongs().size() == 0) {
            languagePresenter.
                    display("You have no upcoming songs in the queue to shuffle. \n");
        } else {
            activityServiceCache.getQueueManager().shuffleQueue();
            languagePresenter.
                    display("Your upcoming songs in the queue have been shuffled. \n");
        }
    }
}
