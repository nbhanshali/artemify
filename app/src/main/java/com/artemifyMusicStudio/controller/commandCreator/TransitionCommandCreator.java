package com.artemifyMusicStudio.controller.commandCreator;

import android.view.View;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;
import com.artemifyMusicStudio.controller.transitionCommand.CreateAccountCommand;
import com.artemifyMusicStudio.controller.transitionCommand.EnableAdminCommand;
import com.artemifyMusicStudio.controller.transitionCommand.ExitPageCommand;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeLogOutCommand;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeNewPlaylistPageCommand;
import com.artemifyMusicStudio.controller.transitionCommand.InvokePlaylistDisplayPage;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeProfileAndSettingPageCommand;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeSearchPageCommand;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeSongDisplayPage;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeUploadSongPageCommand;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeUserDisplayPage;
import com.artemifyMusicStudio.controller.transitionCommand.StartLogInCommand;

/**
 *  A transition command factory to manufacture commands that are in transition command  category
 */
public class TransitionCommandCreator implements SimpleButtonCommandCreator {
    private final ActivityServiceCache activityServiceCache;
    private String targetID = "";

    /**
     *  A constructor of the transitionCommandCreator
     * @param activityServiceCache a ActivityServiceCache Object
     */
    public TransitionCommandCreator(ActivityServiceCache activityServiceCache){
        this.activityServiceCache = activityServiceCache;
    }

    /**
     * A setter to set the targetID
     * @param targetID a string of targetID
     */
    public void setTargetID(String targetID) {
        this.targetID = targetID;
    }

    @Override
    public View.OnClickListener create(CommandItemType type) {
        switch (type){
            case LOG_IN:
                return new StartLogInCommand(this.activityServiceCache);
            case ACCOUNT_CREATION:
                return new CreateAccountCommand(this.activityServiceCache);
            case INVOKE_SEARCH:
                return new InvokeSearchPageCommand(this.activityServiceCache);
            case ENABLE_ADMIN_MODE:
                return new EnableAdminCommand(this.activityServiceCache);
            case INVOKE_SONG_UPLOAD:
                return new InvokeUploadSongPageCommand(this.activityServiceCache);
            case INVOKE_SONG_DISPLAY:
                return new InvokeSongDisplayPage(this.activityServiceCache, this.targetID);
            case INVOKE_PLAYLIST_DISPLAY:
                return new InvokePlaylistDisplayPage(this.activityServiceCache, this.targetID);
            case INVOKE_USER_DISPLAY:
                return new InvokeUserDisplayPage(this.activityServiceCache, this.targetID);
            case PROFILE_AND_SETTING:
                return new InvokeProfileAndSettingPageCommand(this.activityServiceCache);
            case INVOKE_CREATE_NEW_PLAYLIST:
                return new InvokeNewPlaylistPageCommand(this.activityServiceCache);
            case LOG_OUT:
                // TODO: Discuss with Tom
                return new InvokeLogOutCommand(this.activityServiceCache);
            case EXIT_PAGE:
                return new ExitPageCommand(this.activityServiceCache);
            default:
                return null;
        }
    }
}
