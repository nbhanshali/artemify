package com.artemifyMusicStudio.controller.queueServiceCommand;

import android.widget.EditText;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.entity.Song;
import com.presenters.LanguagePresenter;
import com.useCase.Queue;

public class RemoveFromQueueCommand extends QueueServiceCommand{

    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;
    private final EditText inputSongIndex;


    public RemoveFromQueueCommand(ActivityServiceCache activityServiceCache,
                               LanguagePresenter languagePresenter, Queue queueService,
                                  EditText inputSongIndex) {
        super(queueService);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
        this.inputSongIndex = inputSongIndex;
    }

    @Override
    public void execute() {
        int songIndex = Integer.parseInt(inputSongIndex.getText().toString());

        if(songIndex <= activityServiceCache.getQueueManager().getUpcomingSongs().size()){
            int songID = activityServiceCache.getQueueManager().getUpcomingSongs().get(songIndex);
            String songName = activityServiceCache.getSongManager().getSongName(songID);
            activityServiceCache.getQueueManager().removeFromQueue(songID);
            languagePresenter.display(songName + " has been removed from the queue.");
        } else {
            languagePresenter.display("There is no song at your given index.");
        }
    }
}
