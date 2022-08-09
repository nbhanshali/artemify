package com.artemifyMusicStudio.controller.userInputRequestCommand;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.RegularUserHomePage;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.UserAccess;

import java.sql.Timestamp;
import java.util.Date;

public class CreateRegularAccountCommand implements View.OnClickListener {

    protected final ActivityServiceCache activityServiceCache;
    protected final LanguagePresenter languagePresenter;
    protected final UserAccess acctServiceManager;
    protected final PlaylistManager playlistServiceManager;
    protected final EditText inputUserName;
    protected final EditText inputPassword;

    /**
     * Constructor of CreateRegularAccountCommand
     * @param activityServiceCache a PageCreator object
     * @param languagePresenter a LanguagePresenter object
     * @param acctServiceManager a UserAccess object
     */
    public CreateRegularAccountCommand(ActivityServiceCache activityServiceCache, LanguagePresenter languagePresenter,
                                       UserAccess acctServiceManager, PlaylistManager playlistServiceManager,
                                       EditText inputUserName, EditText inputPassword) {
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
        this.acctServiceManager = acctServiceManager;
        this.playlistServiceManager = playlistServiceManager;
        this.inputUserName = inputUserName;
        this.inputPassword = inputPassword;
    }

    /**
     * Execute the CreateRegularAccount Command
     */
    @Override
    public void onClick(View view) {
        // Get user's input for username and password
        String username = inputUserName.getText().toString();
        String password = inputPassword.getText().toString();

        if (this.acctServiceManager.exists(username)){
            String warningMsg = this.languagePresenter.translateString("Username is already taken");
            Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
                    warningMsg, Toast.LENGTH_LONG).show();
        }
        else{
            this.activityServiceCache.setUserID(username);
            // Add user to entities
            this.acctServiceManager.addUser(username, password);
            // Populate default playlist
            this.createDefaultPlaylist(username);

            // Bring user to User Home Page
            PageActivity currentPageActivity = this.activityServiceCache.getCurrentPageActivity();
            Intent it = new Intent(currentPageActivity, RegularUserHomePage.class);
            it.putExtra("cache", this.activityServiceCache);
            currentPageActivity.startActivity(it);
        }
    }

    private void createDefaultPlaylist(String username){
        // create the default playlists for the new user
        Date date = new Date();
        long time = date.getTime();
        // create My Songs playlist and set the user's public songs ID to the newly created playlist's ID
        this.playlistServiceManager.addPlaylist("My Songs",
                "Your songs the world will listen!", username, new Timestamp(time), true);
        this.acctServiceManager.findUser(username).setMyPublicSongsPlaylistId(
                this.playlistServiceManager.latestPlaylistID());
        // create Private Songs playlist and set the user's public songs ID to the newly created playlist's ID
        this.playlistServiceManager.addPlaylist("Private Songs", "Unlisted soundtracks.",
                username, new Timestamp(time), false);
        this.acctServiceManager.findUser(username).setMyPrivateSongsPlaylistId(
                this.playlistServiceManager.latestPlaylistID());
        // create Favourites playlist and set the user's public songs ID to the newly created playlist's ID
        this.playlistServiceManager.addPlaylist("Favourites",
                "Songs you like will appear here!", username, new Timestamp(time), true);
        this.acctServiceManager.findUser(username).setFavoritesPlaylistId(
                this.playlistServiceManager.latestPlaylistID());
    }
}
