package com.artemifyMusicStudio.controller.queueServiceCommand;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.PlaylistSongsDisplayPage;
import com.artemifyMusicStudio.QueueDisplayPage;
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
        Intent it = new Intent(currentPageActivity, PlaylistSongsDisplayPage.class);
        it.putExtra("cache", this.activityServiceCache);
        currentPageActivity.startActivity(it);
        String displayMsg =  this.languagePresenter.
                translateString("Here are all the songs in the queue.") ;
        Toast.makeText(currentPageActivity, displayMsg, Toast.LENGTH_LONG).show();
        ArrayList<Integer> allSongsID = this.activityServiceCache.getQueueManager().getUpcomingSongs();
        for (Integer songID: allSongsID){
            String songNameMsg =  this.languagePresenter.translateString(this.activityServiceCache.getSongManager().findSong(songID).getName());
            Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
                    songNameMsg, Toast.LENGTH_LONG).show();
        }
    }
}

