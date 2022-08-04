package com.artemifyMusicStudio.controller.popupCommand;

import com.artemifyMusicStudio.controller.SimpleCommand;
import com.useCase.UserAccess;


/**
 *  An abstract class to serve as a uniform interface for commands that user will request
 *  for account related service
 *
 * =====Protected Attribute=====:
 *  acctServiceManager - the UserAccess class to allow the program to execute user's command
 *
 * ====== IMPORTANT NOTICE =====:
 *  If you add a new command under the AccountServiceCommand package (i.e. adding a new command class under
 *  Controller.CommandController.AccountServiceCommand folder), you should do the following
 *
 *      1. make the new command class inherit this interface. Namely, extends AccountServiceCommand
 *
 *      2. update the AccountServiceCommandCreator.create() method in the AccountServiceCommandCreator class stored in
 *      Controller.Creator folder. This will ensure your new added command get created properly. Otherwise, you will
 *      not see the new command appear in the UI
 *
 */

public abstract class AccountServiceCommand implements SimpleCommand {

    protected final UserAccess acctServiceManager;

    /**
     * Constructor of AccountServiceCommand
     * @param acctServiceManager a UserAccess object
     */
    public AccountServiceCommand(UserAccess acctServiceManager){
        this.acctServiceManager = acctServiceManager;
    }
}
