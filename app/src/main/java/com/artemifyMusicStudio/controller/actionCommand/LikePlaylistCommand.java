package com.artemifyMusicStudio.controller.actionCommand;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.R;
import com.presenters.LanguagePresenter;

import java.util.ArrayList;

public class LikePlaylistCommand implements View.OnClickListener{
    private final ActivityServiceCache activityServiceCache;
    private final PageActivity playlistDisplayPage;
    private final LanguagePresenter languagePresenter;
    private final String userID;
    private final int playlistID;
    private final ImageButton imageButton;
    private final TextView likeView;

    /**
     * Constructor for LikePlaylistCommand.
     *
     * @param activityServiceCache a PageCreator Object
     * @param userID a String represents the user's id
     * @param playlistID a String represents the playlist's id
     */
    public LikePlaylistCommand(ActivityServiceCache activityServiceCache, int userID, int playlistID){
        this.activityServiceCache = activityServiceCache;
        playlistDisplayPage = activityServiceCache.getCurrentPageActivity();
        this.languagePresenter = activityServiceCache.getLanguagePresenter();
        this.userID = String.valueOf(userID);
        this.playlistID = playlistID;

        imageButton = playlistDisplayPage.findViewById(R.id.display_like_playlist_button);
        likeView = playlistDisplayPage.findViewById(R.id.display_numlikes);

    }

    /**
     * Execute LikePlaylistCommand to increase likes that this playlist has by one and add this playlist to user's
     * MyLikedPlaylists.
     */
    @Override
    public void onClick(View v) {
        // checkUnlike means the user hasn't liked the playlist yet and want to like it
        if (checkUnlike()) {
            displayLikeMsg(playlistDisplayPage);
            imageButton.setBackgroundResource(R.drawable.like_button);
            this.activityServiceCache.getPlaylistManager().likePlaylist(playlistID);
            this.activityServiceCache.getUserAcctServiceManager().addToUserLikedPlaylist(userID, playlistID);
        } else {
            displayUnlikeMsg(playlistDisplayPage);
            imageButton.setBackgroundResource(R.drawable.empty_heart);
            this.activityServiceCache.getPlaylistManager().unlikePlaylist(playlistID);
            this.activityServiceCache.getPlaylistManager().deletePlaylistsByIDs(new ArrayList<>(playlistID));
        }
        setLike();
    }

    private void displayLikeMsg(PageActivity currentPageActivity) {
        String Msg =  this.languagePresenter.translateString("You like the playlist! (＾▽＾) Added to your Liked Playlists");
        Toast.makeText(currentPageActivity, Msg, Toast.LENGTH_LONG).show();
    }

    private void displayUnlikeMsg(PageActivity currentPageActivity) {
        String Msg =  languagePresenter.translateString("You unlike the playlist");
        Toast.makeText(currentPageActivity, Msg, Toast.LENGTH_LONG).show();
    }

    private boolean checkUnlike(){
        return !activityServiceCache.getUserAcctServiceManager().getUserLikedPlaylistsIDs(userID).contains(playlistID);
    }

    private void setLike(){
        String like = String.valueOf(activityServiceCache.getPlaylistManager().getNumLikes(playlistID));
        likeView.setText(like);
    }
}
