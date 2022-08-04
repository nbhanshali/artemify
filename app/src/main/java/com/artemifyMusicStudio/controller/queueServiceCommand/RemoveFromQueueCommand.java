package com.artemifyMusicStudio.controller.queueServiceCommand;

import android.widget.EditText;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;
import com.useCase.Queue;

public class RemoveFromQueueCommand extends QueueServiceCommand{

    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;
    private final EditText inputSongIndex;


    public RemoveFromQueueCommand(ActivityServiceCache activityServiceCache,
                               LanguagePresenter languagePresenter, Queue queueService,
                                  EditText inputSongName) {
        super(queueService);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
        this.inputSongName = inputSongName;
    }

    @Override
    public void execute() {

    }
}
