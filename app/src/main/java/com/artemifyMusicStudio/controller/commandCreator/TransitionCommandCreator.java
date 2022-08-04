package com.artemifyMusicStudio.controller.commandCreator;

import android.view.View;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;
import com.artemifyMusicStudio.controller.transitionCommand.CreateAccountCommand;
import com.artemifyMusicStudio.controller.transitionCommand.EnableAdminCommand;
import com.artemifyMusicStudio.controller.transitionCommand.ExitPageCommand;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeAdminPageCommand;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeLogInCommand;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeNewPlaylistPageCommand;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeProfileAndSettingPageCommand;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeSearchPageCommand;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeUploadSongPageCommand;
import com.artemifyMusicStudio.controller.transitionCommand.StartLogInCommand;

/**
 *  A transition command factory to manufacture commands that are in transition command  category
 */
public class TransitionCommandCreator implements SimpleButtonCommandCreator {
    private final ActivityServiceCache activityServiceCache;

    /**
     *  A constructor of the transitionCommandCreator
     * @param activityServiceCache a ActivityServiceCache Object
     */
    public TransitionCommandCreator(ActivityServiceCache activityServiceCache){
        this.activityServiceCache = activityServiceCache;
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
            case PROFILE_AND_SETTING:
                return new InvokeProfileAndSettingPageCommand(this.activityServiceCache);
            case INVOKE_CREATE_NEW_PLAYLIST:
                return new InvokeNewPlaylistPageCommand(this.activityServiceCache);
            case LOG_OUT:
                // TODO: Discuss with Tom
                return new InvokeLogInCommand(this.activityServiceCache);
            case EXIT_PAGE:
                return new ExitPageCommand(this.activityServiceCache);
            default:
                return null;
        }
    }
}
