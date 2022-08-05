package com.artemifyMusicStudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;

import java.util.ArrayList;
import java.util.List;

public class SongDisplayPage extends PageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_display_page);

        // parse and updated service cache
        parseActivityServiceCache();
        this.activityServiceCache.setCurrentPageActivity(this);

        // populate button
        populateMenuCommandCreatorMap();
        populateExitPageMenuItems();
        populateIdMenuMap();
        populateButtons();
        // TODO: extra setup
    }

    @Override
    protected SimpleButtonCommandCreator getSimpleOnClickCommandCreator(String creatorType) {
        return null;
    }

    @Override
    protected void populateIdMenuMap() {

    }

    @Override
    protected void populateMenuCommandCreatorMap() {
        ArrayList<CommandItemType> tempList1 = new ArrayList<>(
                List.of(CommandItemType.EXIT_PAGE)
        );
        ArrayList<CommandItemType> tempList2 = new ArrayList<>(
                List.of(CommandItemType.UPLOAD_SONG)
        );
        menuCommandCreatorMap.put("TransitionCommandCreator", tempList1);
        menuCommandCreatorMap.put("UserInputCommandCreator", tempList2);
    }

    @Override
    protected void populateExitPageMenuItems() {}
}