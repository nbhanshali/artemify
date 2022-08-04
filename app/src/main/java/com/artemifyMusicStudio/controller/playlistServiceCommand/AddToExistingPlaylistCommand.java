package com.artemifyMusicStudio.controller.playlistServiceCommand;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.SongManager;
import com.useCase.UserAccess;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An AddToExistingPlaylistCommand class to add the currently viewed song to an existing playlist by user input
 */
public class AddToExistingPlaylistCommand extends PlaylistServiceCommand {
    private final LanguagePresenter languagePresenter;
    private final UserAccess accountServiceManager;
    private final SongManager songManager;
    private final String userID;
    private final int songID;

    /**
     * Constructor of AddToExistingCommand
     *
     * @param activityServiceCache a PageCreator object
     * @param languagePresenter a LanguagePresenter object
     * @param playlistServiceManager UserAccess UseCase object
     */
    public AddToExistingPlaylistCommand(ActivityServiceCache activityServiceCache, LanguagePresenter languagePresenter,
                                        PlaylistManager playlistServiceManager){
        super(playlistServiceManager);
        this.languagePresenter = languagePresenter;
        this.accountServiceManager = activityServiceCache.getUserAcctServiceManager();
        this.songManager = activityServiceCache.getSongManager();
        this.userID = activityServiceCache.getUserID();
        this.songID = Integer.parseInt(activityServiceCache.getTargetSongID());
    }

    /**
     * Execute the AddToExistingPlaylistCommand
     */
    @Override
    public void execute() {
        boolean executionIsComplete = false;
        while (!executionIsComplete) {
            try {
                // Show existing Playlist names for the user
                boolean available_playlists = displayExistingPlaylist();
                if (!available_playlists) {
                    executionIsComplete = true;
                }
                else {

                    // Input target Playlist
                    Scanner inputStream = new Scanner(System.in);
                    languagePresenter.display("Please enter the name of the playlist: ");
                    String playlistName = inputStream.nextLine();
                    Integer playlistID = this.playlistServiceManager.getUserOwnedPlaylistIDFromName(this.userID,
                            playlistName);
                    if (playlistID == null) {
                        languagePresenter.display("There is no playlist with the name " + playlistName +
                                ", please enter the correct name of the playlist");
                    } else if (!accountServiceManager.editPlaylistAccess(userID, playlistID)) {
                        this.languagePresenter.display("You don't have access to the playlist\n");
                        if (wantToCancel(languagePresenter)) {
                            executionIsComplete = true;
                        }
                    } else {
                        boolean songIsPublic = songManager.isPublic(songID);
                        // check if it is possible to add the song to the selected playlist
                        boolean succeed = playlistServiceManager.addableToPlaylist(playlistID, songIsPublic);
                        if (!succeed) {
                            languagePresenter.display("You cannot add a private song to a public playlist\n");
                            if (wantToCancel(languagePresenter)) {
                                executionIsComplete = true;
                            }
                        } else {
                            if (playlistServiceManager.getListOfSongsID(playlistID).contains(songID)) {
                                languagePresenter.display(
                                        "Already added! (⌒‿⌒) You can find this song in \uD83D\uDCBD " +
                                                songManager.getSongName(songID) + " \uD83D\uDCBD.\n");
                            } else {
                                playlistServiceManager.addToPlaylist(playlistID, songID);
                                languagePresenter.display(
                                        "Successfully added! (⌒‿⌒) You can now find this song in \uD83D\uDCBD " +
                                                songManager.getSongName(songID) + " \uD83D\uDCBD.\n");
                            }
                            executionIsComplete = true;

                        }
                    }
                }
            } catch (InputMismatchException e) {
                this.languagePresenter.display("Invalid input format, please try again.");
            }
        }
    }

    /**
     * a private helper method to display the existing playlist names for this user
     */
    private boolean displayExistingPlaylist(){
        String [] playlistType = {"Public", "Private"};
        int emp = 0;
        for (String playlist: playlistType){
            languagePresenter.display(playlist + " Playlist");
            ArrayList<Integer> playlistIDs =
                    new ArrayList<>(accountServiceManager.getListOfPlaylistsIDs(userID, playlist));
            if (playlist.equals("Public")) {
                playlistIDs.remove(Integer.valueOf(accountServiceManager.getUserPublicSongsID(userID)));
                playlistIDs.remove(Integer.valueOf(accountServiceManager.getUserFavouritesID(userID)));
            } else {
                playlistIDs.remove(Integer.valueOf(accountServiceManager.getUserPrivateSongsID(userID)));
            }
            if (playlistIDs.isEmpty()) {
                emp += 1;
            }
            ArrayList<String> names = playlistServiceManager.getListOfPlaylistNames(playlistIDs);
            languagePresenter.display(names + "\n");
        }
        if (emp == 2) {
            languagePresenter.display("You do not have any created playlists to add this song to. \n");
            return false;
        }
        return true;
    }
}
