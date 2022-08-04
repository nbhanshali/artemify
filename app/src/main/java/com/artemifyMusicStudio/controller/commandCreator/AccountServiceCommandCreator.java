package com.artemifyMusicStudio.controller.commandCreator;

import com.artemifyMusicStudio.controller.accountServiceCommand.*;
import com.artemifyMusicStudio.controller.SimpleCommandCreator;
import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.ActivityServiceCache;
import com.presenters.LanguagePresenter;
import com.useCase.PlaylistManager;
import com.useCase.SongManager;
import com.useCase.UserAccess;


/**
 * A concrete creator class to create AccountServiceCommand Object
 *
 * ====== IMPORTANT NOTICE =====:
 *  If you add a new command under the AccountServiceCommand package (i.e. adding a new command class under
 *  Controller.CommandController.AccountServiceCommand folder), you should do the following
 *
 *      1. make the new command class inherit this interface. Namely, extends AccountServiceCommand
 *
 *      2. update the AccountServiceCommandCreator.create() method in the AccountServiceCommandCreator class stored in
 *      Controller.Creator folder. This will ensure your new added command get created properly. Otherwise, you will
 *      not see the new command appear in the UI
 */
public class AccountServiceCommandCreator implements SimpleCommandCreator {

    private final LanguagePresenter languagePresenter;
    private final PlaylistManager playlistServiceManager;
    private final SongManager songManager;
    private final ActivityServiceCache activityServiceCache;
    private final UserAccess acctServiceManager;
    private final String userID;

    /**
     * Constructor of AccountServiceCommandCreator
     * @param activityServiceCache a PageCreator object
     */
    public AccountServiceCommandCreator(ActivityServiceCache activityServiceCache){
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = this.activityServiceCache.getLanguagePresenter();
        this.playlistServiceManager = this.activityServiceCache.getPlaylistManager();
        this.songManager = this.activityServiceCache.getSongManager();
        this.acctServiceManager = this.activityServiceCache.getUserAcctServiceManager();
        this.userID = this.activityServiceCache.getUserID();
    }

    /**
     *
     * @param type an Enum in MenuItemType
     * @return a concrete command object that corresponding to the menu item type
     *
     * ====Important Notice====:
     * Every time when you add a new command controller that inherits AccountServiceCommand, you need to update this
     * method so that the AccountServiceCommandCreator can create that specific command
     */
    public AccountServiceCommand create(CommandItemType type){
        switch (type) {
            case ADMIN_LOG_IN_MODE:
                return new AdminLogInCommand(this.activityServiceCache,this.languagePresenter,this.acctServiceManager);
            case VIEW_LOGIN_HISTORY:
                return new ViewLoginHistoryCommand(this.languagePresenter, this.acctServiceManager, this.userID);
            case VIEW_PRIVATE_SONGS:
                return new ViewPrivateSongsCommand(this.acctServiceManager, this.languagePresenter,
                        this.playlistServiceManager, this.songManager, this.userID);
            case VIEW_PRIVATE_PLAYLISTS:
                return new ViewPrivatePlaylistsCommand(this.acctServiceManager, this.languagePresenter,
                        this.playlistServiceManager, this.userID);
            case BAN_USER:
                return new BanUserCommand(this.languagePresenter, this.acctServiceManager);
            case UNBAN_USER:
                return new UnBanUserCommand(this.languagePresenter, this.acctServiceManager);
            case DELETE_USER:
                return new DeleteUserCommand(this.languagePresenter, this.acctServiceManager,
                        this.playlistServiceManager, this.songManager);
            case GRANT_ADMIN_RIGHT:
                return new MakeAdminUserCommand(this.languagePresenter, this.acctServiceManager);
            default:
                return null;
        }
    }
}
