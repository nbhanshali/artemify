package com.artemifyMusicStudio.controller.popupCommand;

import com.artemifyMusicStudio.controller.accountServiceCommand.AccountServiceCommand;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.SongManager;
import com.useCase.UserAccess;

import java.util.ArrayList;

/**
 * A ViewPrivateSongsCommand class to handle the view private songs request from a user
 *
 */
public class ViewPrivateSongsCommand extends AccountServiceCommand {

    private final LanguagePresenter languagePresenter;
    private final PlaylistManager playlistServiceManager;
    private final SongManager songManager;
    private final String userID;

    /** Constructor for ViewPrivateSongsCommand
     *
     * @param acctServiceManager a UserAccess object
     * @param languagePresenter a LanguagePresenter object
     * @param playlistServiceManager a PlaylistManager object
     * @param songManager a SongManager object
     * @param userID id of user performing the command
     */
    public ViewPrivateSongsCommand(UserAccess acctServiceManager, LanguagePresenter languagePresenter,
                                PlaylistManager playlistServiceManager, SongManager songManager,
                                String userID){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
        this.playlistServiceManager = playlistServiceManager;
        this.songManager = songManager;
        this.userID = userID;
    }

    /**
     * Execute the view private songs command
     */
    @Override
    public void execute() {
        int privateSongsPlaylistID = this.acctServiceManager.getUserPrivateSongsID(this.userID);
        ArrayList<Integer> songsIDs = this.playlistServiceManager.getListOfSongsID(privateSongsPlaylistID);
        ArrayList<String> songNames = this.songManager.getPlaylistSongNames(songsIDs);
        if (songNames.size() == 0) {
            this.languagePresenter.display("You have no private songs.");
        } else {
            this.languagePresenter.display("The following are your private songs:");
            for (String songName : songNames) {
                this.languagePresenter.display(songName);
            }
        } languagePresenter.display("\n");
    }
}
