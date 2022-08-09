package com.gateway;

import androidx.appcompat.app.AppCompatActivity;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.useCase.PlaylistEntityContainer;
import com.useCase.SongEntityContainer;
import com.useCase.UserEntityContainer;

import java.io.IOException;

/**
 * An abstract Gateway class that handles system I/O.
 */

public abstract class IGateway implements SimpleGateway{

    protected AppCompatActivity currentPageActivity;

    /**
     * Constructor of IGateway
     * @param currentPageActivity a PageActivity object
     */
    public IGateway(AppCompatActivity currentPageActivity){
        this.currentPageActivity = currentPageActivity;
    }

    /**
     * An abstract method to save entities to a file
     * @param fileName a String to represent the file name
     * @param entities a container that stores the entities to be saved
     * @throws IOException throw IOException
     */
    public abstract void saveToFile(String fileName, Object entities) throws IOException;

    /**
     * An abstract method that read user entities from a file
     * @return a UserContainer class object
     * @throws IOException throw IOException
     * @throws ClassNotFoundException throw ClassNotFoundException
     */
    public abstract UserEntityContainer readUsersFromFile() throws IOException, ClassNotFoundException;

    /**
     * An abstract method that read playlist entities from a file
     * @return a PlaylistContainer object
     * @throws IOException throw IOException
     * @throws ClassNotFoundException throw ClassNotFoundException
     *
     */
    public abstract PlaylistEntityContainer readPlaylistsFromFile() throws IOException, ClassNotFoundException;

    /**
     * An abstract method that read song entities from a file
     * @return a SongContainer object
     * @throws IOException throw IOException
     * @throws ClassNotFoundException throw ClassNotFoundException
     */
    public abstract SongEntityContainer readSongsFromFile() throws IOException, ClassNotFoundException;

    /**
     * An abstract method that read activityServiceCache from a file
     * @return a ActivityServiceCache object
     * @throws IOException throw IOException
     * @throws ClassNotFoundException throw ClassNotFoundException
     */
    public abstract ActivityServiceCache readActivityServiceCacheFromFile() throws IOException, ClassNotFoundException;
}
