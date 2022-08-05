package com.presenters;

public class ChinesePresenter extends LanguagePresenter {

    /**
     * Constructor for LanguagePresenter
     *
     * @param language the language used in the presenter
     */
    public ChinesePresenter(LanguageType language) {
        super(language);
    }

    /**
     * Display method to display a Mandarin string to the language of this Presenter
     * @param chineseString a String written in Chinese
     */
    @Override
    public void display(String chineseString) {
        System.out.println(chineseString);
    }

    @Override
    public String translateString(String otherLanguageString) {
        return translator.translate("","zh-CN", otherLanguageString);
    }
}
