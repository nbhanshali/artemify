package com.presenters;

public class FrenchPresenter extends LanguagePresenter {

    /**
     * Constructor of EnglishPresenter
     * @param language a Language type
     */
    public FrenchPresenter(LanguageType language) {
        super(language);
    }

    /**
     * Display method to display an English string to the language of this Presenter
     * @param frenchString a String written in french
     */
    @Override
    public void display(String frenchString) {
        System.out.println(frenchString);
    }

    @Override
    public String translateString(String otherLanguageString) {
        return translator.translate("","fr", otherLanguageString);
    }

}

