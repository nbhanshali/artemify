package com.artemifyMusicStudio;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;
import com.artemifyMusicStudio.controller.commandCreator.TransitionCommandCreator;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.SongManager;
import com.useCase.UserAccess;

import java.util.ArrayList;

/**
 * A UserDisplayPage
 */
public class UserDisplayPage extends PageActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_display_page);

        // parse service cache, searchResult and searchType
        parseActivityServiceCache();
        this.activityServiceCache.setCurrentPageActivity(this);

        // set UserDisplayPage info
        setUpUserDisplayInfo();

        // populateButtons
        populateButtons();
    }

    @Override
    protected void populateButtons() {
        LanguagePresenter languagePresenter = this.activityServiceCache.getLanguagePresenter();
        PlaylistManager playlistServiceManager = this.activityServiceCache.getPlaylistManager();
        UserAccess acctServiceManager = this.activityServiceCache.getUserAcctServiceManager();
        SongManager songManager = this.activityServiceCache.getSongManager();
        String targetUserID = this.activityServiceCache.getTargetUserID();

        // populate Song, Playlist and LikedPlaylistButtons
        populatePublicSongButtons(languagePresenter, acctServiceManager, playlistServiceManager,
                songManager, targetUserID);
        populatePublicPlaylistButtons(languagePresenter, acctServiceManager, playlistServiceManager,
                targetUserID);
        populateLikedPlaylistButtons(languagePresenter, acctServiceManager, playlistServiceManager,
                targetUserID);
    }

    private void populatePublicSongButtons(LanguagePresenter languagePresenter,
                                           UserAccess acctServiceManager,
                                           PlaylistManager playlistServiceManager,
                                           SongManager songManager,
                                           String targetUserID){
        int publicSongsPlaylistID = acctServiceManager.getUserPublicSongsID(targetUserID);
        ArrayList<Integer> songsIDs = playlistServiceManager.getListOfSongsID(publicSongsPlaylistID);
        ArrayList<String> songNames = songManager.getPlaylistSongNames(songsIDs);

        // Populate Song display buttons
        populateTargetInfoButtons(languagePresenter, songsIDs,songNames,R.id.display_public_songs,
                CommandItemType.INVOKE_SONG_DISPLAY);
    }

    private void populatePublicPlaylistButtons(LanguagePresenter languagePresenter,
                                               UserAccess acctServiceManager,
                                               PlaylistManager playlistServiceManager,
                                               String targetUserID){
        ArrayList<Integer> playlistIDs = acctServiceManager.getListOfPlaylistsIDs(targetUserID,
                "Public");
        ArrayList<String> playlistNames = playlistServiceManager.getListOfPlaylistNames(playlistIDs);
        // Populate Song display buttons
        populateTargetInfoButtons(languagePresenter, playlistIDs,playlistNames,R.id.display_public_playlist,
                CommandItemType.INVOKE_PLAYLIST_DISPLAY);
    }

    private void populateLikedPlaylistButtons(LanguagePresenter languagePresenter,
                                              UserAccess acctServiceManager,
                                              PlaylistManager playlistServiceManager,
                                              String targetUserID){
        ArrayList<Integer> likedPlaylistIDs = acctServiceManager.getUserLikedPlaylistsIDs(targetUserID);
        ArrayList<String>  likedPlaylistNames = new ArrayList<>();
        for (int playlistID: likedPlaylistIDs){
            String creator_username = playlistServiceManager.getCreatorUsername(playlistID);
            String playlist_name = playlistServiceManager.getPlaylistName(playlistID);
            String composite_name = playlist_name + ", created by " + creator_username;
            likedPlaylistNames.add(composite_name);
        }
        populateTargetInfoButtons(languagePresenter, likedPlaylistIDs,likedPlaylistNames,
                R.id.display_liked_playlist, CommandItemType.INVOKE_PLAYLIST_DISPLAY);
    }

    private void populateTargetInfoButtons(LanguagePresenter languagePresenter,
                                           ArrayList<Integer> targetIDs,
                                           ArrayList<String> targetNames, Integer layoutID,
                                           CommandItemType targetCommandType){
        // Get layout and create buttons
        LinearLayout publicSongDisplay = findViewById(layoutID);
        int count = 0;
        TransitionCommandCreator transitionCommandCreator = new TransitionCommandCreator(this.activityServiceCache);
        for (int targetID: targetIDs){
            String buttonDescription = languagePresenter.translateString(targetNames.get(count));
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setId(count);
            button.setText(buttonDescription);
            transitionCommandCreator.setTargetID(Integer.toString(targetID));
            View.OnClickListener onClickListener = transitionCommandCreator.create(targetCommandType);
            button.setOnClickListener(onClickListener);

            // populate the button to the layout
            publicSongDisplay.addView(button);
            count += 1;
        }
        publicSongDisplay.setGravity(Gravity.CENTER);
    }

    private void setUpUserDisplayInfo(){
        // get the user's name for display
        String targetUserID = activityServiceCache.getTargetUserID();
        //calculate the user's accumulated likes
        int accumulateLikes = 0;
        //calculate the likes from this user's songs
        int publicSongs = activityServiceCache.getUserAcctServiceManager().getUserPublicSongsID(targetUserID);
        int privateSongs = activityServiceCache.getUserAcctServiceManager().getUserPrivateSongsID(targetUserID);
        ArrayList<Integer> publicSongsID = activityServiceCache.getPlaylistManager().getListOfSongsID(publicSongs);
        ArrayList<Integer> privateSongsID = activityServiceCache.getPlaylistManager().getListOfSongsID(privateSongs);
        for (int i: publicSongsID) {
            accumulateLikes += activityServiceCache.getSongManager().getSongNumLikes(i);
        }
        for (int i: privateSongsID) {
            accumulateLikes += activityServiceCache.getSongManager().getSongNumLikes(i);
        }
        //calculate the likes from this user's playlists
        ArrayList<Integer> publicPlaylists = activityServiceCache.getUserAcctServiceManager().
                getListOfPlaylistsIDs(targetUserID, "Public");
        ArrayList<Integer> privatePlaylists = activityServiceCache.getUserAcctServiceManager().
                getListOfPlaylistsIDs(targetUserID, "Private");
        for (int i: publicPlaylists) {
            accumulateLikes += activityServiceCache.getPlaylistManager().getNumLikes(i);
        }
        for (int i: privatePlaylists) {
            accumulateLikes += activityServiceCache.getPlaylistManager().getNumLikes(i);
        }
        String numFollowers = Integer.toString(activityServiceCache.getUserAcctServiceManager().getNumFollowers(targetUserID));
        String numFollowing = Integer.toString(activityServiceCache.getUserAcctServiceManager().getNumFollowing(targetUserID));
        String numLikes = Integer.toString(accumulateLikes);

        // populate num of follower info
        TextView numOfFollowersDisplay = findViewById(R.id.num_followers);
        numOfFollowersDisplay.setText(numFollowers);

        // populate num of following info
        TextView numOfFollowingDisplay = findViewById(R.id.num_followings);
        numOfFollowingDisplay.setText(numFollowing);

        // populate like info
        TextView numOfLikes = findViewById(R.id.num_of_likes);
        numOfLikes.setText(numLikes);
    }

    /**
     *
     * No usage in this class, no actual implementation
     */
    @Override
    protected SimpleButtonCommandCreator getSimpleOnClickCommandCreator(String creatorType) {
        return null;
    }

    /**
     * No usage in this class, no actual implementation
     */
    @Override
    protected void populateIdMenuMap() {}

    /**
     * No usage in this class, no actual implementation
     */
    @Override
    protected void populateMenuCommandCreatorMap() {}

    /**
     * No usage in this class, no actual implementation
     */
    @Override
    protected void populateExitPageMenuItems() {}
}