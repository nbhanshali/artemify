package com.artemifyMusicStudio;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;
import com.artemifyMusicStudio.controller.commandCreator.PopupCommandCreator;
import com.artemifyMusicStudio.controller.commandCreator.QueueServiceCommandCreator;
import com.artemifyMusicStudio.controller.commandCreator.TransitionCommandCreator;
import com.useCase.PlaylistManager;
import com.useCase.Queue;
import com.useCase.SongManager;

import java.util.ArrayList;
import java.util.List;

public class ViewQueuePage extends PageActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_queue_page);

        // parse and updated service cache
        parseActivityServiceCache();
        this.activityServiceCache.setCurrentPageActivity(this);

        // populate button
        populateMenuCommandCreatorMap();
        populateExitPageMenuItems();
        populateIdMenuMap();
        populateButtons();

        LinearLayout songLists = findViewById(R.id.list_songs_display);
        // remove all existing songs in the lists to prevent redundancy
        songLists.removeAllViews();

        SongManager songManager = activityServiceCache.getSongManager();
        Queue queueManager = activityServiceCache.getQueueManager();
        ArrayList<Integer> allSongIDs = queueManager.getUpcomingSongs();
        int count = 1;
        for (Integer songID: allSongIDs) {
            String num = String.valueOf(count);
            String songName = songManager.getSongName(songID);
            String artistName = songManager.getSongArtist(songID);
            String displayName = num + ". " + songName;
            View oneSong = LayoutInflater.from(this).inflate(R.layout.one_song_display, null);
            // set song name for this song
            TextView songNameDisplay = oneSong.findViewById(R.id.display_song_name);
            songNameDisplay.setText(displayName);
            // set artist name for this song
            TextView artistNameDisplay = oneSong.findViewById(R.id.display_artist_name);
            artistNameDisplay.setText(artistName);
            // put this song in PlaylistSongsDisplay
            songLists.addView(oneSong);
        }
    }

    @Override
    protected SimpleButtonCommandCreator getSimpleOnClickCommandCreator(String creatorType) {
        switch (creatorType) {
            case "TransitionCommandCreator":
                return new TransitionCommandCreator(this.activityServiceCache);
            default:
                return null;
        }
    }

    @Override
    protected void populateIdMenuMap() {
        idMenuItemMap.put(CommandItemType.EXIT_PAGE, R.id.exit);
    }

    @Override
    protected void populateMenuCommandCreatorMap() {
        ArrayList<CommandItemType> tempList = new ArrayList<>(
                List.of(CommandItemType.EXIT_PAGE)
        );
        menuCommandCreatorMap.put("TransitionCommandCreator", tempList);
    }

    @Override
    protected void populateExitPageMenuItems() {
        this.exitPageMenuItems.add(CommandItemType.EXIT_PAGE);
    }
}
