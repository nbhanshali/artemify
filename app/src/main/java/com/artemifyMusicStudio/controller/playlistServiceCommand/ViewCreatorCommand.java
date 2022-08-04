package com.artemifyMusicStudio.controller.playlistServiceCommand;

import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageType;
import com.useCase.PlaylistManager;

/**
 * A ViewCreatorCommand object to handle the user view the creator of playlist request
 */

public class ViewCreatorCommand extends PlaylistServiceCommand {
    private final ActivityServiceCache activityServiceCache;

    private final String creatorID;

    /**
     * Constructor for ViewCreatorCommand.
     *
     * @param activityServiceCache            a PageCreator Object
     * @param playlistServiceManager a UserAccess object
     * @param creatorID a String that represents the userID of this playlist's creator
     */
    public ViewCreatorCommand(ActivityServiceCache activityServiceCache,
                              PlaylistManager playlistServiceManager, String creatorID) {
        super(playlistServiceManager);
        this.activityServiceCache = activityServiceCache;
        this.creatorID = creatorID;
    }

    /**
     * Execute the ViewCreatorCommand by invoking UserDisplayPage of the creator
     */
    @Override
    public void execute() {
        this.activityServiceCache.setTargetUserID(this.creatorID);
        PageActivity targetUserDisplayPage = this.activityServiceCache.creat(PageType.USER_DISPLAY_PAGE);
        targetUserDisplayPage.invokes();
    }
}
