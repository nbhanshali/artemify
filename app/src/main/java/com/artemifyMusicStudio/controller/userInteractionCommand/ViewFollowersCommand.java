package com.artemifyMusicStudio.controller.userInteractionCommand;

import com.presenters.LanguagePresenter;
import com.useCase.UserAccess;

import java.util.ArrayList;

/**
 * A ViewFollowersCommand class to handle the view followers request from a user
 *
 */
public class ViewFollowersCommand extends UserInteractionCommand{

    private final LanguagePresenter languagePresenter;
    private final String userID;
    private final String viewedID;

    /** Constructor of ViewFollowersCommand
     *
     * @param acctServiceManager a UserAccess object
     * @param languagePresenter a LanguagePresenter object
     * @param userID id of user performing the command
     * @param viewedID id of user the command is being performed on
     */
    public ViewFollowersCommand(UserAccess acctServiceManager, LanguagePresenter languagePresenter, String userID,
                                String viewedID){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
        this.userID = userID;
        this.viewedID = viewedID;
    }

    /**
     * Execute the view followers user command
     */
    @Override
    public void execute() {
        ArrayList<String> followers = this.acctServiceManager.getFollowers(this.viewedID);
        if(this.userID.equals(this.viewedID)){
            if (followers.size() == 0) {
                this.languagePresenter.display("You have no followers :(");
            } else {
                this.languagePresenter.display("The following are your followers:");
                for (String follower : followers) {
                    this.languagePresenter.display(follower);
                }
            }
        }
        else {
            if (followers.size() == 0) {
                this.languagePresenter.display(this.viewedID + " has no followers.");
            } else {
                this.languagePresenter.display("The following are " + this.viewedID + "'s followers:");
                for (String follower : followers) {
                    this.languagePresenter.display(follower);
                }
            }
        }
        languagePresenter.display("\n");
    }
}
