package com.artemifyMusicStudio.controller.queueServiceCommand;

import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.QueueDisplayPage;
import com.presenters.LanguagePresenter;

public class RemoveFromQueueCommand implements View.OnClickListener {

    private final ActivityServiceCache activityServiceCache;
    private final LanguagePresenter languagePresenter;
    private final EditText inputSongIndex;


    public RemoveFromQueueCommand(ActivityServiceCache activityServiceCache, EditText inputSongIndex) {
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = activityServiceCache.getLanguagePresenter();
        this.inputSongIndex = inputSongIndex;
    }

    @Override
    public void onClick(View v) {
        PageActivity currentPageActivity = activityServiceCache.getCurrentPageActivity();
        String text = inputSongIndex.getText().toString();
        try {
            int songIndex = Integer.parseInt(text);

            if(songIndex <= activityServiceCache.getQueueManager().getUpcomingSongs().size()){
                int songID = activityServiceCache.getQueueManager().getUpcomingSongs().get(songIndex);
                String songName = activityServiceCache.getSongManager().getSongName(songID);
                activityServiceCache.getQueueManager().removeFromQueue(songIndex);
                Intent it = new Intent(currentPageActivity, QueueDisplayPage.class);
                it.putExtra("cache", this.activityServiceCache);
                currentPageActivity.startActivity(it);
                String warningMsg =  this.languagePresenter.
                        translateString(songName + " has been removed from the queue.") ;
                Toast.makeText(currentPageActivity, warningMsg, Toast.LENGTH_LONG).show();
            } else {
                String warningMsg =  this.languagePresenter.
                        translateString("There is no song at your given index. ") ;
                Toast.makeText(currentPageActivity, warningMsg, Toast.LENGTH_LONG).show();
            }
        } catch (NumberFormatException e) {
            String warningMsg =  this.languagePresenter.
                    translateString("Input is not an integer, please enter a valid input.") ;
            Toast.makeText(currentPageActivity, warningMsg, Toast.LENGTH_LONG).show();
        }

    }
}
