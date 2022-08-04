package com.artemifyMusicStudio.controller.popupCommand;

import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.SongManager;
import com.useCase.UserAccess;

import java.util.ArrayList;

/**
 * A ViewUserFavouritesCommand class to handle the view favourites request from a user
 *
 */
public class ViewUserFavouritesCommand extends UserInteractionCommand {

    private final LanguagePresenter languagePresenter;
    private final PlaylistManager playlistServiceManager;
    private final SongManager songManager;
    private final String userID;
    private final String viewedID;

    /** Constructor of ViewUserFavouritesCommand
     *
     * @param acctServiceManager a UserAccess object
     * @param languagePresenter a LanguagePresenter object
     * @param userID id of user performing the command
     * @param viewedID id of user the command is being performed on
     */
    public ViewUserFavouritesCommand(UserAccess acctServiceManager, LanguagePresenter languagePresenter,
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
     * Execute the view favourites command
     */
    @Override
    public void execute() {
        int favouriteSongsPlaylistID = this.acctServiceManager.getUserFavouritesID(this.viewedID);
        ArrayList<Integer> favouritesIDs = this.playlistServiceManager.getListOfSongsID(favouriteSongsPlaylistID);
        if (this.userID.equals(this.viewedID)){
            if (favouritesIDs.size() == 0) {
                this.languagePresenter.display("You have not liked any songs.");
            } else {
                this.languagePresenter.display("The following are your favourite songs:");
                for (int favID: favouritesIDs) {
                    String creatorName = this.songManager.getSongArtist(favID);
                    String songName = this.songManager.getSongName(favID);
                    this.languagePresenter.display((songName + ", by " + creatorName));
                }
            }
        }
        else {
            if (favouritesIDs.size() == 0) {
                this.languagePresenter.display(this.viewedID + " has not liked any songs.");
            } else {
                this.languagePresenter.display("The following are " + this.viewedID + "'s favourite songs:");
                for (int favID: favouritesIDs) {
                    String creatorName = this.songManager.getSongArtist(favID);
                    String songName = this.songManager.getSongName(favID);
                    this.languagePresenter.display((songName + ", by " + creatorName));
                }
            }
        }
        languagePresenter.display("\n");
    }
}
