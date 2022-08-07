package com.artemifyMusicStudio.controller.popupCommand;

import android.view.View;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;
import com.useCase.UserAccess;

import java.util.ArrayList;

/**
 * A ViewFollowersCommand class to handle the view followers request from a user
 *
 */
public class ViewFollowersCommand implements View.OnClickListener {

    private final ActivityServiceCache activityServiceCache;
    private final UserAccess acctServiceManager;
    private final LanguagePresenter languagePresenter;
    private final String userID;
    private final String viewedID;

    /** Constructor of ViewFollowersCommand
     *
     * @param activityServiceCache a PageCreator Object
     * @param languagePresenter a LanguagePresenter object
     * @param userID id of user performing the command
     * @param viewedID id of user the command is being performed on
     */
    public ViewFollowersCommand(ActivityServiceCache activityServiceCache, LanguagePresenter languagePresenter, String userID,
                                String viewedID){
        this.activityServiceCache = activityServiceCache;
        this.acctServiceManager = activityServiceCache.getUserAcctServiceManager();
        this.languagePresenter = languagePresenter;
        this.userID = userID;
        this.viewedID = viewedID;
    }

    /**
     * Execute the view followers user command
     */
    @Override
    public void onClick(View view) {
        String finalMessage = "";
        ArrayList<String> followers = this.acctServiceManager.getFollowers(this.viewedID);
        if(this.userID.equals(this.viewedID)){
            if (followers.size() == 0) {
                String noFollowerMessage = this.languagePresenter.translateString("You have no followers :(");
                finalMessage = finalMessage + noFollowerMessage + "\n";
//                Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
//                        noFollowerMessage, Toast.LENGTH_LONG).show();
            } else {
                String followerMessage = this.languagePresenter.translateString("The following are your followers:");
                finalMessage = finalMessage + followerMessage + "\n";
//                Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
//                        followerMessage, Toast.LENGTH_LONG).show();
                for (String follower : followers) {
                    follower = this.languagePresenter.translateString(follower);
                    finalMessage = finalMessage + follower + "\n";
//                    Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
//                            follower, Toast.LENGTH_LONG).show();
                }
            }
        }
        else {
            if (followers.size() == 0) {
                String noFollowerMessage = this.languagePresenter.translateString(this.viewedID + " has no followers.");
                finalMessage = finalMessage + noFollowerMessage + "\n";
//                Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
//                        noFollowerMessage, Toast.LENGTH_LONG).show();
            } else {
                String followerMessage = this.languagePresenter.translateString("The following are " + this.viewedID + "'s followers:");
                finalMessage = finalMessage + followerMessage + "\n";
//                Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
//                        followerMessage, Toast.LENGTH_LONG).show();
                for (String follower : followers) {
                    follower = this.languagePresenter.translateString(follower);
                    finalMessage = finalMessage + follower + "\n";
//                    Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
//                            follower, Toast.LENGTH_LONG).show();
                }
            }
        }
//        languagePresenter.display("\n");
        Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
                finalMessage, Toast.LENGTH_LONG).show();
    }

}
