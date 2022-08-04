package com.presenters;

import java.io.Serializable;

public abstract class LanguagePresenter implements SimplePresenter, Serializable {

    protected final LanguageType language;

    /**
     * Constructor for LanguagePresenter
     * @param language the language used in the presenter
     */
    public LanguagePresenter(LanguageType language){
        this.language = language;
    }

    public abstract void display(String english);

    public abstract String translateString(String english);
    
}
