package com.artemifyMusicStudio.controller.pageNavigationCommand;

import com.artemifyMusicStudio.controller.SimpleCommand;

/**
 * An abstract class to serve as a uniform interface for commands that allow user to navigate pages
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
 *
 *
 */

public abstract class PageNavigationCommand implements SimpleCommand {

}
