package com.artemifyMusicStudio.controller.actionCommand;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.QueueDisplayPage;
import com.presenters.LanguagePresenter;

public class AddToQueueCommand implements View.OnClickListener{

    private final ActivityServiceCache activityServiceCache;

    public AddToQueueCommand(ActivityServiceCache activityServiceCache){
        this.activityServiceCache = activityServiceCache;
    }

    @Override
    public void onClick(View v) {
        PageActivity currentPageActivity = activityServiceCache.getCurrentPageActivity();
        int songId = Integer.parseInt(activityServiceCache.getTargetSongID());
        activityServiceCache.getQueueManager().addToQueueEnd(songId);
        displaySuccessfulMsg(currentPageActivity);
        Intent it = new Intent(currentPageActivity, QueueDisplayPage.class);
        it.putExtra("cache", this.activityServiceCache);
        currentPageActivity.startActivity(it);
    }

    private void displaySuccessfulMsg(PageActivity currentPageActivity) {
        String Msg =  activityServiceCache.getLanguagePresenter().translateString("You add the song to queue!");
        Toast.makeText(currentPageActivity, Msg, Toast.LENGTH_LONG).show();
    }
}
