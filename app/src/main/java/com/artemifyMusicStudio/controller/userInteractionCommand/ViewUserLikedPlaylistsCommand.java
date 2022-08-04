package com.artemifyMusicStudio.controller.userInteractionCommand;

import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.UserAccess;

import java.util.ArrayList;

/**
 * A ViewUserLikedPlaylistsCommand class to handle the view liked playlists request from a user
 *
 */
public class ViewUserLikedPlaylistsCommand extends UserInteractionCommand{

    private final LanguagePresenter languagePresenter;
    private final PlaylistManager playlistServiceManager;
    private final String userID;
    private final String viewedID;


    /** Constructor of ViewUserLikedPlaylistsCommand
     *
     * @param acctServiceManager a UserAccess object
     * @param languagePresenter a LanguagePresenter object
     * @param playlistServiceManager a PlaylistManager object
     * @param userID id of user performing the command
     * @param viewedID id of user the command is being performed on
     */
    public ViewUserLikedPlaylistsCommand(UserAccess acctServiceManager, LanguagePresenter languagePresenter,
                                    PlaylistManager playlistServiceManager, String userID, String viewedID){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
        this.playlistServiceManager = playlistServiceManager;
        this.userID = userID;
        this.viewedID = viewedID;
    }

    /**
     * Execute the view liked playlists command
     */
    @Override
    public void execute() {
        ArrayList<Integer> playlistIDs =
                this.acctServiceManager.getUserLikedPlaylistsIDs(this.viewedID);
        if (this.userID.equals(this.viewedID)){
            if (playlistIDs.size() == 0) {
                this.languagePresenter.display("You have not liked any playlists.");
            } else {
                this.languagePresenter.display("The following are your liked playlists:");
                for (int playlistID : playlistIDs) {
                    String creator_username = this.playlistServiceManager.getCreatorUsername(playlistID);
                    String playlist_name = this.playlistServiceManager.getPlaylistName(playlistID);
                    this.languagePresenter.display(playlist_name + ", created by " + creator_username);
                }
            }
        }
        else {
            if (playlistIDs.size() == 0) {
                this.languagePresenter.display(this.viewedID + " has not liked any playlists.");
            } else {
                this.languagePresenter.display("The following are " + this.viewedID + "'s liked playlists:");
                for (int playlistID : playlistIDs) {
                    String creator_username = this.playlistServiceManager.getCreatorUsername(playlistID);
                    String playlist_name = this.playlistServiceManager.getPlaylistName(playlistID);
                    this.languagePresenter.display(playlist_name + ", created by " + creator_username);
                }
            }
        }
        languagePresenter.display("\n");
    }
}
