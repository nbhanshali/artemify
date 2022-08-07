package com.artemifyMusicStudio.controller.commandCreator;

import android.view.View;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;
import com.artemifyMusicStudio.controller.popupCommand.LikePlaylistCommand;
import com.artemifyMusicStudio.controller.popupCommand.PlayPlaylistCommand;
import com.artemifyMusicStudio.controller.popupCommand.ViewCreatorCommand;
import com.artemifyMusicStudio.controller.popupCommand.ViewFollowersCommand;
import com.artemifyMusicStudio.controller.popupCommand.ViewPlaylistSongsCommand;
import com.artemifyMusicStudio.controller.popupCommand.inputDialogPopupCommand.PopUpInputDialogCommand;
import com.presenters.LanguagePresenter;

/**
 * A PopupCommandCreator to create pop up related command
 */
public class PopupCommandCreator implements SimpleButtonCommandCreator {
    private final ActivityServiceCache activityServiceCache;

    /**
     * Constructor of PopupCommandCreator
     * @param activityServiceCache a ActivityServiceCache object
     */
    public PopupCommandCreator(ActivityServiceCache activityServiceCache){
        this.activityServiceCache = activityServiceCache;
    }

    @Override
    public View.OnClickListener create(CommandItemType type) {
        switch (type){
            case POP_UP_SEARCH_USER_DIALOG:
                return new PopUpInputDialogCommand(this.activityServiceCache,
                        "Search by User",
                        "Enter the username of the user you wish to search",
                        CommandItemType.SEARCH_USER);
            case POP_UP_SEARCH_PLAYLIST_DIALOG:
                return new PopUpInputDialogCommand(this.activityServiceCache,
                        "Search by Playlist",
                        "Enter the name of the playlist you wish to search",
                        CommandItemType.SEARCH_PLAYLIST);
            case POP_UP_SEARCH_SONG_DIALOG:
                return new PopUpInputDialogCommand(this.activityServiceCache,
                        "Search by Song",
                        "Enter the name of the song you wish to search",
                        CommandItemType.SEARCH_SONG);
            case VIEW_PLAYLIST_SONGS:
                return new ViewPlaylistSongsCommand(this.activityServiceCache,
                        this.activityServiceCache.getLanguagePresenter(),
                        this.activityServiceCache.getPlaylistManager(),
                        this.activityServiceCache.getTargetPlaylistID());
            case VIEW_CREATOR:
                return new ViewCreatorCommand(this.activityServiceCache,
                        this.activityServiceCache.getPlaylistManager(),
                        this.activityServiceCache.getTargetUserID());
            case LIKE_PLAYLIST:
                return new LikePlaylistCommand(this.activityServiceCache,
                        this.activityServiceCache.getLanguagePresenter(),
                        this.activityServiceCache.getPlaylistManager(),
                        this.activityServiceCache.getUserID(),
                        this.activityServiceCache.getTargetPlaylistID());
            case PLAY_PLAYLIST:
                return new PlayPlaylistCommand(this.activityServiceCache,
                        this.activityServiceCache.getLanguagePresenter(),
                        this.activityServiceCache.getPlaylistManager(),
                        this.activityServiceCache.getTargetPlaylistID());
            case VIEW_FOLLOWERS:
                return new ViewFollowersCommand(this.activityServiceCache,
                        this.activityServiceCache.getLanguagePresenter(),
                        this.activityServiceCache.getUserID(),
                        this.activityServiceCache.getTargetUserID());
            default:
                return null;
        }
    }
}
