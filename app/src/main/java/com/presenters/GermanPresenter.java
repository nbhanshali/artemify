package com.presenters;

public class GermanPresenter extends LanguagePresenter {

    /**
     * Constructor for LanguagePresenter
     *
     * @param language the language used in the presenter
     */
    public GermanPresenter(LanguageType language) {
        super(language);
    }

    /**
     * Display method to display a Mandarin string to the language of this Presenter
     * @param germanString a String written in German
     */
    @Override
    public void display(String germanString) {
        System.out.println(germanString);
    }

    @Override
    public String translateString(String otherLanguageString) {
        return translator.translate("","Gr", otherLanguageString);
    }
}
