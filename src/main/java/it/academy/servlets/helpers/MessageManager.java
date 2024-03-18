package it.academy.servlets.helpers;

import java.util.ResourceBundle;

import static it.academy.utils.Constants.MESSAGES;

public class MessageManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle(MESSAGES);

    private MessageManager() {
    }

    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
