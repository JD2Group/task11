package it.academy.exceptions;

import it.academy.utils.Constants;
import net.bytebuddy.asm.Advice;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(){
        super(Constants.USER_NOT_FOUND);
    }
}
