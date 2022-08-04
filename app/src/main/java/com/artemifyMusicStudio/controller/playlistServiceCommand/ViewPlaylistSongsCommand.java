package com.artemifyMusicStudio.controller.playlistServiceCommand;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;

import java.util.ArrayList;

/**
 * A ViewPlaylistSongsCommand object to handle the user view all the songs in playlist request
 */

public class ViewPlaylistSongsCommand extends PlaylistServiceCommand {

    private final ActivityServiceCache activityServiceCache;

    private final LanguagePresenter languagePresenter;
    private final String targetPlaylistID;

    /**
     * Constructor for ViewPlaylistCommand.
     *
     * @param activityServiceCache            a PageCreator Object
     * @param languagePresenter      a LanguagePresenter Object
     * @param playlistServiceManager a UserAccess object
     * @param targetPlaylistID a String that represents the ID of the playlist that user want to look at
     */
    public ViewPlaylistSongsCommand(ActivityServiceCache activityServiceCache, LanguagePresenter languagePresenter,
                                    PlaylistManager playlistServiceManager, String targetPlaylistID) {
        super(playlistServiceManager);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
        this.targetPlaylistID = targetPlaylistID;
    }

    /**
     * Execute LikePlaylistCommand to display all song names in the playlist.
     */
    @Override
    public void execute() {
        String playlistName = this.activityServiceCache.getPlaylistManager().
                getPlaylistName(Integer.parseInt(this.targetPlaylistID));
        this.languagePresenter.display("Here are all the songs in \uD83D\uDCBD " + playlistName + " \uD83D\uDCBD:");
        ArrayList<Integer> allSongsID = this.playlistServiceManager.getListOfSongsID
                (Integer.parseInt(this.targetPlaylistID));
        for (Integer songID: allSongsID){
            this.languagePresenter.display(this.activityServiceCache.getSongManager().findSong(songID).getName());
        }
        languagePresenter.display("\n");
    }
}
