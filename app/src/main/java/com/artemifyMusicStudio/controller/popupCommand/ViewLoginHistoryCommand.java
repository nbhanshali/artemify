package com.artemifyMusicStudio.controller.popupCommand;

import com.artemifyMusicStudio.controller.accountServiceCommand.AccountServiceCommand;
import com.presenters.LanguagePresenter;
import com.useCase.UserAccess;

import java.util.ArrayList;

/**
 * A ViewLoginHistoryCommand class to handle the view login history request from a user
 *
 */
public class ViewLoginHistoryCommand extends AccountServiceCommand {

    private final LanguagePresenter languagePresenter;
    private final String userID;

    /** Constructor of ViewLoginHistoryCommand
     *
     * @param languagePresenter A LanguagePresenter object
     * @param acctServiceManager A UserAccess UserCase object
     * @param userID the id of the user for whom the login history is being diplayed
     */
    public ViewLoginHistoryCommand(LanguagePresenter languagePresenter, UserAccess acctServiceManager, String userID){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
        this.userID = userID;
    }

    /**
     * Execute the ViewLoginHistoryCommand to query the user's previous login history
     */
    @Override
    public void execute() {
        this.languagePresenter.display("Hey " + userID + ", you were logged in previously at: ");
        ArrayList<String> logInHistory = this.acctServiceManager.getPreviousLogin(this.userID);
        for (String history: logInHistory){
            this.languagePresenter.display(history);
            //System.out.println("\n");
        } languagePresenter.display("\n");
    }
}
