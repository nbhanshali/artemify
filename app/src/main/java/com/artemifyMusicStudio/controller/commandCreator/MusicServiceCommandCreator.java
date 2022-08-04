package com.artemifyMusicStudio.controller.commandCreator;

import com.artemifyMusicStudio.controller.musicServiceCommand.*;
import com.artemifyMusicStudio.controller.SimpleCommandCreator;
import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;
import com.useCase.SongManager;

/**
 * A concrete creator class to create MusicServiceCommand Object
 *
 * ====== IMPORTANT NOTICE =====:
 *  If you add a new command under the MusicServiceCommand package (i.e. adding a new command class under
 *  Controller.CommandController.MusicServiceCommand folder), you should do the following
 *
 *      1. make the new command class inherit this interface. Namely, extends MusicServiceCommand
 *
 *      2. update the MusicServiceCommandCreator.create() method in the MusicServiceCommandCreator class stored in
 *      Controller.CommandCreator folder. This will ensure your new added command get created properly. Otherwise,
 *      you will not see the new command appear in the UI
 */
public class MusicServiceCommandCreator implements SimpleCommandCreator {
    private final LanguagePresenter languagePresenter;

    private final ActivityServiceCache activityServiceCache;

    private final SongManager songManager;

    /**
     * Constructor of MusicServiceCommandCreator
     * @param activityServiceCache a PageCreator object
     */
    public MusicServiceCommandCreator(ActivityServiceCache activityServiceCache){
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = this.activityServiceCache.getLanguagePresenter();
        this.songManager = this.activityServiceCache.getSongManager();
    }

    /**
     *
     * @param type an Enum in MenuItemType
     * @return a concrete command object that corresponding to the menu item type
     *
     * ====Important Notice====:
     * Every time when you add a new command controller that inherits MusicServiceCommand, you need to update this
     * method so that the MusicServiceCommandCreator can create that specific command
     */
    public MusicServiceCommand create(CommandItemType type){
        switch (type) {
            case VIEW_LYRICS:
                return new ViewLyricsCommand(this.activityServiceCache, this.languagePresenter,
                        this.songManager);
            case LIKE_SONG:
                return new LikeSongCommand(this.activityServiceCache, this.languagePresenter,
                        this.songManager);
            default:
                return null;
        }
    }
}
