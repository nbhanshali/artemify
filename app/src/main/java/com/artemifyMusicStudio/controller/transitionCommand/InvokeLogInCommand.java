package com.artemifyMusicStudio.controller.transitionCommand;

import android.content.Intent;
import android.view.View;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.LoginPage;
import com.artemifyMusicStudio.PageActivity;

public class InvokeLogInCommand implements View.OnClickListener{
    private final ActivityServiceCache activityServiceCache;

    public InvokeLogInCommand(ActivityServiceCache activityServiceCache){
        this.activityServiceCache = activityServiceCache;
    }

    @Override
    public void onClick(View v) {
        PageActivity currentPageActivity = activityServiceCache.getCurrentPageActivity();
        Intent it = new Intent(currentPageActivity, LoginPage.class);
        it.putExtra("cache", this.activityServiceCache);
        currentPageActivity.startActivity(it);
    }
}
