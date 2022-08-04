package com.artemifyMusicStudio.controller.accountServiceCommand;

import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.SongManager;
import com.useCase.UserAccess;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * A DeleteUserCommand class to handle delete user request from an admin user
 *
 */
public class DeleteUserCommand extends AccountServiceCommand {
    private final LanguagePresenter languagePresenter;
    private final PlaylistManager playlistManager;
    private final SongManager songManager;

    /**
     * Constructor of DeleteUserCommand class
     * @param acctServiceManager a UserAccess object
     * @param languagePresenter a LanguagePresenter object
     * @param playlistManager a PlaylistManager object
     * @param songManager a SongManager object
     */
    public DeleteUserCommand(LanguagePresenter languagePresenter, UserAccess acctServiceManager,
                             PlaylistManager playlistManager, SongManager songManager){
        super(acctServiceManager);
        this.languagePresenter = languagePresenter;
        this.playlistManager = playlistManager;
        this.songManager = songManager;
    }

    /**
     * Execute the delete user request from a admin user
     */
    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);

        this.languagePresenter.display("Enter the username of the user you wish to delete: ");
        String username = in.nextLine();
        if (this.acctServiceManager.exists(username)) {
            if (this.acctServiceManager.findUser(username).getIsAdmin()) {
                this.languagePresenter.display("Invalid action. You cannot delete admins.");
            }
            else{
                ArrayList<String> userOwnPlaylistsIds =
                        this.acctServiceManager.getListOfAllPlaylistIDsFromUser(username);
                ArrayList<String> userCreatedSongsIds = this.songManager.getStringSongIDsFromCreator(username);
                this.playlistManager.deletePlaylistsByIDs(userOwnPlaylistsIds);
                this.songManager.deleteSongsByIDs(userCreatedSongsIds);
                this.acctServiceManager.delete(username);
                this.languagePresenter.display("Successfully deleted");
            }
        }
        else{
            this.languagePresenter.display("User does not exist");
        }
    }
}
