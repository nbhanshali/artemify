package com.artemifyMusicStudio.controller.popupCommand;

import android.view.View;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.controller.playlistServiceCommand.PlaylistServiceCommand;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;

import java.util.ArrayList;

/**
 * A ViewPlaylistSongsCommand object to handle the user view all the songs in playlist request
 */

public class ViewPlaylistSongsCommand implements View.OnClickListener {

    private final ActivityServiceCache activityServiceCache;

    private final LanguagePresenter languagePresenter;
    private final String targetPlaylistID;

    /**
     * Constructor for ViewPlaylistCommand.
     *
     * @param activityServiceCache            a PageCreator Object
     * @param languagePresenter      a LanguagePresenter Object
     * @param playlistServiceManager a UserAccess object
     * @param targetPlaylistID a String that represents the ID of the playlist that user want to look at
     */
    public ViewPlaylistSongsCommand(ActivityServiceCache activityServiceCache, LanguagePresenter languagePresenter,
                                    PlaylistManager playlistServiceManager, String targetPlaylistID) {
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
        this.targetPlaylistID = targetPlaylistID;
    }

    /**
     * Execute LikePlaylistCommand to display all song names in the playlist.
     */
    @Override
    public void onClick(View view) {
        String playlistName = this.activityServiceCache.getPlaylistManager().
                getPlaylistName(Integer.parseInt(this.targetPlaylistID));
        String songDisplayMsg =  this.languagePresenter.translateString("Here are all the songs in" + playlistName + ":");
        Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
                songDisplayMsg, Toast.LENGTH_LONG).show();
        ArrayList<Integer> allSongsID = this.activityServiceCache.getPlaylistManager().getListOfSongsID
                (Integer.parseInt(this.targetPlaylistID));
        for (Integer songID: allSongsID){
            String songNameMsg =  this.languagePresenter.translateString(this.activityServiceCache.getSongManager().findSong(songID).getName());
            Toast.makeText(this.activityServiceCache.getCurrentPageActivity(),
                    songNameMsg, Toast.LENGTH_LONG).show();
        }
//        languagePresenter.display("\n");
    }

}
