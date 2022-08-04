package com.gateway;

import com.useCase.PlaylistEntityContainer;
import com.useCase.SongEntityContainer;
import com.useCase.UserEntityContainer;

import java.io.IOException;

/**
 * An abstract Gateway class that handles system I/O.
 */

public abstract class IGateway implements SimpleGateway {

    protected final String filePath;

    /**
     * Constructor for IGateway
     * @param filePath the directory path of the file
     */
    public IGateway(String filePath) {
        this.filePath = filePath;
    }

    /**
     * An abstract method to save entities to a file
     * @param entities a container that stores the entities to be saved
     * @param fileName the fileName that will be saved
     * @throws IOException throw IOException
     */
    public abstract void saveToFile(Object entities, String fileName) throws IOException;

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

}
