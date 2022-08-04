package com.artemifyMusicStudio.controller.commandCreator;

import com.artemifyMusicStudio.controller.SimpleCommand;
import com.artemifyMusicStudio.controller.SimpleCommandCreator;
import com.artemifyMusicStudio.controller.systemServiceCommand.LogOutCommand;
import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;

/**
 * A concrete creator class to create SystemServiceCommand Object
 *
 * ====== IMPORTANT NOTICE =====:
 *  If you add a new command under the SystemServiceCommand package (i.e. adding a new command class under
 *  Controller.CommandController.SystemServiceCommand folder), you should do the following
 *
 *      1. make the new command class inherit this interface. Namely, extends SystemServiceCommand
 *
 *      2. update the SystemServiceCommandCreator.create() method in the SystemServiceCommandCreator class stored in
 *      Controller.Creator folder. This will ensure your new added command get created properly. Otherwise, you will
 *      not see the new command appear in the UI
 */

public class SystemServiceCommandCreator implements SimpleCommandCreator {
    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;

    /**
     * Constructor of SystemServiceCommandCreator
     * @param activityServiceCache a PageCreator object
     */
    public SystemServiceCommandCreator(ActivityServiceCache activityServiceCache){
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = activityServiceCache.getLanguagePresenter();
    }

    /**
     * @param type a menu item type that stored in MenuItemType
     * @return a concrete system service command
     */
    @Override
    public SimpleCommand create(CommandItemType type) {
        switch (type){
            case LOG_OUT:
                return new LogOutCommand(this.languagePresenter);
            default:
                return null;
        }
    }
}
