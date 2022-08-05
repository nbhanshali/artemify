package com.gateway;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;

import com.artemifyMusicStudio.PageActivity;
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
     * @param currentPageActivity a PageActivity object
     */
    public SerGateway(AppCompatActivity currentPageActivity) {
        super(currentPageActivity);
    }

    /**
     * A method to save entities to a .ser file
     * @param fileName a String to represent the file name
     * @param entities a container that stores the entities to be saved
     * @throws IOException throw IOException
     */
    @Override
    public void saveToFile(String fileName, Object entities) throws IOException {
        FileOutputStream file = this.currentPageActivity.openFileOutput(fileName,
                Context.MODE_PRIVATE);
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
        FileInputStream file = this.currentPageActivity.openFileInput("Users.ser");
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
        FileInputStream file = this.currentPageActivity.openFileInput("Playlists.ser") ;
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
        FileInputStream file = this.currentPageActivity.openFileInput("Songs.ser");
        InputStream buffer = new BufferedInputStream(file);
        ObjectInput input = new ObjectInputStream(buffer);

        // serialize the Map
        SongEntityContainer songs = (SongEntityContainer) input.readObject();
        input.close();
        return songs;
    }

}
