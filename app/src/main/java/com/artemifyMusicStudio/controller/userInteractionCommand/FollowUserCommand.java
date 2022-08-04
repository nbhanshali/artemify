package com.artemifyMusicStudio.controller.userInteractionCommand;

import com.artemifyMusicStudio.controller.accountServiceCommand.AccountServiceCommand;
import com.presenters.LanguagePresenter;
import com.useCase.UserAccess;


/**
 * A FollowUserCommand class to handle the follow user request from a user
 *
 */
public class FollowUserCommand extends AccountServiceCommand {

    private final LanguagePresenter languagePresenter;
    private final String userID;
    private final String viewedID;

    /** Constructor of FollowUserCommand
     *
     * @param acctServiceManager a UserAccess object
     * @param languagePresenter a LanguagePresenter object
     * @param userID id of user performing the command
     * @param viewedID id of user the command is being performed on
     */
    public FollowUserCommand(UserAccess acctServiceManager, LanguagePresenter languagePresenter, String userID,
                             String viewedID){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
        this.userID = userID;
        this.viewedID = viewedID;
    }

    /**
     * Execute the follow user command
     */
    @Override
    public void execute() {
        if (this.userID.equals(this.viewedID)) {
            this.languagePresenter.display("You cannot follow/unfollow yourself.\n");
        }
        else if (this.acctServiceManager.getFollowing(this.userID).contains(this.viewedID)) {
            this.languagePresenter.display("You are already following " + this.viewedID + ". ( ･ั﹏･ั)ε｀●)\n");
        }
        else{
            this.acctServiceManager.addFollower(this.viewedID, this.userID);
            this.languagePresenter.display("Successfully followed " + this.viewedID + "! (ꈍᴗꈍ)ε｀●)\n");
        }
    }
}
