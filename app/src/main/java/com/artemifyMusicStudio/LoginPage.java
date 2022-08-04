package com.artemifyMusicStudio;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.annotation.NonNull;

import com.artemifyMusicStudio.controller.CommandItemType;
import com.artemifyMusicStudio.controller.SimpleButtonCommandCreator;
import com.artemifyMusicStudio.controller.commandCreator.UserInputCommandCreator;

import java.util.ArrayList;
import java.util.List;

public class LoginPage extends PageActivity {
    private CommandItemType loginCommandType = CommandItemType.REGULAR_LOG_IN_MODE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);

        // parse and updated service cache
        parseActivityServiceCache();
        this.activityServiceCache.setCurrentPageActivity(this);

        // populate button
        populateMenuCommandCreatorMap();
        populateExitPageMenuItems();
        populateIdMenuMap();
        populateButtons();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.login_option_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.admin_user:
                this.loginCommandType = CommandItemType.ADMIN_LOG_IN_MODE;
                populateMenuCommandCreatorMap();
                populateIdMenuMap();
                populateButtons();
                return true;
            case R.id.regular_user:
                this.loginCommandType = CommandItemType.REGULAR_LOG_IN_MODE;
                populateMenuCommandCreatorMap();
                populateIdMenuMap();
                populateButtons();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void populateIdMenuMap() {
        this.idMenuItemMap.put(this.loginCommandType, R.id.conduct_log_in);
    }

    @Override
    protected void populateMenuCommandCreatorMap() {
        ArrayList<CommandItemType> tempList1 = new ArrayList<>(
                List.of(this.loginCommandType)
        );
        menuCommandCreatorMap.put("UserInputCommandCreator", tempList1);
    }

    @Override
    protected SimpleButtonCommandCreator getSimpleOnClickCommandCreator(String creatorType){
        EditText inputUserName = findViewById(R.id.inputUserName);
        EditText inputPassword = findViewById(R.id.inputPassword);
        if ("UserInputCommandCreator".equals(creatorType)) {
            return new UserInputCommandCreator(this.activityServiceCache, inputUserName, inputPassword);
        }
        return null;
    }

    @Override
    protected void populateExitPageMenuItems() {}
}