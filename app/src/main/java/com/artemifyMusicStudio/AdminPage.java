package com.artemifyMusicStudio;

import android.os.Bundle;
import android.widget.TextView;

import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;
import com.artemifyMusicStudio.controller.commandCreator.PopupCommandCreator;
import com.artemifyMusicStudio.controller.commandCreator.TransitionCommandCreator;

import java.util.ArrayList;
import java.util.List;

public class AdminPage extends PageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_page);

        //parse and updated service cache
        parseActivityServiceCache();
        this.activityServiceCache.setCurrentPageActivity(this);

//        // populate button
//        populateMenuCommandCreatorMap();
//        populateExitPageMenuItems();
//        populateIdMenuMap();
//        populateButtons();
//        //set user name
//        TextView tv = findViewById(R.id.user_name);
//        tv.setText(activityServiceCache.getUserID());
    }

    @Override
    protected void populateMenuCommandCreatorMap() {
        ArrayList<CommandItemType> tempList = new ArrayList<>(
                List.of(CommandItemType.DELETE_USER, CommandItemType.BAN_USER,
                        CommandItemType.UNBAN_USER, CommandItemType.QUIT_ADMIN_MODE,
                        CommandItemType.LOG_OUT)
        );
        menuCommandCreatorMap.put("TransitionCommandCreator", tempList);
        menuCommandCreatorMap.put("PopupCommandCreator", tempList);
        // TODO: add other (popup)commandcreators
    }

    @Override
    protected void populateIdMenuMap() {
        idMenuItemMap.put(CommandItemType.LOG_OUT, R.id.exit);
        idMenuItemMap.put(CommandItemType.QUIT_ADMIN_MODE, R.id.exitAdminMode);
        idMenuItemMap.put(CommandItemType.DELETE_USER, R.id.deleteUser);
        idMenuItemMap.put(CommandItemType.BAN_USER, R.id.banUser);
        idMenuItemMap.put(CommandItemType.UNBAN_USER, R.id.unbanUser);
        idMenuItemMap.put(CommandItemType.GRANT_ADMIN_RIGHT, R.id.MakeAdmin);
        // TODO: add other commands that are not implemented yet
    }

    @Override
    protected SimpleButtonCommandCreator getSimpleOnClickCommandCreator(String creatorType) {
        switch (creatorType) {
            case "TransitionCommandCreator":
                return new TransitionCommandCreator(this.activityServiceCache);
            case "PopupCommandCreator" :
                return new PopupCommandCreator(this.activityServiceCache);
            default:
                return null;
        }
    }

    @Override
    protected void populateExitPageMenuItems() {
        exitPageMenuItems.add(CommandItemType.LOG_OUT);
        exitPageMenuItems.add(CommandItemType.QUIT_ADMIN_MODE);
    }
}