package com.artemifyMusicStudio;

import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * An abstract Page Activity class serve as the super class of all page activities. It serves as an abstract
 * GUI interface to provide the following benefits
 *      1. A uniform interface to provide canonical methods for all page activities
 *      2. Re-usability for some standard methods to increase the efficiency of development of various page controllers
 *         when implementing Phase 1 and Phase 2 features
 *
 * =====Private Attribute=====:
 *  idMenuItemMap - a LinkedHashMap with the following mapping relationship:
 *                          set of  menuItemType -> set of available button id defined in activity_<pagename>.xml
 *                          This map is bijective.
 *
 *  menuCommandCreatorMap - a LinkedHashMap with the following mapping relationship:
 *                          set of available menu item command creator -> set of menuItemType
 *                          This map is surjective.
 *
 *  exitPageMenuItemsDescription - a ArrayList to store the corresponding Enum of menu items that,
 *                                 once the execution of
 *                                 these menu items are completed, it will trigger the exit of the page controller.
 *                                 Example would like Logout, BackToPreviousPage, etc...
 *
 *
 */

public abstract class PageActivity extends AppCompatActivity implements Serializable {
    protected final LinkedHashMap<CommandItemType, Integer> idMenuItemMap = new LinkedHashMap<>();
    protected final LinkedHashMap<String, ArrayList<CommandItemType>> menuCommandCreatorMap = new LinkedHashMap<>();
    protected final ArrayList<CommandItemType> exitPageMenuItems = new ArrayList<>();

    protected ActivityServiceCache activityServiceCache = new ActivityServiceCache();

    protected void parseActivityServiceCache(){
        this.activityServiceCache = (ActivityServiceCache) getIntent().getSerializableExtra("cache");
    }

    public void invokes(){}

    /**
     * A protected method that can be reused by a concrete page controller class to populate MenuItemMap
     */
    protected void populateButtons(){
        ArrayList<String> commandCreatorKeys = new ArrayList<>(this.menuCommandCreatorMap.keySet());
        for (String creatorType : commandCreatorKeys){
            ArrayList<CommandItemType> itemTypeList = this.menuCommandCreatorMap.get(creatorType);
            SimpleButtonCommandCreator currentCreator = this.getSimpleOnClickCommandCreator(creatorType);
            // Populate menu item if and only if there exists a command creator for it
            if (currentCreator != null && itemTypeList != null){
                for (CommandItemType itemType: itemTypeList){
                    Button button = findViewById(idMenuItemMap.get(itemType));
                    button.setOnClickListener(currentCreator.create(itemType));
                }
            }
        }
    }


    /**
     * A protected method to enforce a concrete page controller class to implement its specific way of getting
     * its menu command creators
     */
    protected abstract SimpleButtonCommandCreator getSimpleOnClickCommandCreator(String creatorType);

    protected abstract void populateIdMenuMap();

    /**
     * A protected method that can be reused by a concrete page controller class to populate menu command creator
     */
    protected abstract void populateMenuCommandCreatorMap();

    /**
     * A protected method to enforce a concrete page controller class to implement its specific
     * items that will trigger the exit of the page controller. Namely, the conditions that will terminate the call of
     * invokes()
     */
    @Deprecated
    protected abstract void populateExitPageMenuItems();

}
