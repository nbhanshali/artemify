package com.artemifyMusicStudio.controller.userInteractionCommand;

import com.artemifyMusicStudio.controller.SimpleCommand;
import com.useCase.UserAccess;

/**
 *  An abstract class to serve as a uniform interface for commands that user will request
 *  for interacting with other users
 *
 *
 * ====== IMPORTANT NOTICE =====:
 *  If you add a new command under the UserInteractionCommand package (i.e. adding a new command class under
 *  Controller.CommandController.UserInteractionCommand folder), you should do the following
 *
 *      1. make the new command class inherit the interface. Namely, extends UserInteractionCommand
 *
 *      2. update the UserInteractionCommandCreator.create() method in the UserInteractionCommandCreator class stored in
 *      Controller.Creator folder. This will ensure your new added command get created properly. Otherwise, you will
 *      not see the new command appear in the UI
 *
 *
 */

public abstract class UserInteractionCommand implements SimpleCommand {

    protected final UserAccess acctServiceManager;

    /**
     * Constructor of UserInteractionCommand
     * @param acctServiceManager a UserAccess object
     */
    public UserInteractionCommand(UserAccess acctServiceManager){
        this.acctServiceManager = acctServiceManager;
    }
}
