package com.presenters;

public class SpanishPresenter extends LanguagePresenter {

    /**
     * Constructor for LanguagePresenter
     *
     * @param language the language used in the presenter
     */
    public SpanishPresenter(LanguageType language) {
        super(language);
    }

    /**
     * Display method to display a Mandarin string to the language of this Presenter
     * @param spanishString a String written in Spanish
     */
    @Override
    public void display(String spanishString) {
        System.out.println(spanishString);
    }

    @Override
    public String translateString(String otherLanguageString) {
        return translator.translate("","es", otherLanguageString);
    }
}
