package com.artemifyMusicStudio.controller.playlistServiceCommand;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.entity.Playlist;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.Queue;

import java.util.ArrayList;

/**
 * A PlayPlaylistCommand object to handle the user play all songs in playlist request
 */

public class PlayPlaylistCommand extends PlaylistServiceCommand {
    private final ActivityServiceCache activityServiceCache;

    private final LanguagePresenter languagePresenter;
    private final String playlistID;

    /**
     * Constructor for PlayPlaylistCommand.
     *
     * @param activityServiceCache            a PageCreator Object
     * @param languagePresenter      a LanguagePresenter Object
     * @param playlistServiceManager a UserAccess object
     * @param playlistID a String represents the playlist's id
     */
    public PlayPlaylistCommand(ActivityServiceCache activityServiceCache, LanguagePresenter languagePresenter,
                               PlaylistManager playlistServiceManager, String playlistID) {
        super(playlistServiceManager);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
        this.playlistID = playlistID;
    }


    /**
     * Execute PlayPlaylistCommand to add all songs in playlist to top of queue.
     * Will add some code to invoke QueueDisplayPage in phase 2.
     */
    @Override
    public void execute() {
        Queue queueManager = this.activityServiceCache.getQueueManager();
        Playlist currPlaylist = this.activityServiceCache.getPlaylistManager().findPlaylist(Integer.parseInt(this.playlistID));
        ArrayList<Integer> allSongsID = currPlaylist.getSongs();
        int counter = 0;
        for(Integer songID : allSongsID){
            queueManager.addToQueue(songID, counter);
            counter++;
        }
        this.languagePresenter.display("Added to your playing queue.\n");
        /*
        Takes user to QueueDisplayPage. Will add this part in phase 2.
        PageController queueDisplayPage = this.pageCreator.creat(PageType.QUEUE_DISPLAY_PAGE);
        queueDisplayPage.invokes();
        */
    }
}
