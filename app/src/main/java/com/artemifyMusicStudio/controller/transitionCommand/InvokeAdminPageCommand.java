package com.artemifyMusicStudio.controller.transitionCommand;

import android.content.Intent;
import android.view.View;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.AdminPage;
import com.artemifyMusicStudio.PageActivity;

public class InvokeAdminPageCommand implements View.OnClickListener{
    private final ActivityServiceCache activityServiceCache;

    public InvokeAdminPageCommand(ActivityServiceCache activityServiceCache){
        this.activityServiceCache = activityServiceCache;
    }

    @Override
    public void onClick(View v) {
        PageActivity currentPageActivity = activityServiceCache.getCurrentPageActivity();
        Intent it = new Intent(currentPageActivity, AdminPage.class);
        it.putExtra("cache", this.activityServiceCache);
        currentPageActivity.startActivity(it);
    }
}
