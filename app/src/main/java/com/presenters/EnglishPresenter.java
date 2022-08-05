package com.presenters;

public class EnglishPresenter extends LanguagePresenter {

    /**
     * Constructor of EnglishPresenter
     * @param language a Language type
     */
    public EnglishPresenter(LanguageType language) {
        super(language);
    }

    /**
     * Display method to display an English string to the language of this Presenter
     * @param englishString a String written in english
     */
    @Override
    public void display(String englishString) {
        System.out.println(englishString);
    }

    @Override
    public String translateString(String english) {
        return english;
    }

}

