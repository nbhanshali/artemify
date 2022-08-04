package com.artemifyMusicStudio.controller.popupCommand;

import com.artemifyMusicStudio.controller.SimpleCommand;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *  An abstract class to serve as a uniform interface for commands that user will request
 *  for playlist related service
 *
 * =====Protected Attribute=====:
 *  playlistServiceManager - the PlaylistManager class to allow the program to execute user's command on playlists
 *
 * ====== IMPORTANT NOTICE =====:
 *  If you add a new command under the PlaylistServiceCommand package (i.e. adding a new command class under
 *  Controller.CommandController.PlaylistServiceCommand folder), you should do the following
 *
 *      1. make the new command class inherit this interface. Namely, extends PlaylistServiceCommand
 *
 *      2. update the PlaylistServiceCommandCreator.create() method in the PlaylistServiceCommandCreator class stored in
 *      Controller.Creator folder. This will ensure your new added command get created properly. Otherwise, you will
 *      not see the new command appear in the UI
 *
 */

public abstract class PlaylistServiceCommand implements SimpleCommand {

    protected final PlaylistManager playlistServiceManager;

    /**
     * Constructor for PlaylistServiceCommand.
     * @param playlistServiceManager a PlaylistManager Object
     */
    public PlaylistServiceCommand(PlaylistManager playlistServiceManager){
        this.playlistServiceManager = playlistServiceManager;
    }

    /**
     * A protected helper method to determine the next step when not doing the task successfully for the first time
     * @param languagePresenter a LanguagePresenter object
     * @return whether to create again or to cancel this action
     */
    protected boolean wantToCancel(LanguagePresenter languagePresenter) {
        boolean create = false;
        boolean finishCheck = false;
        while (!finishCheck) {
            languagePresenter.display(
                    "Enter 1 to try again or enter 2 to cancel: ");
            try {
                Scanner newVal = new Scanner(System.in);
                int val = newVal.nextInt();
                switch (val) {
                    case 1:
                        finishCheck = true;
                        create = true;
                        break;
                    case 2:
                        finishCheck = true;
                        break;
                    default:
                        languagePresenter.display("Invalid option, please try again.");
                }
            } catch (InputMismatchException e) {
                languagePresenter.display("Invalid input format, please try again.");
            }
        }
        return !create;
    }
}
