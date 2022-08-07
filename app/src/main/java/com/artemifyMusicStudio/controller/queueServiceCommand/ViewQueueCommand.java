package com.artemifyMusicStudio.controller.queueServiceCommand;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.PlaylistSongsDisplayPage;
import com.artemifyMusicStudio.QueueDisplayPage;
import com.artemifyMusicStudio.ViewQueuePage;
import com.presenters.LanguagePresenter;
import com.useCase.Queue;

import java.util.ArrayList;

public class ViewQueueCommand extends QueueServiceCommand{

    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;


    public ViewQueueCommand(ActivityServiceCache activityServiceCache,
                           LanguagePresenter languagePresenter, Queue queueService) {
        super(queueService);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
    }

    @Override
    public void onClick (View view) {
        PageActivity currentPageActivity = activityServiceCache.getCurrentPageActivity();
        Intent it = new Intent(currentPageActivity, ViewQueuePage.class);
        it.putExtra("cache", this.activityServiceCache);
        currentPageActivity.startActivity(it);


    }
}

