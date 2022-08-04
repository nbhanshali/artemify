package com.artemifyMusicStudio.controller.transitionCommand;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.PageType;
import com.artemifyMusicStudio.controller.pageNavigationCommand.PageNavigationCommand;
import com.presenters.LanguagePresenter;

/**
 * A QuitCommand class to quit the Admin mode
 *
 */
public class QuitAdminCommand extends PageNavigationCommand {

    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;

    private final String userID;

    /**
     /**
     * Constructor of QuitAdminCommand
     * @param activityServiceCache a PageCreator object
     */

    public QuitAdminCommand(ActivityServiceCache activityServiceCache){
        super();
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = this.activityServiceCache.getLanguagePresenter();
        this.userID = this.activityServiceCache.getUserID();
    }

    /**
     * Execute the quit admin mode request
     */
    @Override
    public void execute() {
        this.languagePresenter.display("You have successfully exited admin mode.\n");
        this.activityServiceCache.setUserID(this.userID);
        PageActivity regularUserHomePage = this.activityServiceCache.creat(PageType.REGULAR_USER_HOME_PAGE);
        regularUserHomePage.invokes();
    }
}
