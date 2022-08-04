package com.artemifyMusicStudio.controller.accountServiceCommand;

import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.UserAccess;

import java.util.ArrayList;

/**
 * A ViewPrivatePlaylistsCommand class to handle the view private playlists request from a user
 *
 */
public class ViewPrivatePlaylistsCommand extends AccountServiceCommand{

    private final LanguagePresenter languagePresenter;
    private final PlaylistManager playlistServiceManager;
    private final String userID;

    /** Constructor of ViewPrivatePlaylistsCommand
     *
     * @param acctServiceManager a UserAccess object
     * @param languagePresenter a LanguagePresenter object
     * @param playlistServiceManager a PlaylistManager object
     * @param userID id of user performing the command
     */
    public ViewPrivatePlaylistsCommand(UserAccess acctServiceManager, LanguagePresenter languagePresenter,
                                    PlaylistManager playlistServiceManager,
                                    String userID){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
        this.playlistServiceManager = playlistServiceManager;
        this.userID = userID;
    }

    /**
     * Execute the view private playlists command
     */
    @Override
    public void execute() {
        ArrayList<Integer> playlistIDs =
                this.acctServiceManager.getListOfPlaylistsIDs(this.userID, "Private");
        ArrayList<String> playlistNames = this.playlistServiceManager.getListOfPlaylistNames(playlistIDs);
        if (playlistNames.size() == 0) {
            this.languagePresenter.display("You have no private playlists.");
        } else {
            this.languagePresenter.display("The following are your private playlists:");
            for (String playlistName : playlistNames) {
                this.languagePresenter.display(playlistName);
            }
        } languagePresenter.display("\n");
    }
}
