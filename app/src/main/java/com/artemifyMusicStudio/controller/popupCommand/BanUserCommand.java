package com.artemifyMusicStudio.controller.popupCommand;
import com.artemifyMusicStudio.ActivityServiceCache;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.artemifyMusicStudio.PageActivity;
import com.presenters.LanguagePresenter;

import java.util.Scanner;

/**
 * A BanUserCommand class to handle the ban user request from an admin user
 *
 */

public class BanUserCommand implements View.OnClickListener {
    private final LanguagePresenter languagePresenter;
    protected final ActivityServiceCache activityServiceCache;
    protected EditText InputTargetName;


    /**
     * Constructor of BanUserCommand
     * @param activityServiceCache a PageCreator object
     * @param userInputTargetName a EditText to capture the user input for the username of a
     *                            to-be banned user.
     */
    public BanUserCommand(ActivityServiceCache activityServiceCache,
                          EditText userInputTargetName){
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = activityServiceCache.getLanguagePresenter();
        this.InputTargetName = userInputTargetName;
    }

    /**
     * Execute the ban user command
     */
    @Override
    public void onClick(View view) {

//        this.languagePresenter.display("Enter the username of the user you wish to ban: ");
        String username = InputTargetName.getText().toString();
        if (this.activityServiceCache.getUserAcctServiceManager().exists(username)) {
            if (this.activityServiceCache.
                    getUserAcctServiceManager().findUser(username).getIsAdmin()) {
                String msg = this.languagePresenter.
                        translateString("Invalid action. You cannot ban admins.");
                displayToastMsg(msg);
            } else if (this.activityServiceCache.getUserAcctServiceManager().ban(username)) {
                String msg = this.languagePresenter. translateString("Successfully banned");
                displayToastMsg(msg);
            }
        }
        else{
            String msg = this.languagePresenter.translateString("User does not exist");
            displayToastMsg(msg);
        }
    }

    public void displayToastMsg(String msg) {
        PageActivity currentPageActivity = activityServiceCache.getCurrentPageActivity();
        Toast.makeText(currentPageActivity, msg, Toast.LENGTH_LONG).show();
    }
}
