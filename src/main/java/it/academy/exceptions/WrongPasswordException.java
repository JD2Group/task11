package it.academy.exceptions;

import it.academy.utils.Constants;

public class WrongPasswordException extends Exception{

    public WrongPasswordException(){
        super(Constants.WRONG_PASSWORD);
    }

}
