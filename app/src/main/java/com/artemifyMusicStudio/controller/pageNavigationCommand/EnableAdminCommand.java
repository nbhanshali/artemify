package com.artemifyMusicStudio.controller.pageNavigationCommand;

import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageType;
import com.presenters.LanguagePresenter;
import com.useCase.UserAccess;

/**
 * The EnableAdminCommand object to allow user to enter to admin mode in RegularUserHomePage if he/she has admin right
 */
public class EnableAdminCommand extends PageNavigationCommand {
    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;

    private final UserAccess acctServiceManager;
    private final String userID;

    /**
     * Constructor of the EnableAdminCommand class
     * @param activityServiceCache a PageCreator object
     *
     */
    public EnableAdminCommand(ActivityServiceCache activityServiceCache){
        super();
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = this.activityServiceCache.getLanguagePresenter();
        this.acctServiceManager = this.activityServiceCache.getUserAcctServiceManager();
        this.userID = this.activityServiceCache.getUserID();
    }

    /**
     * Execute the EnableAdminCommand
     */
    @Override
    public void execute() {
        this.activityServiceCache.setUserID(userID);
        if (this.acctServiceManager.isAdmin(userID)){
            this.languagePresenter.display("You are now logged in as an admin.\n");
            PageActivity adminPage = this.activityServiceCache.creat(PageType.ADMIN_PAGE);
            adminPage.invokes();
        }
        else{
            this.languagePresenter.display("Sorry, you don't have admin rights.\n");
            PageActivity regularUserHomePage = this.activityServiceCache.creat(PageType.REGULAR_USER_HOME_PAGE);
            regularUserHomePage.invokes();
        }
    }
}
