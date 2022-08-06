package com.artemifyMusicStudio.controller.popupCommand;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.presenters.LanguagePresenter;

import java.util.Scanner;

/**
 * A UnBanUserCommand class to handle the ban user request from an admin user
 *
 */

public class UnBanUserCommand implements View.OnClickListener {
    private final LanguagePresenter languagePresenter;
    protected final ActivityServiceCache activityServiceCache;
    protected EditText InputTargetName;

    /**
     * Constructor of DeleteUserCommand class
     * @param activityServiceCache a PageCreator object
     * @param userInputTargetName a EditText to capture the user input for the username of a
     *                            to-be unbanned user.
     */
    public UnBanUserCommand(ActivityServiceCache activityServiceCache,
                             EditText userInputTargetName){
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = activityServiceCache.getLanguagePresenter();
        this.InputTargetName = userInputTargetName;
    }

    /**
     * Execute to unban user request from an admin user
     */
    @Override
    public void onClick(View view) {
//        Scanner in = new Scanner(System.in);

//        this.languagePresenter.display("Enter the username of the user you wish to unban: ");
        String username = InputTargetName.getText().toString();
        if(this.activityServiceCache.getUserAcctServiceManager().unban(username)){
            String msg = this.languagePresenter.translateString("Successfully unbanned");
            displayToastMsg(msg);
        }else{
            String msg = this.languagePresenter.translateString("User does not exist");
            displayToastMsg(msg);
        }
    }

    public void displayToastMsg(String msg) {
        PageActivity currentPageActivity = activityServiceCache.getCurrentPageActivity();
        Toast.makeText(currentPageActivity, msg, Toast.LENGTH_LONG).show();
    }
}
