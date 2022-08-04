package com.artemifyMusicStudio.controller.accountServiceCommand;

import com.presenters.LanguagePresenter;
import com.useCase.UserAccess;

import java.util.Scanner;

/**
 * A UnBanUserCommand class to handle the ban user request from an admin user
 *
 */

public class UnBanUserCommand extends AccountServiceCommand {
    private final LanguagePresenter languagePresenter;

    /**
     * Constructor of UnBanUserCommand
     * @param languagePresenter a LanguagePresenter object
     * @param acctServiceManager a UserAccess object
     */
    public UnBanUserCommand(LanguagePresenter languagePresenter, UserAccess acctServiceManager){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
    }

    /**
     * Execute to unban user request from an admin user
     */
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);

        this.languagePresenter.display("Enter the username of the user you wish to unban: ");
        String username = in.nextLine();
        if(this.acctServiceManager.unban(username)){
            this.languagePresenter.display("Successfully unbanned");
        }else{
            this.languagePresenter.display("User does not exist");
        }
    }
}
