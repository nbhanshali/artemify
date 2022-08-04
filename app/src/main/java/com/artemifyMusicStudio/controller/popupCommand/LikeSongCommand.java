package com.artemifyMusicStudio.controller.popupCommand;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.controller.musicServiceCommand.MusicServiceCommand;
import com.presenters.LanguagePresenter;
import com.useCase.SongManager;

/**
 * a LikeSongCommand class to like the song and add it to the Favorites playlist
 */
public class LikeSongCommand extends MusicServiceCommand {
    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;
    private final int songID;

    /**
     * Constructor of UploadSongCommand
     *
     * @param activityServiceCache a PageCreator object
     * @param languagePresenter a LanguagePresenter object
     * @param songManager UserAccess UseCase object
     */
    public LikeSongCommand(ActivityServiceCache activityServiceCache, LanguagePresenter languagePresenter,
                           SongManager songManager){
        super(songManager);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
        this.songID = Integer.parseInt(activityServiceCache.getTargetSongID());
    }

    /**
     * Execute the like song command
     */
    @Override
    public void execute() {
        int userFavouritesIDID = activityServiceCache.getUserAcctServiceManager().getUserFavouritesID(activityServiceCache.getUserID());
        if (activityServiceCache.getPlaylistManager().getListOfSongsID(userFavouritesIDID).contains(songID)) {
            languagePresenter.display(
                    "Already liked! (＾▽＾) You can find this song in your Favourites playlist.\n");
        } else {
            activityServiceCache.getSongManager().likeSong(songID);
            activityServiceCache.getPlaylistManager().addToPlaylist(userFavouritesIDID, songID);
            languagePresenter.display("Liked! (＾▽＾) You can now find this song in your Favourites playlist.\n");
        }
    }
}
