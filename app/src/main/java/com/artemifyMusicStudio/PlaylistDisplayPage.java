package com.artemifyMusicStudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;
import com.artemifyMusicStudio.controller.commandCreator.PopupCommandCreator;
import com.artemifyMusicStudio.controller.commandCreator.TransitionCommandCreator;

import java.net.CookieManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaylistDisplayPage extends PageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playlist_display_page);

        // parse and updated service cache
        parseActivityServiceCache();
        this.activityServiceCache.setCurrentPageActivity(this);


        TextView tv = findViewById(R.id.playlist_name_display);
        int playlistID = Integer.parseInt(activityServiceCache.getTargetPlaylistID());
        tv.setText(this.activityServiceCache.getPlaylistManager().getPlaylistName(playlistID));

        // populate button
        populateMenuCommandCreatorMap();
        populateExitPageMenuItems();
        populateIdMenuMap();
        populateButtons();
//        // set user name
//        TextView tv = findViewById(R.id.user_name);
//        tv.setText(activityServiceCache.getUserID());

    }

    @Override
    protected SimpleButtonCommandCreator getSimpleOnClickCommandCreator(String creatorType) {
        switch (creatorType){
            case "PopupCommandCreator":
                return new PopupCommandCreator(this.activityServiceCache);
            case "TransitionCommandCreator":
                return new TransitionCommandCreator(this.activityServiceCache);
            default:
                return null;
        }
    }

    @Override
    protected void populateIdMenuMap() {
        idMenuItemMap.put(CommandItemType.VIEW_PLAYLIST_SONGS, R.id.view_playlist_songs);
        idMenuItemMap.put(CommandItemType.VIEW_CREATOR, R.id.view_creator);
        idMenuItemMap.put(CommandItemType.LIKE_PLAYLIST, R.id.like_playlist);
        idMenuItemMap.put(CommandItemType.PLAY_PLAYLIST, R.id.play_playlist);
        idMenuItemMap.put(CommandItemType.EXIT_PAGE, R.id.exit);

    }

    @Override
    protected void populateMenuCommandCreatorMap() {
        ArrayList<CommandItemType> tempList1 = new ArrayList<>(
                Arrays.asList(CommandItemType.VIEW_PLAYLIST_SONGS,
                        CommandItemType.VIEW_CREATOR,
                        CommandItemType.LIKE_PLAYLIST,
                        CommandItemType.PLAY_PLAYLIST)
        );
        ArrayList<CommandItemType> tempList2 = new ArrayList<>(
                List.of(CommandItemType.EXIT_PAGE)
        );
        menuCommandCreatorMap.put("PopupCommandCreator", tempList1);
        menuCommandCreatorMap.put("TransitionCommandCreator", tempList2);
    }

    @Override
    protected void populateExitPageMenuItems() {
        this.exitPageMenuItems.add(CommandItemType.EXIT_PAGE);
    }
}