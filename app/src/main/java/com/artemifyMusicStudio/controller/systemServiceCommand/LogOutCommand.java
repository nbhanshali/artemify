package com.artemifyMusicStudio.controller.systemServiceCommand;

import com.presenters.LanguagePresenter;

/**
 * A command class to handle logout command
 */
public class LogOutCommand extends SystemServiceCommand {
    private final LanguagePresenter languagePresenter;

    /**
     * Constructor of the LogOutCommand class
     * @param languagePresenter a LanguagePresenter object
     */
    public LogOutCommand(LanguagePresenter languagePresenter){
        super();
        this.languagePresenter = languagePresenter;
    }

    /**
     * Conduct logout command by printing the logout message
     */
    public void execute(){
        this.languagePresenter.display("You are now logged out.\n");
    }
}
