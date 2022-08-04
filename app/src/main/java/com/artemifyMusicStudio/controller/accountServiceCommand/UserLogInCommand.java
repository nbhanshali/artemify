package com.artemifyMusicStudio.controller.accountServiceCommand;

import com.artemifyMusicStudio.PageActivity;
import com.artemifyMusicStudio.ActivityServiceCache;
import com.artemifyMusicStudio.PageType;
import com.presenters.LanguagePresenter;
import com.presenters.LanguageType;
import com.presenters.PresenterCreator;
import com.useCase.UserAccess;

import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public abstract class UserLogInCommand extends AccountServiceCommand {

    protected final ActivityServiceCache activityServiceCache;
    protected final LanguagePresenter languagePresenter;
    protected String userAccountType = "";

    /**
     * Constructor of UserLogInCommand
     *
     * @param acctServiceManager a UserAccess object
     */
    public UserLogInCommand(ActivityServiceCache activityServiceCache, LanguagePresenter languagePresenter,
                            UserAccess acctServiceManager) {
        super(acctServiceManager);
        this.activityServiceCache = activityServiceCache;
        this.languagePresenter = languagePresenter;
    }

    /**
     * A template to execute the command of RegularLogInCommand, AdminLogInCommand, etc....
     */
    @Override
    public void execute() {
        boolean executionIsCompleted = false;
        while (!executionIsCompleted){
            try {
                //Using Scanner to get Input from User through command line
                Scanner inputStream = new Scanner(System.in);

                // Take input from User
                this.languagePresenter.display("Please enter username: ");
                String userID = inputStream.nextLine();
                this.languagePresenter.display("Please enter password: ");
                String pwd = inputStream.nextLine();

                // User Authentication
                HashMap<String, Boolean> authenticationStatus = this.acctServiceManager.checkLogin(userID, pwd);
                boolean exists = authenticationStatus.get("Exists");
                boolean failForSecondLayerAuthentication = this.getSecondLayerAuthenticationStatus(authenticationStatus);

                if (exists){
                    if (failForSecondLayerAuthentication){this.secondLayerAuthenticationFailAction();}
                    else{this.logInSuccessfulAction(userID);}
                }
                else{
                    this.languagePresenter.display("Incorrect username or password, please try again.");
                    PageActivity loginPage = this.activityServiceCache.creat(PageType.LOG_IN_PAGE);
                    loginPage.invokes();
                }
                executionIsCompleted = true;
            }catch (InputMismatchException e){
                this.languagePresenter.display("Invalid input format, please try again.");
            }
        }
    }


    /**
     * A protected method to update the Language presenter and userID upon a successful login
     * @param userID a String to represent a
     */
    protected void setUpPageCreator(String userID){
        String userPreferredDisplayLanguage = this.acctServiceManager.getUserDisplayLanguage(userID);
        PresenterCreator presenterCreator = new PresenterCreator();
        LanguagePresenter userLanguagePresenter = presenterCreator.createLanguagePresenter(
                LanguageType.valueOf(userPreferredDisplayLanguage.toUpperCase())
        );
        this.activityServiceCache.setLanguagePresenter(userLanguagePresenter);
        this.activityServiceCache.setUserID(userID);
    }

    /**
     * A protected helper method to retrieve the page controller based on the user account type
     * @param userAccountType a String to represent the user account type
     * @return a Page controller object
     */
    protected PageActivity getLogInPageController(String userAccountType){
        switch (userAccountType){
            case "Regular":
                return this.activityServiceCache.creat(PageType.REGULAR_USER_HOME_PAGE);
            case "Admin":
                return this.activityServiceCache.creat(PageType.ADMIN_PAGE);
            default:
                return null;
        }
    }

    /**
     * A protected method to handle the action if login is successful
     */
    protected void logInSuccessfulAction(String userID){
        this.displaySuccessfulLogInMsg(userID);
        this.setUpPageCreator(userID);
        PageActivity userAccountHomePage = this.getLogInPageController(this.userAccountType);
        userAccountHomePage.invokes();
    }

    /**
     * While the first layer of log in authentication is always check whether userID and password are match, this
     * abstract protected helper allows the subclass to select their second layer of log in authentication.
     *
     * @param authenticationStatus a HashMap that contains all authentication status for a user
     * @return a boolean to indicate whether the account has the correct rights to log in with current userAccountType
     */
    protected abstract boolean getSecondLayerAuthenticationStatus(HashMap<String, Boolean> authenticationStatus);

    /**
     * A protected method to force the subclass to define their own way of displaying welcome message if the user
     * successful log in
     */
    protected abstract void displaySuccessfulLogInMsg(String userID);

    /**
     * A protected method to handle the action if the user fails for second layer authentication when logging in
     */
    protected abstract void secondLayerAuthenticationFailAction();
}
