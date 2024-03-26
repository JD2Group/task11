package it.academy.exceptions;

import it.academy.utils.Constants;

public class PasswordMatchException extends Exception {

    public PasswordMatchException() {
        super(Constants.PASSWORD_MATCH_ERROR);
    }

}
