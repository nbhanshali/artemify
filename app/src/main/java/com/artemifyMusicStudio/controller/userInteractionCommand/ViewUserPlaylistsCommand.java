package com.artemifyMusicStudio.controller.userInteractionCommand;

import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.UserAccess;

import java.util.ArrayList;

/**
 * A ViewUserPlaylistsCommand class to handle the view playlists request from a user
 *
 */
public class ViewUserPlaylistsCommand extends UserInteractionCommand{

    private final LanguagePresenter languagePresenter;
    private final PlaylistManager playlistServiceManager;
    private final String userID;
    private final String viewedID;

    /** Constructor of ViewUserPlaylistsCommand
     *
     * @param acctServiceManager a UserAccess object
     * @param languagePresenter a LanguagePresenter object
     * @param playlistServiceManager a PlaylistManager object
     * @param userID id of user performing the command
     * @param viewedID id of user the command is being performed on
     */
    public ViewUserPlaylistsCommand(UserAccess acctServiceManager, LanguagePresenter languagePresenter,
                                PlaylistManager playlistServiceManager,
                                String userID, String viewedID){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
        this.playlistServiceManager = playlistServiceManager;
        this.userID = userID;
        this.viewedID = viewedID;
    }

    /**
     * Execute the view playlists command
     */
    @Override
    public void execute() {
        ArrayList<Integer> playlistIDs =
                this.acctServiceManager.getListOfPlaylistsIDs(this.viewedID, "Public");
        ArrayList<String> playlistNames = this.playlistServiceManager.getListOfPlaylistNames(playlistIDs);
        if (this.userID.equals(this.viewedID)){
            if (playlistNames.size() == 0) {
                this.languagePresenter.display("You have no public playlists.");
            } else {
                this.languagePresenter.display("The following are your public playlists:");
                for (String playlistName : playlistNames) {
                    this.languagePresenter.display(playlistName);
                }
            }

        }
        else {
            if (playlistNames.size() == 0) {
                this.languagePresenter.display(this.viewedID + " has no playlists.");
            } else {
                this.languagePresenter.display("The following are " + this.viewedID + "'s playlists:");
                for (String playlistName : playlistNames) {
                    this.languagePresenter.display(playlistName);
                }
            }
        }
        languagePresenter.display("\n");
    }
}
