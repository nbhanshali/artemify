package com.artemifyMusicStudio.controller.searchCommand;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.SearchResultPage;
import com.presenters.LanguagePresenter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * An abstract SearchServiceCommand to provide unify helper methods for all its child search command
 */
public abstract class SearchServiceCommand extends SearchAndPushServiceCommand {
    protected final ActivityServiceCache activityServiceCache;
    protected final LanguagePresenter languagePresenter;
    protected String searchType = "";
    protected EditText InputTargetName;

    /**
     * Constructor of SearchServiceCommand
     * @param activityServiceCache a PageCreator object
     * @param userInputTargetName a EditText to capture the user input for search
     */
    public SearchServiceCommand(ActivityServiceCache activityServiceCache, EditText userInputTargetName){
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = activityServiceCache.getLanguagePresenter();
        this.InputTargetName = userInputTargetName;
    }

    /**
     * A template method to execute Search related command
     */
    @Override
    public void onClick(View view) {
        // get current page activity
        PageActivity currentPageActivity = this.activityServiceCache.getCurrentPageActivity();

        // Take input from User to get Search ids with the searched name
        String userInputSearchString = InputTargetName.getText().toString();
        ArrayList<String> targetEntityIDs = this.getTargetEntityIDs(userInputSearchString);

        // Check whether songs with the userInputSongName exits
        if (targetEntityIDs != null){
            HashMap<Integer, String> searchResults = this.populateSearchResult(targetEntityIDs);
            //this.viewSuccessfulSearchResult(userInputSearchString, searchResults);
            Intent it = new Intent(currentPageActivity, SearchResultPage.class);
            it.putExtra("cache", this.activityServiceCache);
            it.putExtra("searchType", this.searchType);
            it.putExtra("searchResults", searchResults);
            currentPageActivity.startActivity(it);
        }
        else{
            String warningMsg = this.languagePresenter.translateString(userInputSearchString +
                    " does not exists!");
            Toast.makeText(currentPageActivity, warningMsg, Toast.LENGTH_LONG).show();
        }
    }

    /**
     * A generic protected helper method to populate the search result based on the name that provided from the user
     * @return a HashMap stores the target entity ids of search result in a numeric order
     */
    protected HashMap<Integer, String> populateSearchResult(ArrayList<String> uniqueIDs){
        int numberOfSongs = uniqueIDs.size();
        HashMap<Integer, String> searchResultMap = new HashMap<>();
        for (int i = 0 ; i < numberOfSongs; i++){
            String uniqueID = uniqueIDs.get(i);
            searchResultMap.put(i+1, uniqueID);
        }
        searchResultMap.put(numberOfSongs+1, "Return to Search Page");
        return searchResultMap;
    }

    /**
     * A generic protected helper method to display searching result to user
     * @param searchString the name that user provided when he/she search
     * @param searchResults the hash map stores all search results
     */
    protected void displaySearchResult(String searchString,
                                       HashMap<Integer, String> searchResults){
        String headMsg = "These are all " + this.searchType + " with the name " + searchString +
                ". Please press the corresponding number shown below to view the specific option or " +
                "return to search page: ";
        this.languagePresenter.display(headMsg);
        StringBuilder viewResultMenuMsg = new StringBuilder();
        ArrayList<Integer> availableOptionNum = new ArrayList<>(searchResults.keySet());
        int exitOptionNum = availableOptionNum.get(availableOptionNum.size() - 1);
        for (Integer currOption: availableOptionNum){
            if (currOption == exitOptionNum){
                String exitOptionMsg =  "  " + currOption + ". " + searchResults.get(currOption);
                viewResultMenuMsg.append(exitOptionMsg);
            }
            else{
                String searchResultID = searchResults.get(currOption);
                String searchResultDescription = this.getSearchResultDescription(currOption,
                        searchString, searchResultID);
                searchResultDescription += "\n";
                viewResultMenuMsg.append(searchResultDescription);
            }
        }
        this.languagePresenter.display(viewResultMenuMsg.toString());
    }

    /**
     * A generic helper to allow use to view the search result
     * @param userInputSearchString the that user provided when he/she search
     * @param searchResults the numeric map to store target entity ids
     */
    protected void viewSuccessfulSearchResult(String userInputSearchString, HashMap<Integer, String> searchResults){
        boolean viewIsCompleted = false;
        while(!viewIsCompleted) {
            try {
                // Show the string representation of the search results and ask use to choose the song that
                // he or she wants to view
                displaySearchResult(userInputSearchString, searchResults);

                // Ask user to choose the specific result that he/she wants to view
                Scanner inputStream = new Scanner(System.in);
                int userViewSongOptionNum = inputStream.nextInt();
                ArrayList<Integer> availableOptionNum = new ArrayList<>(searchResults.keySet());
                int exitOptionNum = availableOptionNum.get(availableOptionNum.size() - 1);
                if (!availableOptionNum.contains(userViewSongOptionNum)) {
                    this.languagePresenter.display("Invalid option, please try again.");
                }
                else if (userViewSongOptionNum == exitOptionNum){
                    viewIsCompleted = true;
                }
                else {
                    String targetEntityID = searchResults.get(userViewSongOptionNum);
                    PageActivity songDisplayPage = this.getEntityDisplayPage(targetEntityID);
                    songDisplayPage.invokes();
                    viewIsCompleted = true;
                }
            }catch (InputMismatchException e){
                this.languagePresenter.display("Invalid input format, please try again.");
            }
        }
    }

    /**
     * A protected helper method to determine whether a user wants to search again or
     * go back to home page
     * @return false if user wants to search again and true if user wants to go back to
     *         home page
     */
    protected boolean selectFailSearchAction(){
        boolean searchCompletionFlag = false;
        boolean selectionIsCompleted = false;
        while(!selectionIsCompleted){
            try{
                this.languagePresenter.display("- press 1 to search " + this.searchType + " again.");
                this.languagePresenter.display("- press 2 to back to search page");
                // Ask user to choose the option that he/she wants
                Scanner inputStream = new Scanner(System.in);
                int userOptionNum = inputStream.nextInt();
                switch(userOptionNum){
                    case 1:
                        selectionIsCompleted = true;
                        break;
                    case 2:
                        selectionIsCompleted = true;
                        searchCompletionFlag = true;
                        break;
                    default:
                        this.languagePresenter.display("Invalid option, please try again.");
                }
            }catch (InputMismatchException e){
                this.languagePresenter.display("Invalid input format, please try again.");
            }
        }
        return searchCompletionFlag;
    }

    /**
     * A protected abstract method to force child search commands to define their own way to
     * retrieve the target entity ids based on the search name string
     * @param searchString a String that user typed in to search
     * @return a list stores all searched entity ids in String
     */
    protected abstract ArrayList<String> getTargetEntityIDs (String searchString);

    /**
     * A protected abstract method to force child search commands to define their own description of the search results
     * @param index a integer index
     * @param searchString a String for search
     * @param targetEntityID a String for target entity id
     * @return a String of search result description
     */

    protected abstract String getSearchResultDescription(int index, String searchString, String targetEntityID);

    /**
     * A protected abstract method to force child search commands to define their way to get the entity display page
     * that the user can view the result that he/she wants
     *
     * @param targetEntityID a String for target entity id
     * @return a PageCreator object to represent the entity display page
     */
    protected abstract PageActivity getEntityDisplayPage(String targetEntityID);

}
