package com.artemifyMusicStudio;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;

import com.presenters.LanguagePresenter;

import java.util.ArrayList;

public class ViewQueuePage extends PageActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result_page);

        // parse service cache, searchResult and searchType
        parseActivityServiceCache();
        this.activityServiceCache.setCurrentPageActivity(this);

        // get language presenter
        LanguagePresenter languagePresenter = this.activityServiceCache.getLanguagePresenter();

        // populate head message
        String headMsgEng = "Songs in Queue";
        String headMsg = languagePresenter.translateString(headMsgEng);
        TextView tv = findViewById(R.id.search_result_head_msg);
        tv.setText(headMsg);

        //populate search result buttons
        populateButtons();
    }

    @Override
    protected void populateButtons(){
        LanguagePresenter languagePresenter = this.activityServiceCache.getLanguagePresenter();
        LinearLayout searchResultDisplay = findViewById(R.id.search_result_display_layout);
        ArrayList<Integer> allSongsID = this.activityServiceCache.getQueueManager().getUpcomingSongs();
        int count = 1;
        for (Integer songID: allSongsID){
            String num = String.valueOf(count);
            String songName = activityServiceCache.getSongManager().getSongName(songID);
            String buttonDescription = languagePresenter.translateString( num + ". " + songName);
            Button button = new Button(this);
            button.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            button.setId(count);
            button.setText(buttonDescription);
            searchResultDisplay.addView(button);
            count += 1;
        }
        searchResultDisplay.setGravity(Gravity.CENTER);
    }

    /**
     * The getSimpleOnClickCommandCreator will not be used in SearchResultPage. Therefore,
     * no implementation in here
     *
     */
    @Override
    protected SimpleButtonCommandCreator getSimpleOnClickCommandCreator(String creatorType) {
        return null;
    }

    /**
     * The idMenuMap will not be used in SearchResultPage. No implementation here
     */
    @Override
    protected void populateIdMenuMap() {
    }

    /**
     * The menuCommandCreatorMap will not be used in SearchResultPage, no population in here
     */
    @Override
    protected void populateMenuCommandCreatorMap() {}

    @Override
    protected void populateExitPageMenuItems() {}


}