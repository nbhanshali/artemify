package com.artemifyMusicStudio.controller.popupCommand;

import android.view.View;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.controller.playlistServiceCommand.PlaylistServiceCommand;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;

/**
 * A LikePlaylistCommand object to handle the user like playlist request
 */

public class LikePlaylistCommand implements View.OnClickListener {
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
    public void onClick(View view) {
        if (activityServiceCache.getUserAcctServiceManager().getUserLikedPlaylistsIDs(userID).
                contains(Integer.parseInt(playlistID))) {
            String alreadyLikedMsg =  this.languagePresenter.translateString("Already liked! (＾▽＾) You can find this playlist under your liked playlists");
            Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
                    alreadyLikedMsg, Toast.LENGTH_LONG).show();
        } else {
            this.activityServiceCache.getPlaylistManager().likePlaylist(Integer.parseInt(this.playlistID));
            this.activityServiceCache.getUserAcctServiceManager().addToUserLikedPlaylist(this.userID,
                    Integer.parseInt(this.playlistID));
            String likedMsg =  this.languagePresenter.translateString("Liked! (＾▽＾) Added to your Liked Playlists");
            Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
                    likedMsg, Toast.LENGTH_LONG).show();
        }
    }

}
