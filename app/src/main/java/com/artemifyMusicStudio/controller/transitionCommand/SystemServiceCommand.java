package com.artemifyMusicStudio.controller.transitionCommand;

import com.artemifyMusicStudio.controller.SimpleCommand;

/**
 *  An abstract class to serve as a uniform interface for commands that user will request
 *  for system related service like request login in MainPage, request logout in RegularUserHomePage, request the
 *  termination of the program
 *
 *
 * ====== IMPORTANT NOTICE =====:
 *  If you add a new command under the SystemServiceCommand package (i.e. adding a new command class under
 *  Controller.CommandController.SystemServiceCommand folder), you should do the following
 *
 *      1. make the new command class inherit the interface. Namely, extends SystemServiceCommand
 *
 *      2. update the SystemServiceCommandCreator.create() method in the SystemServiceCommandCreator class stored in
 *      Controller.Creator folder. This will ensure your new added command get created properly. Otherwise, you will
 *      not see the new command appear in the UI
 *
 *
 */

public abstract class SystemServiceCommand implements SimpleCommand {}
