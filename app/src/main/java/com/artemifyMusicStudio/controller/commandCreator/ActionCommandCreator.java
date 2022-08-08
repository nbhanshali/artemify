package com.artemifyMusicStudio.controller.commandCreator;

import android.view.View;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;
import com.artemifyMusicStudio.controller.actionCommand.PlaySongCommand;

public class ActionCommandCreator implements SimpleButtonCommandCreator {

    private final ActivityServiceCache activityServiceCache;

    /**
     * Constructor of ActionCommandCreator
     * @param activityServiceCache a ActivityServiceCache object
     */
    public ActionCommandCreator(ActivityServiceCache activityServiceCache){
        this.activityServiceCache = activityServiceCache;
    }
    @Override
    public View.OnClickListener create(CommandItemType type) {
        if (type == CommandItemType.PLAY_SONG) {
            return new PlaySongCommand(activityServiceCache);
        }
        return null;
    }
}
