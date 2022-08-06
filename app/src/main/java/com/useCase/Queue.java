package com.useCase;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * A class responsible for storing songs that have played, to be played, and is currently playing.
 *
 * NOTE: This use case class will be used for Queue Display Page, which will be implemented in Phase 2.
 */

public class Queue implements Serializable {
    /**
     * nowPlaying is an integer representing the ID of the song that is currently being played.
     */
    private int nowPlaying = 0;

    /**
     * upcomingSongs stores a list of song IDs that the queue consists of, in the order that the songs will be played
     */
    private ArrayList<Integer> upcomingSongs = new ArrayList<>();

    /**
     * recentlyPlayedSongs stores a list of song IDs that have been recently played, in order starting from the song
     * that was most recently played.
     */
    private ArrayList<Integer> recentlyPlayedSongs = new ArrayList<>();

    /**
     * Constructor for a Queue Object
     * @param upcomings An ArrayList of all upcoming songs to be played
     * @param played An ArrayList of all songs previously played
     */
    public Queue(ArrayList<Integer> upcomings, ArrayList<Integer> played) {
        upcomingSongs = upcomings;
        recentlyPlayedSongs = played;
    }

    /**
     * An empty constructor to make the code run in Phase 1, this constructor will be removed when Phase 2
     * development starts
     */
    public Queue(){}

    /**
     * getter for getNowPlaying
     * @return the ID of the song that is currently playing
     */
    public int getNowPlaying() {
        return nowPlaying;
    }

    /**
     * setter for getNowPlaying
     * @param nowPlaying The ID of the song that is currently playing
     */
    public void setNowPlaying(int nowPlaying) {
        this.nowPlaying = nowPlaying;
    }

    /**
     * getter for upcomingSongs
     * @return an ArrayList of all songs to be played.
     */
    public ArrayList<Integer> getUpcomingSongs() {
        return upcomingSongs;
    }

    /**
     * setter for upcomingSongs
     * @param upcomingSongs an ArrayList of all songs to be played
     */
    public void setUpcomingSongs(ArrayList<Integer> upcomingSongs) {
        this.upcomingSongs = upcomingSongs;
    }

    /**
     * getter for recentlyPlayedSongs
     * @return an ArrayList of all recently played songs
     */
    public ArrayList<Integer> getRecentlyPlayedSongs() {
        return recentlyPlayedSongs;
    }

    /**
     * setter for recentlyPlayedSongs
     * @param recentlyPlayedSongs an ArrayList of all recently played songs
     */
    public void setRecentlyPlayedSongs(ArrayList<Integer> recentlyPlayedSongs) {
        this.recentlyPlayedSongs = recentlyPlayedSongs;
    }

    /**
     * Add a song to the queue at the specified index
     * @param songID The ID of a song
     * @param index The position at which the song will be played.
     */
    public void addToQueue(int songID, int index) {
        getUpcomingSongs().add(index, songID);
    }

    /**
     * Remove a song from the queue.
     * @param songID The ID of a song
     */
    public void removeFromQueue(int songID) {
        getUpcomingSongs().remove(songID);
    }

    /**
     * Clear a queue by emptying all objects in the queue
     */
    public void clearQueue() {
        setUpcomingSongs(new ArrayList<>());
    }

    /**
     * Remove the song that is currently playing.
     */
    public void popFromQueue() {
        recentlyPlayedSongs.add(0, nowPlaying);
        nowPlaying = getUpcomingSongs().get(0);
        removeFromQueue(nowPlaying);
    }

    /**
     * Shuffle the songs in the queue
     */
    public void shuffleQueue() {
        Collections.shuffle(upcomingSongs);
    }

    public void addToQueueEnd(int songID){
        int length = upcomingSongs.size();
        addToQueue(songID, length);
    }
}
