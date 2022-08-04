package com.artemifyMusicStudio;

import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;
import com.artemifyMusicStudio.controller.commandCreator.PopupCommandCreator;
import com.artemifyMusicStudio.controller.commandCreator.TransitionCommandCreator;

public class QueueDisplayPage extends PageActivity{

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
    protected void populateIdMenuMap() {

    }

    @Override
    protected void populateMenuCommandCreatorMap() {

    }

    @Override
    protected void populateExitPageMenuItems() {
        this.exitPageMenuItems.add(CommandItemType.EXIT_PAGE);
    }
}
