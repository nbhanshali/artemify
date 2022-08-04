package com.artemifyMusicStudio.controller.popupCommand;

import com.artemifyMusicStudio.controller.SimpleCommand;
import com.useCase.SongManager;

/**
 *  An abstract class to serve as a uniform interface for commands that user will request
 *  for music (song) related service
 *
 * =====Protected Attribute=====:
 *  songManager - a songManager object to allow the program to manage songs
 *
 * ====== IMPORTANT NOTICE =====:
 *  If you add a new command under the MusicServiceCommand package (i.e. adding a new command class under
 *  Controller.CommandController.MusicServiceCommand folder), you should do the following
 *
 *      1. make the new command class inherit this interface. Namely, extends MusicServiceCommand
 *
 *      2. update the MusicServiceCommandCreator.create() method in the MusicServiceCommandCreator class stored in
 *      Controller.CommandController.CommandCreator folder. This will ensure your new added command get created
 *      properly. Otherwise, you will not see the new command appear in the UI
 *
 */
public abstract class MusicServiceCommand implements SimpleCommand {

    protected final SongManager songManager;

    /**
     * Constructor of MusicServiceCommand
     * @param songManager a SongManager object
     */
    public MusicServiceCommand(SongManager songManager){
        this.songManager = songManager;
    }
}
