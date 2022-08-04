package com.artemifyMusicStudio.controller.transitionCommand;

import android.content.Intent;
import android.view.View;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.UploadSongPage;

/**
 * To handle the request that a user choose to invoke the UploadSongPage to upload song in RegularUserHomePage
 */
public class InvokeUploadSongPageCommand implements View.OnClickListener {

    private final ActivityServiceCache activityServiceCache;

    public InvokeUploadSongPageCommand(ActivityServiceCache activityServiceCache){
        this.activityServiceCache = activityServiceCache;
    }

    @Override
    public void onClick(View v) {
        PageActivity currentPageActivity = activityServiceCache.getCurrentPageActivity();
        Intent it = new Intent(currentPageActivity, UploadSongPage.class);
        it.putExtra("cache", this.activityServiceCache);
        currentPageActivity.startActivity(it);
    }
}
