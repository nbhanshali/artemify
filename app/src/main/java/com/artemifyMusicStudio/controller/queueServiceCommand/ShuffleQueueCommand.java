package com.artemifyMusicStudio.controller.queueServiceCommand;

import android.view.View;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
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
    public void onClick(View view) {
        PageActivity currentPageActivity = activityServiceCache.getCurrentPageActivity();
        if (activityServiceCache.getQueueManager().getUpcomingSongs().size() == 0) {
            String warningMsg =  this.languagePresenter.
                    translateString("You have no upcoming songs in the queue to shuffle.") ;
            Toast.makeText(currentPageActivity, warningMsg, Toast.LENGTH_LONG).show();
        } else {
            activityServiceCache.getQueueManager().shuffleQueue();
            String warningMsg =  this.languagePresenter.
                    translateString("Your upcoming songs in the queue have been shuffled.") ;
            Toast.makeText(currentPageActivity, warningMsg, Toast.LENGTH_LONG).show();
        }
    }

}
