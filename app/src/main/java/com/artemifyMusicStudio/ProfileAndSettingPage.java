package com.artemifyMusicStudio;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;
import com.artemifyMusicStudio.controller.commandCreator.PopupCommandCreator;
import com.artemifyMusicStudio.controller.commandCreator.TransitionCommandCreator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileAndSettingPage extends PageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_and_setting_page);

        parseActivityServiceCache();
        this.activityServiceCache.setCurrentPageActivity(this);

        populateMenuCommandCreatorMap();
        populateExitPageMenuItems();
        populateIdMenuMap();
        populateButtons();
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
        idMenuItemMap.put(CommandItemType.VIEW_FOLLOWERS, R.id.view_followers);
        idMenuItemMap.put(CommandItemType.VIEW_FOLLOWINGS, R.id.view_followings);
        idMenuItemMap.put(CommandItemType.VIEW_LOGIN_HISTORY, R.id.view_login_history);
        idMenuItemMap.put(CommandItemType.VIEW_USER_SONGS, R.id.view_user_songs);
        idMenuItemMap.put(CommandItemType.VIEW_PRIVATE_SONGS, R.id.view_private_songs);
        idMenuItemMap.put(CommandItemType.VIEW_USER_PLAYLISTS, R.id.view_user_playlists);
        idMenuItemMap.put(CommandItemType.VIEW_PRIVATE_PLAYLISTS, R.id.view_private_playlists);
        idMenuItemMap.put(CommandItemType.VIEW_USER_FAVORITES, R.id.view_user_favorites);
        idMenuItemMap.put(CommandItemType.VIEW_USER_LIKED_PLAYLISTS, R.id.view_user_liked_playlists);
        idMenuItemMap.put(CommandItemType.EXIT_PAGE, R.id.exit_page);
    }

    @Override
    protected void populateMenuCommandCreatorMap() {
        ArrayList<CommandItemType> tempList1 = new ArrayList<>(
                Arrays.asList(CommandItemType.VIEW_FOLLOWERS,
                        CommandItemType.VIEW_FOLLOWINGS,
                        CommandItemType.VIEW_LOGIN_HISTORY,
                        CommandItemType.VIEW_USER_SONGS,
                        CommandItemType.VIEW_PRIVATE_SONGS,
                        CommandItemType.VIEW_USER_PLAYLISTS,
                        CommandItemType.VIEW_PRIVATE_PLAYLISTS,
                        CommandItemType.VIEW_USER_FAVORITES,
                        CommandItemType.VIEW_USER_LIKED_PLAYLISTS)
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