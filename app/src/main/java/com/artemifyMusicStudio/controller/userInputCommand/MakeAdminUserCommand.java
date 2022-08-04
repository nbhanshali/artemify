package com.artemifyMusicStudio.controller.userInputCommand;

import com.artemifyMusicStudio.controller.accountServiceCommand.AccountServiceCommand;
import com.presenters.LanguagePresenter;
import com.useCase.UserAccess;

import java.util.Scanner;

/**
 * A MakeAdminUserCommand class to grant another user the admin right
 *
 */

public class MakeAdminUserCommand extends AccountServiceCommand {

    private final LanguagePresenter languagePresenter;

    /**
     * Constructor of MakeAdminUserCommand
     * @param languagePresenter a LanguagePresenter object
     * @param acctServiceManager a UserAccess object
     */
    public MakeAdminUserCommand(LanguagePresenter languagePresenter, UserAccess acctServiceManager){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
    }

    /**
     * Execute the request of granting a user the admin right
     */
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);

        this.languagePresenter.display("Enter the username of the user you want to make admin: ");
        String username = in.nextLine();
        if(this.acctServiceManager.makeAdmin(username)){
            this.languagePresenter.display("Admin rights granted");
        }else{
            this.languagePresenter.display("User does not exist");
        }
    }
}
