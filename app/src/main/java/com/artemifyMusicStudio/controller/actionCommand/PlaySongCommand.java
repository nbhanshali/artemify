package com.artemifyMusicStudio.controller.actionCommand;

import android.view.View;
import android.widget.Toast;

import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageActivity;
import com.presenters.LanguagePresenter;
import com.useCase.Queue;

public class PlaySongCommand implements View.OnClickListener{

    private final PageActivity songDisplayPage;
    private final LanguagePresenter languagePresenter;
    private final int songId;
    private final Queue queueManager;

    public PlaySongCommand(ActivityServiceCache activityServiceCache){
        this.songId = Integer.parseInt(activityServiceCache.getTargetSongID());
        songDisplayPage = activityServiceCache.getCurrentPageActivity();
        languagePresenter = activityServiceCache.getLanguagePresenter();
        queueManager = activityServiceCache.getQueueManager();
    }
    @Override
    public void onClick(View v) {
        if (queueManager.getNowPlaying() == songId){
            displayFailMsg(songDisplayPage);
        }
        else{
            displaySuccessfulMsg(songDisplayPage);
            queueManager.setNowPlaying(songId);
        }
    }

    private void displaySuccessfulMsg(PageActivity currentPageActivity) {
        String Msg =  languagePresenter.translateString("You are playing the song!");
        Toast.makeText(currentPageActivity, Msg, Toast.LENGTH_LONG).show();
    }

    private void displayFailMsg(PageActivity currentPageActivity) {
        String Msg =  languagePresenter.translateString("You are already played the song");
        Toast.makeText(currentPageActivity, Msg, Toast.LENGTH_LONG).show();
    }
}
