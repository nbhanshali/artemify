package com.artemifyMusicStudio.controller.accountServiceCommand;
import com.presenters.LanguagePresenter;
import com.useCase.UserAccess;

import java.util.Scanner;

/**
 * A BanUserCommand class to handle the ban user request from an admin user
 *
 */

public class BanUserCommand extends AccountServiceCommand {
    private final LanguagePresenter languagePresenter;


    /**
     * Constructor of BanUserCommand
     * @param languagePresenter a LanguagePresenter object
     * @param acctServiceManager a UserAccess object
     */
    public BanUserCommand(LanguagePresenter languagePresenter, UserAccess acctServiceManager){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
    }

    /**
     * Execute the ban user command
     */
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);

        this.languagePresenter.display("Enter the username of the user you wish to ban: ");
        String username = in.nextLine();
        if (this.acctServiceManager.exists(username)) {
            if (this.acctServiceManager.findUser(username).getIsAdmin()) {
                this.languagePresenter.display("Invalid action. You cannot ban admins.");
            } else if (this.acctServiceManager.ban(username)) {
                this.languagePresenter.display("Successfully banned");
            }
        }
        else{
            this.languagePresenter.display("User does not exist");
        }
    }
}
