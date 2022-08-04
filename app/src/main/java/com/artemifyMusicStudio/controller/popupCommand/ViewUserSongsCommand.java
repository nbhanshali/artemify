package com.artemifyMusicStudio.controller.popupCommand;

import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.SongManager;
import com.useCase.UserAccess;

import java.util.ArrayList;

/**
 * A ViewUserSongsCommand class to handle the view songs request from a user
 *
 */
public class ViewUserSongsCommand extends UserInteractionCommand {

    private final LanguagePresenter languagePresenter;
    private final PlaylistManager playlistServiceManager;
    private final SongManager songManager;
    private final String userID;
    private final String viewedID;

    /** Constructor for ViewUserSongsCommand
     *
     * @param acctServiceManager a UserAccess object
     * @param languagePresenter a LanguagePresenter object
     * @param playlistServiceManager a PlaylistManager object
     * @param songManager a SongManager object
     * @param userID id of user performing the command
     * @param viewedID id of user the command is being performed on
     */
    public ViewUserSongsCommand(UserAccess acctServiceManager, LanguagePresenter languagePresenter,
                                PlaylistManager playlistServiceManager, SongManager songManager,
                                String userID, String viewedID){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
        this.playlistServiceManager = playlistServiceManager;
        this.songManager = songManager;
        this.userID = userID;
        this.viewedID = viewedID;
    }

    /**
     * Execute the view songs command
     */
    @Override
    public void execute() {
        int publicSongsPlaylistID = this.acctServiceManager.getUserPublicSongsID(this.viewedID);
        ArrayList<Integer> songsIDs = this.playlistServiceManager.getListOfSongsID(publicSongsPlaylistID);
        ArrayList<String> songNames = this.songManager.getPlaylistSongNames(songsIDs);
        if (this.userID.equals(this.viewedID)){
            if (songNames.size() == 0) {
                this.languagePresenter.display("You have no public songs.");
            } else {
                this.languagePresenter.display("The following are your public songs:");
                for (String songName : songNames) {
                    this.languagePresenter.display(songName);
                }
            }
        }
        else {
            if (songNames.size() == 0) {
                this.languagePresenter.display(this.viewedID + " has no public songs.");
            } else {
                this.languagePresenter.display("The following are " + this.viewedID + "'s public songs:");
                for (String songName : songNames) {
                    this.languagePresenter.display(songName);
                }
            }
        }
        languagePresenter.display("\n");
    }
}
