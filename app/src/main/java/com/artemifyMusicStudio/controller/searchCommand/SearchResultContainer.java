package com.artemifyMusicStudio.controller.searchCommand;

import java.io.Serializable;
import java.util.HashMap;

/**
 * A class act as a container to store the search result
 */
public class SearchResultContainer implements Serializable {
    private final HashMap<Integer, String> searchResults;

    /**
     * Constructor of Search Result Container
     * @param searchResults a HashMap stores raw search results
     */
    public SearchResultContainer(HashMap<Integer, String> searchResults){
        this.searchResults = searchResults;
    }

    /**
     * A getter to get the search results
     * @return this.searchResults
     */
    public HashMap<Integer, String> getSearchResults() {
        return searchResults;
    }
}
