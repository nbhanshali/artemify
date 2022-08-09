package com.artemifyMusicStudio.controller.pageTransitionCommand;

import android.view.View;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.SongManager;
import com.useCase.UserAccess;

/**
 * An AddToExistingPlaylistCommand class to add the currently viewed song to an existing playlist by user input
 */
public class AddToExistingPlaylistCommand implements View.OnClickListener {
    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;
    private final PlaylistManager playlistServiceManager;
    private final UserAccess accountServiceManager;
    private final SongManager songManager;
    private final String userID;
    private final int songID;
    private final int playlistID;

    /**
     * Constructor of AddToExistingCommand
     *
     * @param activityServiceCache a PageCreator object
     *
     */
    public AddToExistingPlaylistCommand(ActivityServiceCache activityServiceCache,
                                        int songID, int playlistID){
        this.activityServiceCache = activityServiceCache;
        this.playlistServiceManager = activityServiceCache.getPlaylistManager();
        this.languagePresenter = activityServiceCache.getLanguagePresenter();
        this.accountServiceManager = activityServiceCache.getUserAcctServiceManager();
        this.songManager = activityServiceCache.getSongManager();
        this.userID = activityServiceCache.getUserID();
        this.songID = songID;
        this.playlistID = playlistID;
    }

    /**
     * Execute the AddToExistingPlaylistCommand
     */
    @Override
    public void onClick(View view) {
        boolean songIsPublic = songManager.isPublic(songID);
        // check if it is possible to add the song to the selected playlist
        boolean succeed = playlistServiceManager.addableToPlaylist(playlistID, songIsPublic);
        if (!succeed) {
            String msg = "You cannot add a private song to a public playlist";
            Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
                    this.languagePresenter.translateString(msg), Toast.LENGTH_LONG).show();}
        else {
            if (playlistServiceManager.getListOfSongsID(playlistID).contains(songID)) {
                String msg = "Already added! (⌒‿⌒) You can find this song in \uD83D\uDCBD " +
                        songManager.getSongName(songID) + " \uD83D\uDCBD.";
                Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
                        this.languagePresenter.translateString(msg), Toast.LENGTH_LONG).show();
            }else {
                playlistServiceManager.addToPlaylist(playlistID, songID);
                String msg = "Successfully added! (⌒‿⌒) You can now find this song in \uD83D\uDCBD " +
                        songManager.getSongName(songID) + " \uD83D\uDCBD.";
                Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
                        this.languagePresenter.translateString(msg), Toast.LENGTH_LONG).show();
            }
        }
    }


}
