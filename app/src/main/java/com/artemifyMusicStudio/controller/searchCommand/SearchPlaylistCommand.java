package com.artemifyMusicStudio.controller.searchCommand;

import android.widget.EditText;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.PageType;
import com.useCase.PlaylistManager;

import java.util.ArrayList;

/**
 * A command class to handle the request of searching a playlist
 */
public class SearchPlaylistCommand extends SearchServiceCommand {
    private final PlaylistManager playlistServiceManager;

    /**
     * Constructor of SearchPlaylistCommand
     * @param activityServiceCache a ActivityServiceCache object
     *
     */
    public SearchPlaylistCommand(ActivityServiceCache activityServiceCache,
                                 EditText inputTargetName){
        super(activityServiceCache, inputTargetName);
        this.playlistServiceManager = this.activityServiceCache.getPlaylistManager();
        this.searchType = "Playlist";
    }

    /**
     * Execute the searching of playlist by invoking method in playlist manager
     */

    @Override
    protected ArrayList<String> getTargetEntityIDs(String searchString) {
        return this.playlistServiceManager.getListOfStringPlaylistIDs(searchString);
    }

    @Override
    protected String getSearchResultDescription(int index, String searchName, String targetEntityID) {
        int playlistID = Integer.parseInt(targetEntityID);
        return "  " + index + ". " + searchName + ", created by "
                + this.playlistServiceManager.getCreatorUsername(playlistID);
    }

    @Override
    protected PageActivity getEntityDisplayPage(String targetEntityID) {
        int targetPlaylistID = Integer.parseInt(targetEntityID);
        String targetSongCreator = this.playlistServiceManager.getCreatorUsername(targetPlaylistID);
        // Update the targetSongId and targetUserId in page creator
        this.activityServiceCache.setTargetPlaylistID(String.valueOf(targetPlaylistID));
        this.activityServiceCache.setTargetUserID(targetSongCreator);
        return this.activityServiceCache.creat(PageType.PLAYLIST_DISPLAY_PAGE);
    }
}
