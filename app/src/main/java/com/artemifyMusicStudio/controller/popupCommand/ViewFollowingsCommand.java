package com.artemifyMusicStudio.controller.popupCommand;

import com.presenters.LanguagePresenter;
import com.useCase.UserAccess;

import java.util.ArrayList;

/**
 * A ViewFollowingsCommand class to handle the view followings request from a user
 *
 */
public class ViewFollowingsCommand extends UserInteractionCommand {

    private final LanguagePresenter languagePresenter;
    private final String userID;
    private final String viewedID;

    /** Constructor of ViewFollowingsCommand
     *
     * @param acctServiceManager a UserAccess object
     * @param languagePresenter a LanguagePresenter object
     * @param userID id of user performing the command
     * @param viewedID id of user the command is being performed on
     */
    public ViewFollowingsCommand(UserAccess acctServiceManager, LanguagePresenter languagePresenter, String userID,
                                 String viewedID){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
        this.userID = userID;
        this.viewedID = viewedID;
    }

    /**
     * Execute the view followings user command
     */
    @Override
    public void execute() {
        ArrayList<String> followings = this.acctServiceManager.getFollowing(this.viewedID);
        if(this.userID.equals(this.viewedID)){
            if (followings.size() == 0) {
                this.languagePresenter.display("You are not following anyone.");
            } else {
                this.languagePresenter.display("The following are your followings:");
                for (String following : followings) {
                    this.languagePresenter.display(following);
                }
            }
        }
        else {
            if (followings.size() == 0) {
                this.languagePresenter.display(this.viewedID + " is not following anyone.");
            } else {
                this.languagePresenter.display("The following are " + this.viewedID + "'s followings:");
                for (String following : followings) {
                    this.languagePresenter.display(following);
                }
            }
        }
        languagePresenter.display("\n");
    }
}
