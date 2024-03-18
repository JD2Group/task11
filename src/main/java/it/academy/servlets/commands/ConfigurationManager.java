package it.academy.servlets.commands;

import java.util.ResourceBundle;

import static it.academy.utils.Constants.PAGES;

public class ConfigurationManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(PAGES);

    private ConfigurationManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
