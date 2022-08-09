package com.artemifyMusicStudio.controller.commandCreator;

import android.view.View;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;
import com.artemifyMusicStudio.controller.popupCommand.PlayPlaylistCommand;
import com.artemifyMusicStudio.controller.popupCommand.ViewCreatorCommand;
import com.artemifyMusicStudio.controller.popupCommand.ViewLoginHistoryCommand;
import com.artemifyMusicStudio.controller.popupCommand.ViewLyricsCommand;
import com.artemifyMusicStudio.controller.popupCommand.ViewPlaylistSongsCommand;
import com.artemifyMusicStudio.controller.userInputCommand.PopUpInputDialogCommand;

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
            case VIEW_PLAYLIST_SONGS:
                return new ViewPlaylistSongsCommand(this.activityServiceCache);
            case VIEW_CREATOR:
                return new ViewCreatorCommand(this.activityServiceCache,
                        this.activityServiceCache.getPlaylistManager(),
                        this.activityServiceCache.getTargetUserID());
            case PLAY_PLAYLIST:
                return new PlayPlaylistCommand(this.activityServiceCache,
                        this.activityServiceCache.getLanguagePresenter(),
                        this.activityServiceCache.getPlaylistManager(),
                        this.activityServiceCache.getTargetPlaylistID());
            case VIEW_LYRICS:
                return new ViewLyricsCommand(this.activityServiceCache);
            case VIEW_LOGIN_HISTORY:
                return new ViewLoginHistoryCommand(this.activityServiceCache,
                        this.activityServiceCache.getLanguagePresenter(),
                        this.activityServiceCache.getUserID());
            default:
                return null;
        }
    }
}
