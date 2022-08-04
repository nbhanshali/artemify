package com.gateway;

import com.useCase.PlaylistEntityContainer;
import com.useCase.SongEntityContainer;
import com.useCase.UserEntityContainer;

import java.io.*;


/**
 * A Gateway class that handles .ser files.
 */

public class SerGateway extends IGateway{

    /**
     * Constructor for SerGateway
     * @param filePath the directory path of the file
     */
    public SerGateway(String filePath) {
        super(filePath);
    }

    /**
     * A method to save entities to a .ser file
     *
     * @param entities a container that stores the entities to be saved
     * @param fileName fileName the fileName that will be saved
     * @throws IOException throw IOException
     */
    @Override
    public void saveToFile(Object entities, String fileName) throws IOException {
        String fullFilePath = this.filePath + fileName;
        OutputStream file = new FileOutputStream(fullFilePath);
        OutputStream buffer = new BufferedOutputStream(file);
        ObjectOutput output = new ObjectOutputStream(buffer);

        // serialize the Map
        output.writeObject(entities);
        output.close();
    }

    /**
     * A method that read user entities from a .ser file
     *
     * @return a UserContainer class object
     * @throws IOException            throw IOException
     * @throws ClassNotFoundException throw ClassNotFoundException
     */
    @Override
    public UserEntityContainer readUsersFromFile() throws IOException, ClassNotFoundException {
        String fullFilePath = this.filePath + "Users.ser";
        InputStream file = new FileInputStream(fullFilePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        UserEntityContainer users = (UserEntityContainer) input.readObject();
        input.close();
        return users;
    }

    /**
     * A method that read playlist entities from a .ser file
     *
     * @return a PlaylistContainer object
     * @throws IOException            throw IOException
     * @throws ClassNotFoundException throw ClassNotFoundException
     */
    @Override
    public PlaylistEntityContainer readPlaylistsFromFile() throws IOException, ClassNotFoundException {
        String fullFilePath = this.filePath + "Playlists.ser";
        InputStream file = new FileInputStream(fullFilePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        PlaylistEntityContainer playlists = (PlaylistEntityContainer) input.readObject();
        input.close();
        return playlists;
    }

    /**
     * A method that read song entities from a .ser file
     *
     * @return a SongContainer object
     * @throws IOException            throw IOException
     * @throws ClassNotFoundException throw ClassNotFoundException
     */
    @Override
    public SongEntityContainer readSongsFromFile() throws IOException, ClassNotFoundException {
        String fullFilePath = this.filePath + "Songs.ser";
        InputStream file = new FileInputStream(fullFilePath);
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        SongEntityContainer songs = (SongEntityContainer) input.readObject();
        input.close();
        return songs;
    }

}
