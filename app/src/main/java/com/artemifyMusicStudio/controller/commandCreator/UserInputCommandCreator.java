package com.artemifyMusicStudio.controller.commandCreator;

import android.view.View;
import android.widget.EditText;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;
import com.artemifyMusicStudio.controller.userInputCommand.AdminLogInCommand;
import com.artemifyMusicStudio.controller.userInputCommand.CreateNewPlaylistCommand;
import com.artemifyMusicStudio.controller.userInputCommand.CreateRegularAccountCommand;
import com.artemifyMusicStudio.controller.userInputCommand.RegularLogInCommand;
import com.artemifyMusicStudio.controller.userInputCommand.UploadSongCommand;

/**
 *  A input command factory to manufacture commands that are in input command  category which require user to provide inputs
 */
public class UserInputCommandCreator implements SimpleButtonCommandCreator {
    private final ActivityServiceCache activityServiceCache;
    private EditText inputUserName;
    private EditText inputPassword;

    private EditText inputSongName;
    private EditText inputMinute;
    private EditText inputSecond;
    private EditText inputLyrics;
    private boolean isPublic;

    private EditText inputPlaylistName;
    private EditText inputDescription;


    public UserInputCommandCreator(ActivityServiceCache activityServiceCache, EditText inputUserName, EditText inputPassword){
        this.activityServiceCache = activityServiceCache;
        this.inputUserName = inputUserName;
        this.inputPassword = inputPassword;
    }

    public UserInputCommandCreator(ActivityServiceCache activityServiceCache, EditText inputSongName,
                                   EditText inputMinute, EditText inputSecond, EditText inputLyrics,
                                   boolean isPublic){
        this.activityServiceCache = activityServiceCache;
        this.inputSongName = inputSongName;
        this.inputMinute = inputMinute;
        this.inputSecond = inputSecond;
        this.inputLyrics = inputLyrics;
        this.isPublic = isPublic;
    }

    public UserInputCommandCreator(ActivityServiceCache activityServiceCache,
                                   EditText inputPlaylistName, EditText inputDescription,
                                   boolean isPublic){
        this.activityServiceCache = activityServiceCache;
        this.inputPlaylistName = inputPlaylistName;
        this.inputDescription = inputDescription;
        this.isPublic = isPublic;
    }

    @Override
    public View.OnClickListener create(CommandItemType type) {
        switch (type){
            case REGULAR_LOG_IN_MODE:
                return new RegularLogInCommand(this.activityServiceCache, this.activityServiceCache.getLanguagePresenter(), this.activityServiceCache.getUserAcctServiceManager(),
                        this.inputUserName, this.inputPassword);
            case ADMIN_LOG_IN_MODE:
                return new AdminLogInCommand(this.activityServiceCache, this.activityServiceCache.getLanguagePresenter(), this.activityServiceCache.getUserAcctServiceManager(),
                        this.inputUserName, this.inputPassword);
            case UPLOAD_SONG:
                return new UploadSongCommand(this.activityServiceCache,
                        this.activityServiceCache.getLanguagePresenter(), this.inputSongName,
                        this.inputMinute, this.inputSecond, this.inputLyrics, this.isPublic);
            case CREATE_REGULAR_ACCOUNT:
                return new CreateRegularAccountCommand(this.activityServiceCache, this.activityServiceCache.getLanguagePresenter(), this.activityServiceCache.getUserAcctServiceManager(),
                        this.activityServiceCache.getPlaylistManager(), this.inputUserName, this.inputPassword);
            case CREATE_NEW_PLAYLIST:
                return new CreateNewPlaylistCommand(this.activityServiceCache,
                        this.activityServiceCache.getLanguagePresenter(), this.inputPlaylistName,
                        this.inputDescription, this.isPublic);
            default:
                return null;
        }
    }
}
