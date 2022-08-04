package com.artemifyMusicStudio.controller.playlistServiceCommand;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;

/**
 * A LikePlaylistCommand object to handle the user like playlist request
 */

public class LikePlaylistCommand extends PlaylistServiceCommand {
    private final ActivityServiceCache activityServiceCache;

    private final LanguagePresenter languagePresenter;
    private final String userID;
    private final String playlistID;

    /**
     * Constructor for LikePlaylistCommand.
     *
     * @param activityServiceCache a PageCreator Object
     * @param languagePresenter a LanguagePresenter Object
     * @param playlistServiceManager a UserAccess object
     * @param userID a String represents the user's id
     * @param playlistID a String represents the playlist's id
     */
    public LikePlaylistCommand(ActivityServiceCache activityServiceCache, LanguagePresenter languagePresenter,
                               PlaylistManager playlistServiceManager, String userID, String playlistID) {
        super(playlistServiceManager);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
        this.userID = userID;
        this.playlistID = playlistID;
    }

    /**
     * Execute LikePlaylistCommand to increase likes that this playlist has by one and add this playlist to user's
     * MyLikedPlaylists.
     */
    @Override
    public void execute() {
        if (activityServiceCache.getUserAcctServiceManager().getUserLikedPlaylistsIDs(userID).
                contains(Integer.parseInt(playlistID))) {
            languagePresenter.
                    display("Already liked! (＾▽＾) You can find this playlist under your liked playlists.\n");
        } else {
            this.activityServiceCache.getPlaylistManager().likePlaylist(Integer.parseInt(this.playlistID));
            this.activityServiceCache.getUserAcctServiceManager().addToUserLikedPlaylist(this.userID,
                    Integer.parseInt(this.playlistID));
//            pageCreator.getPlaylistManager().
            this.languagePresenter.display("Liked! (＾▽＾) Added to your Liked Playlists.\n");
        }
    }
}
