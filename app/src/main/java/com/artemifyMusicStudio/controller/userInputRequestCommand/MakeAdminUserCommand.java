package com.artemifyMusicStudio.controller.userInputRequestCommand;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.presenters.LanguagePresenter;

/**
 * A MakeAdminUserCommand class to grant another user the admin right
 *
 */

public class MakeAdminUserCommand implements View.OnClickListener {
    private final LanguagePresenter languagePresenter;
    protected final ActivityServiceCache activityServiceCache;
    protected EditText InputTargetName;

    /**
     * Constructor of DeleteUserCommand class
     * @param activityServiceCache a PageCreator object
     * @param userInputTargetName a EditText to capture the user input for the username of a
     *                            to-be admin user.
     */
    public MakeAdminUserCommand(ActivityServiceCache activityServiceCache,
                             EditText userInputTargetName){
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = activityServiceCache.getLanguagePresenter();
        this.InputTargetName = userInputTargetName;
    }

    /**
     * Execute the request of granting a user the admin right
     */
    @Override
    public void onClick(View view) {
        String username = InputTargetName.getText().toString();
        if(this.activityServiceCache.getUserAcctServiceManager().makeAdmin(username)){
            String msg = this.languagePresenter.translateString("Admin rights granted");
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
