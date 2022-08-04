package com.artemifyMusicStudio.controller.popupCommand;

import com.artemifyMusicStudio.controller.systemServiceCommand.SystemServiceCommand;
import com.presenters.LanguagePresenter;

/**
 * A ExitSystemCommand that allow user to terminate the entire program
 */
public class ExitProgramCommand extends SystemServiceCommand {
    private final LanguagePresenter languagePresenter;

    /**
     * Constructor of ExiProgramCommand
     * @param languagePresenter a LanguagePresenter object
     */
    public ExitProgramCommand(LanguagePresenter languagePresenter){
        super();
        this.languagePresenter = languagePresenter;
    }

    /**
     * Execute the request to terminate the entire program
     */
    @Override
    public void execute() {
        this.languagePresenter.display("Thank you for choosing Artemify, Goodbye!");
    }
}
