package com.artemifyMusicStudio.controller.popupCommand;

import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.PlaylistSongsDisplayPage;
import com.artemifyMusicStudio.UserDisplayPage;
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
        // Bring user to PlaylistSongsDisplayPage
        PageActivity currentPageActivity = this.activityServiceCache.getCurrentPageActivity();
        Intent it = new Intent(currentPageActivity, PlaylistSongsDisplayPage.class);
        it.putExtra("cache", this.activityServiceCache);
        currentPageActivity.startActivity(it);
    }

}
