package com.artemifyMusicStudio.controller.popupCommand;

import com.artemifyMusicStudio.controller.accountServiceCommand.AccountServiceCommand;
import com.presenters.LanguagePresenter;
import com.useCase.UserAccess;


/**
 * An UnfollowUserCommand class to handle the un follow user request from a user
 *
 */
public class UnfollowUserCommand extends AccountServiceCommand {

    private final LanguagePresenter languagePresenter;
    private final String userID;
    private final String viewedID;

    /** Constructor of UnfollowUserCommand
     *
     * @param acctServiceManager a UserAccess object
     * @param languagePresenter a LanguagePresenter object
     * @param userID id of user performing the command
     * @param viewedID id of user the command is being performed on
     */
    public UnfollowUserCommand(UserAccess acctServiceManager, LanguagePresenter languagePresenter, String userID,
                             String viewedID){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
        this.userID = userID;
        this.viewedID = viewedID;
    }

    /**
     * Execute the un follow user command
     */
    @Override
    public void execute() {
        if (this.userID.equals(this.viewedID)) {
            this.languagePresenter.display("You cannot follow/unfollow yourself.\n");
        }
        else if (!this.acctServiceManager.getFollowing(this.userID).contains(this.viewedID)) {
            this.languagePresenter.display("You are already not following " + this.viewedID + ". (　｀_ゝ´)\n");
        }
        else{
            this.acctServiceManager.removeFollower(this.viewedID,this.userID);
            this.languagePresenter.display("Successfully unfollowed " + this.viewedID + ". (っ˘̩╭╮˘̩)っ\n");
        }
    }
}
