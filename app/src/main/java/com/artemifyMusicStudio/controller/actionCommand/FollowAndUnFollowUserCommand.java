package com.artemifyMusicStudio.controller.actionCommand;

import android.annotation.SuppressLint;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.controller.accountServiceCommand.AccountServiceCommand;
import com.presenters.LanguagePresenter;
import com.useCase.UserAccess;


/**
 * A FollowUserCommand class to handle the follow user request from a user
 *
 */
public class FollowAndUnFollowUserCommand implements CompoundButton.OnCheckedChangeListener {

    private final ActivityServiceCache activityServiceCache;
    private final UserAccess acctServiceManager;
    private final LanguagePresenter languagePresenter;
    private final String userID;
    private final String targetUserID;
    private final Switch followUnfollowSwitch;

    /** Constructor of FollowUserCommand
     *
     *
     */
    public FollowAndUnFollowUserCommand(ActivityServiceCache activityServiceCache,
                                        Switch followUnfollowSwitch){
        this.activityServiceCache = activityServiceCache;
        this.acctServiceManager = activityServiceCache.getUserAcctServiceManager();
        this.languagePresenter = activityServiceCache.getLanguagePresenter();
        this.userID = activityServiceCache.getUserID();
        this.targetUserID = activityServiceCache.getTargetUserID();
        this.followUnfollowSwitch = followUnfollowSwitch;
    }


    /**
     * Execute the FollowAndUnfollow User command
     * @param compoundButton a compoundButton object
     * @param switchIsChecked a boolen to indicate whether the swicht is checked or not
     */
    @SuppressLint("SetTextI18n")
    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean switchIsChecked) {
        PageActivity currentPageActivity = this.activityServiceCache.getCurrentPageActivity();
        if(switchIsChecked){
            this.acctServiceManager.addFollower(this.targetUserID, this.userID);
            this.followUnfollowSwitch.setText(this.languagePresenter.translateString("Follow"));
            String msg = this.languagePresenter.translateString(
                    "Successfully followed " + this.targetUserID + "! (ꈍᴗꈍ)ε｀●)");
            Toast.makeText(currentPageActivity, msg, Toast.LENGTH_LONG).show();
        }
        else{
            this.acctServiceManager.removeFollower(this.targetUserID,this.userID);
            this.followUnfollowSwitch.setText(this.languagePresenter.translateString("UnFollow"));
            String msg = "Successfully unfollowed " + this.targetUserID + ". (っ˘̩╭╮˘̩)っ";
            Toast.makeText(currentPageActivity, msg, Toast.LENGTH_LONG).show();
        }
    }
}
