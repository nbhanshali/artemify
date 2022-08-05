package com.artemifyMusicStudio.controller.transitionCommand;

import android.content.Intent;
import android.view.View;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.UserDisplayPage;

/**
 * A command to invoke UserDisplayPage
 */
public class InvokeUserDisplayPage implements View.OnClickListener{
    private final ActivityServiceCache activityServiceCache;
    private final String targetUserID;

    /**
     * Constructor of InvokeUserDisplayPage
     * @param activityServiceCache a ActivityServiceCache object
     */
    public InvokeUserDisplayPage(ActivityServiceCache activityServiceCache,
                                 String targetUserID){
        this.activityServiceCache = activityServiceCache;
        this.targetUserID = targetUserID;
    }

    /**
     * A method to execute the command when user click the button
     * @param view a View object
     */
    @Override
    public void onClick(View view) {
        this.activityServiceCache.setTargetUserID(targetUserID);
        PageActivity currentPageActivity = activityServiceCache.getCurrentPageActivity();
        Intent it = new Intent(currentPageActivity, UserDisplayPage.class);
        it.putExtra("cache", this.activityServiceCache);
        currentPageActivity.startActivity(it);
    }
}
