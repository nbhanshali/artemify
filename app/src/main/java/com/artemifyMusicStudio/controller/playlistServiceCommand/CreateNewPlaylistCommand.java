package com.artemifyMusicStudio.controller.playlistServiceCommand;

import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageType;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;

import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A CreateNewPlaylistCommand object to handle the user's create new playlist request
 */

public class CreateNewPlaylistCommand extends PlaylistServiceCommand {
    private final ActivityServiceCache activityServiceCache;

    private final LanguagePresenter languagePresenter;
    private final String userID;

    /**
     * Constructor for CreateNewPlaylistCommand.
     *
     * @param activityServiceCache a PageCreator Object
     * @param languagePresenter a LanguagePresenter Object
     * @param userID a String that represents the ID of this user
     */
    public CreateNewPlaylistCommand(ActivityServiceCache activityServiceCache, LanguagePresenter languagePresenter,
                                    PlaylistManager playlistServiceManager, String userID) {
        super(playlistServiceManager);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
        this.userID = userID;
    }

    /**
     * Execute CreateNewPlaylistCommand by conducting the following actions
     *
     * 1. Ask user to provide the name of this new playlist
     * 2. Conduct playlist authentication with the following three criteria
     *      i. If the authentication fails because the user provides a name that is the same as default playlists' name
     *      (i.e. "Favourites", "My Songs", "Private Songs", ask the user to choose whether to try again or cancel.
     *      If user choose to try again, we will redo the steps from i. Otherwise, we will back to NewPlaylistPage.
     *
     *      ii. If user enters a valid playlist name, we will ask users to provide the description of the playlist,
     *      choose whether to set this playlist public or not and automatically generate the time this playlist is
     *      created. We will set this user to be the creator of this playlist and add this playlist to user's playlist
     *      collections.
     *
     *      iii. After the playlist is successfully created, print message to indicate the successful creation and
     *      invoke PlaylistDisplayPage of this playlist for the user.
     *
     */
    @Override
    public void execute() {
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
                    }
                    else{
                        this.languagePresenter.display("Back to New Playlist Page.");
                        PageActivity newPlaylistPage = this.activityServiceCache.creat(PageType.NEW_PLAYLIST_PAGE);
                        newPlaylistPage.invokes();
                    }
                }
                else {
                    this.languagePresenter.display("Please enter the description of this playlist: ");
                    String description = inputStream.nextLine();
                    Timestamp dateTimeCreated = new Timestamp(System.currentTimeMillis());

                    // Ask the user to determine the visibility of the new playlist
                    boolean isPublic = this.selectPlaylistVisibility();

                    // Add the playlist
                    this.playlistServiceManager.addPlaylist(playlistName, description, this.userID,
                            dateTimeCreated, isPublic);
                    Integer newPlaylistID = this.playlistServiceManager.latestPlaylistID();
                    this.activityServiceCache.getUserAcctServiceManager().addToUserPlaylist(this.userID,
                            newPlaylistID, isPublic);

                    // Assumed the new user will use English as the display language, so no updates for the Language
                    // presenter in here. This assumption should be re-examined in Phase 2 to allow more flexibility
                    // in GUI

                    // Create the PlaylistDisplayPage and invoke after the creation of this playlist is completed
                    // this.pageCreator.setUserID(this.userID);
                    this.languagePresenter.display("Successfully created! (⌒‿⌒)\n");
                    this.activityServiceCache.setTargetPlaylistID(String.valueOf(newPlaylistID));
                    PageActivity playlistDisplayPage = this.activityServiceCache.creat(PageType.PLAYLIST_DISPLAY_PAGE);
                    playlistDisplayPage.invokes();
                    executionIsComplete = true;
                }
            } catch (InputMismatchException e) {
                this.languagePresenter.display("Invalid input format, please try again.");
            }
        }
    }


    /**
     * Ask the user to choose the visibility of the playlist
     * @return a boolean to indicate whether the playlist is public or not
     */
    private boolean selectPlaylistVisibility(){
        boolean playlistIsPublic = true;
        boolean selectionIsCompleted = false;
        while(!selectionIsCompleted){
            try{
                this.languagePresenter.display("- press 1 to make the playlist public");
                this.languagePresenter.display("- press 2 to make the playlist private");
                // Ask user to choose the option that he/she wants
                Scanner inputStream = new Scanner(System.in);
                int userOptionNum = inputStream.nextInt();
                switch(userOptionNum){
                    case 1:
                        selectionIsCompleted = true;
                        break;
                    case 2:
                        selectionIsCompleted = true;
                        playlistIsPublic = false;
                        break;
                    default:
                        this.languagePresenter.display("Invalid option, please try again.");
                }
            }catch (InputMismatchException e){
                this.languagePresenter.display("Invalid input format, please try again.");
            }
        }
        return playlistIsPublic;
    }
}
