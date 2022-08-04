package com.artemifyMusicStudio.controller.musicServiceCommand;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;
import com.useCase.SongManager;

/**
 * A ViewLyricsCommand to view the time created and the lyrics of the song
 */
public class ViewLyricsCommand extends MusicServiceCommand{

    private final LanguagePresenter languagePresenter;
    private final int songID;

    /**
     * Constructor of ViewLyricsCommand
     *
     * @param activityServiceCache a PageCreator object
     * @param languagePresenter a LanguagePresenter object
     * @param songManager UserAccess UseCase object
     */
    public ViewLyricsCommand(ActivityServiceCache activityServiceCache, LanguagePresenter languagePresenter,
                             SongManager songManager){
        super(songManager);
        this.languagePresenter = languagePresenter;
        this.songID = Integer.parseInt(activityServiceCache.getTargetSongID());
    }

    /**
     * Display the time created and lyrics of the song
     */
    @Override
    public void execute() {
        languagePresenter.display("The song is created in " + songManager.getSongDateTimeCreated(songID) + "\n");
        languagePresenter.display("The lyrics of the song is the following: ");
        languagePresenter.display("=======================================");
        languagePresenter.display("♪ " +songManager.getSongLyrics(songID) + " ♪");
        languagePresenter.display("=======================================\n");
    }
}
