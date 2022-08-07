package com.artemifyMusicStudio.controller.commandCreator;

import com.artemifyMusicStudio.controller.pageNavigationCommand.*;
import com.artemifyMusicStudio.controller.SimpleCommandCreator;
import com.artemifyMusicStudio.controller.SimpleCommand;
import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.ActivityServiceCache;

/**
 * A concrete creator class to create PageNavigationCommand Object
 *
 *  ====== IMPORTANT NOTICE =====:
 *  If you add a new command under the PageNavigationCommand package (i.e. adding a new command class under
 *  Controller.CommandController.PageNavigationCommand folder), you should do the following
 *
 *      1. make the new command class inherit this interface. Namely, extends PageNavigationCommand
 *
 *      2. update the PageNavigationCommandCreator.create() method in the PageNavigationCommandCreator class stored in
 *      Controller.Creator folder. This will ensure your new added command get created properly. Otherwise, you will
 *      not see the new command appear in the UI
 *
 */
public class PageNavigationCommandCreator implements SimpleCommandCreator {

    private final ActivityServiceCache activityServiceCache;

    /**
     * Constructor of PageNavigationCommandCreator
     * @param activityServiceCache a PageCreator object
     */
    public PageNavigationCommandCreator(ActivityServiceCache activityServiceCache){
        this.activityServiceCache = activityServiceCache;
    }

    /**
     *
     * @param type an Enum in MenuItemType
     * @return a concrete command object that corresponding to the menu item type
     */
    public SimpleCommand create(CommandItemType type){
        switch (type) {
            case PROFILE_AND_SETTING:
                return new ProfileAndSettingCommand(this.activityServiceCache);
            default:
                return null;
        }
    }
}
