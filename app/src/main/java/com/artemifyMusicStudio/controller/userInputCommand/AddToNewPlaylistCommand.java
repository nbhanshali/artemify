package com.artemifyMusicStudio.controller.userInputCommand;

import android.view.View;
import android.widget.EditText;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.PageType;
import com.artemifyMusicStudio.controller.playlistServiceCommand.PlaylistServiceCommand;
import com.artemifyMusicStudio.controller.transitionCommand.InvokeNewPlaylistPageCommand;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;

import java.sql.Timestamp;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * A AddToNewPlaylistCommand that will create a new playlist, then add the song to this newly created playlist
 */
public class AddToNewPlaylistCommand extends CreateNewPlaylistCommand {


    public AddToNewPlaylistCommand(ActivityServiceCache activityServiceCache,
                                   LanguagePresenter languagePresenter, EditText inputPlaylistName,
                                   EditText inputDescription, boolean isPublic) {
        super(activityServiceCache, languagePresenter, inputPlaylistName, inputDescription, isPublic);
    }
}
