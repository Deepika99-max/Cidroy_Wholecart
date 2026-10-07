package org.example.wholecart.pages;

import org.example.wholecart.actions.CommonActions;
import org.example.wholecart.utils.LocatorReader;
import org.openqa.selenium.By;

public class LoginPage {

    private static final String LOCATOR_FILE = "login.json";
    private final CommonActions actions;

    private final By username = LocatorReader.get(LOCATOR_FILE, "username");
    private final By password = LocatorReader.get(LOCATOR_FILE, "password");
    private final By loginButton = LocatorReader.get(LOCATOR_FILE, "loginButton");

    public LoginPage() {
        this.actions = new CommonActions();
    }

    public void enterUsername(String usernameValue) {
        actions.enterText(username, usernameValue);
    }

    public void enterPassword(String passwordValue) {
        actions.enterText(password, passwordValue);
    }

    public void clickLogin() {

        actions.click(loginButton);
    }

    public void login(String usernameValue, String passwordValue) {
        enterUsername(usernameValue);
        enterPassword(passwordValue);
        clickLogin();
    }
}