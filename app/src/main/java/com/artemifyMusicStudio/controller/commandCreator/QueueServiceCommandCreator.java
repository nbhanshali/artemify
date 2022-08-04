package com.artemifyMusicStudio.controller.commandCreator;

import android.widget.EditText;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleCommand;
import com.artemifyMusicStudio.controller.SimpleCommandCreator;
import com.artemifyMusicStudio.controller.queueServiceCommand.PlayPreviousSongCommand;
import com.artemifyMusicStudio.controller.queueServiceCommand.RemoveFromQueueCommand;
import com.artemifyMusicStudio.controller.queueServiceCommand.RepeatSongInfCommand;
import com.artemifyMusicStudio.controller.queueServiceCommand.RepeatSongOnceCommand;
import com.artemifyMusicStudio.controller.queueServiceCommand.ShuffleQueueCommand;
import com.artemifyMusicStudio.controller.queueServiceCommand.SkipSongCommand;
import com.presenters.LanguagePresenter;
import com.useCase.Queue;

public class QueueServiceCommandCreator implements SimpleCommandCreator {

    private final LanguagePresenter languagePresenter;
    private final ActivityServiceCache activityServiceCache;
    private final Queue queueService;
    private final EditText inputSongIndex;

    public QueueServiceCommandCreator(ActivityServiceCache activityServiceCache, EditText inputSongIndex) {
        this.activityServiceCache = activityServiceCache;
        this.inputSongIndex = inputSongIndex;
        this.languagePresenter = this.activityServiceCache.getLanguagePresenter();
        this.queueService = this.activityServiceCache.getQueueManager();
    }

    @Override
    public SimpleCommand create(CommandItemType type) {
        switch (type) {
            case SHUFFLE_QUEUE:
                return new ShuffleQueueCommand(activityServiceCache, languagePresenter,
                        queueService);
            case SKIP_SONG:
                return new SkipSongCommand(activityServiceCache, languagePresenter, queueService);
            case REMOVE_FROM_QUEUE:
                return new RemoveFromQueueCommand(activityServiceCache, languagePresenter,
                       queueService, inputSongIndex);
            //case PLAY_PAUSE_SONG:
            //    return new PlayPauseSongCommand();
            case PLAY_PREVIOUS_SONG:
                return new PlayPreviousSongCommand(activityServiceCache, languagePresenter,
                        queueService);
            case REPEAT_SONG_ONCE:
                return new RepeatSongOnceCommand(activityServiceCache, languagePresenter,
                        queueService);
            case REPEAT_SONG_INF:
                return new RepeatSongInfCommand(activityServiceCache, languagePresenter,
                        queueService);
            default:
                return null;
        }
    }
}
