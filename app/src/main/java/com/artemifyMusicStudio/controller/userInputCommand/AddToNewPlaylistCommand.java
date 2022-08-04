package com.artemifyMusicStudio.controller.userInputCommand;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.PageType;
import com.artemifyMusicStudio.controller.playlistServiceCommand.PlaylistServiceCommand;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;

import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A AddToNewPlaylistCommand that will create a new playlist, then add the song to this newly created playlist
 */
public class AddToNewPlaylistCommand extends PlaylistServiceCommand {
    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;
    private final String userID;
    private final int songID;

    /**
     * Constructor of AddToExistingCommand
     *
     * @param activityServiceCache            a PageCreator object
     * @param playlistServiceManager UserAccess UseCase object
     */
    public AddToNewPlaylistCommand(ActivityServiceCache activityServiceCache, PlaylistManager playlistServiceManager) {
        super(playlistServiceManager);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = activityServiceCache.getLanguagePresenter();
        this.userID = activityServiceCache.getUserID();
        this.songID = Integer.parseInt(activityServiceCache.getTargetSongID());
    }

    /**
     * execute by first creating the playlist, then add the song to this playlist
     */
    @Override
    public void execute() {
        // create new playlist
        int newPlaylistID = createPlaylist();
        if (newPlaylistID > 0){
            // add the song to this newly created playlist
            boolean songIsPublic = activityServiceCache.getSongManager().isPublic(songID);
            // check if it is possible to add the song to the selected playlist
            boolean succeed = playlistServiceManager.addableToPlaylist(newPlaylistID, songIsPublic);
            if (succeed){
                activityServiceCache.getPlaylistManager().addToPlaylist(newPlaylistID, songID);
                // direct to new created playlist page
                activityServiceCache.setTargetPlaylistID(String.valueOf(newPlaylistID));
                PageActivity playlistDisplayPage = activityServiceCache.creat(PageType.PLAYLIST_DISPLAY_PAGE);
                playlistDisplayPage.invokes();
            }
            else {
                languagePresenter.display("Addition denied. You cannot add private songs to public playlists.");
            }

        }
        else {
            languagePresenter.display("Playlist creation failed. Return back to previous page.");
        }
    }

    /**
     * Create the playlist
     * @return the playlistID if created successfully, and return -1 if failed and cancelled the process
     */
    private int createPlaylist() {
        boolean executionIsComplete = false;
        while (!executionIsComplete) {
            try {
                Scanner inputStream = new Scanner(System.in);
                this.languagePresenter.display("Please enter the name of this playlist: ");
                String playlistName = inputStream.nextLine();
                if (playlistName.equalsIgnoreCase("Favourite") ||
                        playlistName.equalsIgnoreCase("My Songs") ||
                        playlistName.equalsIgnoreCase("Private Songs")) {
                    this.languagePresenter.display("The entered name is the same as default playlists' name.");
                    if (wantToCancel(languagePresenter)) {
                        executionIsComplete = true;
                    } else {
                        return createPlaylist();
                    }
                } else {
                    this.languagePresenter.display("Please enter the description of this playlist: ");
                    String description = inputStream.nextLine();
                    Timestamp dateTimeCreated = new Timestamp(System.currentTimeMillis());
                    // read public attribute
                    this.languagePresenter.display("Enter 1 if you want the playlist to be public. " +
                            "Enter anything else to make it private.");
                    String optionVal = inputStream.nextLine();
                    boolean isPublic = optionVal.equals("1");
                    this.playlistServiceManager.addPlaylist(playlistName, description, this.userID,
                            dateTimeCreated, isPublic);
                    int newPlaylistID = this.playlistServiceManager.latestPlaylistID();
                    this.activityServiceCache.getUserAcctServiceManager().addToUserPlaylist(this.userID,
                            newPlaylistID, isPublic);

                    this.languagePresenter.display("Successfully created! (⌒‿⌒)\n");
                    executionIsComplete = true;
                    return newPlaylistID;
                }
            } catch (InputMismatchException e) {
                this.languagePresenter.display("Invalid input format, please try again.");
            }
        }
        return -1;
    }
}
