package com.artemifyMusicStudio.controller.commandCreator;

import com.artemifyMusicStudio.controller.SimpleCommand;
import com.artemifyMusicStudio.controller.SimpleCommandCreator;
import com.artemifyMusicStudio.controller.userInteractionCommand.*;
import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.SongManager;
import com.useCase.UserAccess;

/**
 * A concrete creator class to create UserInteractionCommand Object
 *
 * ====== IMPORTANT NOTICE =====:
 *  If you add a new command under the UserInteractionCommand package (i.e. adding a new command class under
 *  Controller.CommandController.UserInteractionCommand folder), you should do the following
 *
 *      1. make the new command class inherit this interface. Namely, extends UserInteractionCommand
 *
 *      2. update the UserInteractionCommandCreator.create() method in the UserInteractionCommandCreator class stored in
 *      Controller.Creator folder. This will ensure your new added command get created properly. Otherwise, you will
 *      not see the new command appear in the UI
 */
public class UserInteractionCommandCreator implements SimpleCommandCreator {

    private final LanguagePresenter languagePresenter;
    private final PlaylistManager playlistServiceManager;
    private final SongManager songManager;
    private final UserAccess acctServiceManager;
    private final String userID;
    private final String viewedID;

    /**
     * Constructor of UserInteractionCommandCreator
     * @param activityServiceCache a PageCreator object
     */
    public UserInteractionCommandCreator(ActivityServiceCache activityServiceCache) {
        this.languagePresenter = activityServiceCache.getLanguagePresenter();
        this.acctServiceManager = activityServiceCache.getUserAcctServiceManager();
        this.playlistServiceManager = activityServiceCache.getPlaylistManager();
        this.songManager = activityServiceCache.getSongManager();
        this.userID = activityServiceCache.getUserID();
        this.viewedID = activityServiceCache.getTargetUserID();

    }

    /**
     *
     * @param type a menu item type that stored in MenuItemType
     * @return a concrete system service command
     */
    @Override
    public SimpleCommand create(CommandItemType type) {
        switch (type){
            case FOLLOW_USER:
                return new FollowUserCommand(this.acctServiceManager, this.languagePresenter, this.userID,
                        this.viewedID);
            case UNFOLLOW_USER:
                return new UnfollowUserCommand(this.acctServiceManager, this.languagePresenter, this.userID,
                        this.viewedID);
            default:
                return null;
        }
    }
}
