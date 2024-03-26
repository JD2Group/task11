package it.academy.exceptions;

import it.academy.utils.Constants;

public class UserAlreadyExistsException extends Exception{

    public UserAlreadyExistsException(){
        super(Constants.USER_ALREADY_EXISTS);
    }

}
