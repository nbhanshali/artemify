package com.artemifyMusicStudio.controller.commandCreator;

import com.artemifyMusicStudio.controller.playlistServiceCommand.*;
import com.artemifyMusicStudio.controller.SimpleCommandCreator;
import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;

/**
 * A concrete creator class to create PlaylistServiceCommand Object
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
 */

public class PlaylistServiceCommandCreator implements SimpleCommandCreator {
    private final LanguagePresenter languagePresenter;

    private final ActivityServiceCache activityServiceCache;
    private final PlaylistManager playlistServiceManager;
    private final String userID;
    private final String viewedID;
    private final String playlistID;

    /**
     * Constructor of PlaylistServiceCommandCreator
     * @param activityServiceCache a PageCreator object
     */
    public PlaylistServiceCommandCreator(ActivityServiceCache activityServiceCache){
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = this.activityServiceCache.getLanguagePresenter();
        this.playlistServiceManager = this.activityServiceCache.getPlaylistManager();
        this.userID = this.activityServiceCache.getUserID();
        this.viewedID = this.activityServiceCache.getTargetUserID();
        this.playlistID = this.activityServiceCache.getTargetPlaylistID();
    }

    /**
     * This method creates a specific command based on the input MenuItemType type
     * @param type an Enum in MenuItemType
     * @return a concrete command object that corresponding to the menu item type
     *
     * ====Important Notice====:
     * Every time when you add a new command controller that inherits PlaylistServiceCommand, you need to update this
     * method so that the PlaylistServiceCommandCreator can create that specific command
     */
    public PlaylistServiceCommand create(CommandItemType type){
        switch (type) {
            case VIEW_PLAYLIST_SONGS:
                return new ViewPlaylistSongsCommand(this.activityServiceCache, this.languagePresenter,
                        this.playlistServiceManager, this.playlistID);
            case VIEW_CREATOR:
                return new ViewCreatorCommand(this.activityServiceCache, this.playlistServiceManager, this.viewedID);
            case LIKE_PLAYLIST:
                return new LikePlaylistCommand(this.activityServiceCache, this.languagePresenter,
                        this.playlistServiceManager, this.userID, this.playlistID);
            case PLAY_PLAYLIST:
                return new PlayPlaylistCommand(this.activityServiceCache, this.languagePresenter,
                        this.playlistServiceManager, this.playlistID);
            case CREATE_NEW_PLAYLIST:
                return new CreateNewPlaylistCommand(this.activityServiceCache, this.languagePresenter,
                        this.playlistServiceManager, this.userID);
            case ADD_TO_EXISTING_PLAYLIST:
                return new AddToExistingPlaylistCommand(this.activityServiceCache, this.languagePresenter,
                        this.playlistServiceManager);
            case ADD_TO_NEW_PLAYLIST:
                return new AddToNewPlaylistCommand(this.activityServiceCache, this.playlistServiceManager);
            default:
                return null;
        }
    }
}
