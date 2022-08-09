package com.artemifyMusicStudio.controller.userInputRequestCommand;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.PlaylistDisplayPage;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;

import java.sql.Timestamp;

/**
 * A CreateNewPlaylistCommand object to handle the user's create new playlist request
 */

public class CreateNewPlaylistCommand implements View.OnClickListener {
    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;
    private final EditText inputPlaylistName;
    private final EditText inputDescription;
    private final boolean isPublic;

    public CreateNewPlaylistCommand(ActivityServiceCache activityServiceCache,
                                    LanguagePresenter languagePresenter, EditText inputPlaylistName,
                                    EditText inputDescription, boolean isPublic){
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
        this.inputPlaylistName = inputPlaylistName;
        this.inputDescription = inputDescription;
        this.isPublic = isPublic;
    }

    @Override
    public void onClick(View v) {
        String playlistName = inputPlaylistName.getText().toString();
        String description = inputDescription.getText().toString();
        String user = activityServiceCache.getUserID();
        Timestamp dateTimeCreated = new Timestamp(System.currentTimeMillis());
        boolean success = extractValidData(playlistName, description);
        if (success){
            // add playlist
            PlaylistManager playlistManager = activityServiceCache.getPlaylistManager();
            String userID = activityServiceCache.getUserID();
            playlistManager.addPlaylist(playlistName, description, userID, dateTimeCreated, isPublic);
            int newPlaylistID = playlistManager.latestPlaylistID();
            activityServiceCache.getUserAcctServiceManager().addToUserPlaylist(userID,
                    newPlaylistID, isPublic);
            // set target playlist id
            activityServiceCache.setTargetPlaylistID(String.valueOf(newPlaylistID));
            // go to playlist display page
            goToPlaylistDisplayPage(playlistName);
        }
    }

    private void displaySuccessfulMsg(String playlistName, PageActivity currentPageActivity) {
        String Msg =  this.languagePresenter.translateString("You create the playlist "
                + playlistName + " successfully!");
        Toast.makeText(currentPageActivity, Msg, Toast.LENGTH_LONG).show();
    }

    private boolean extractValidData(String playlistName, String desc){
        String [] defaultName = {"Favourite", "My Songs", "Private Songs"};
        if (playlistName.length() == 0 || desc.length() == 0){
            String Msg =  this.languagePresenter.translateString("You does not fill all the " +
                    "blanks");
            Toast.makeText(activityServiceCache.getCurrentPageActivity(), Msg, Toast.LENGTH_LONG).show();
            return false;
        }else {
            for (String defaultPlaylist: defaultName){
                if (playlistName.equals(defaultPlaylist)){
                    String Msg =  this.languagePresenter.translateString("The entered " +
                            "name is the same as default playlists' name.");
                    Toast.makeText(activityServiceCache.getCurrentPageActivity(), Msg, Toast.LENGTH_LONG).show();
                    return false;
                }
            }
        }
        return true;
    }

    private void goToPlaylistDisplayPage(String playlistName){
        PageActivity currentPageActivity = activityServiceCache.getCurrentPageActivity();
        displaySuccessfulMsg(playlistName, currentPageActivity);
        currentPageActivity = activityServiceCache.getCurrentPageActivity();
        Intent it = new Intent(currentPageActivity, PlaylistDisplayPage.class);
        it.putExtra("cache", this.activityServiceCache);
        currentPageActivity.startActivity(it);
        currentPageActivity.finish();
    }
}
