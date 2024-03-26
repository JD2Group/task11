package it.academy.exceptions;

import it.academy.utils.Constants;

public class EmailNullException extends Exception{

    public EmailNullException(){
        super(Constants.EMAIL_NULL_MESSAGE);
    }

}
