package it.academy.controllers.factory;


import it.academy.controllers.Controller;
import it.academy.controllers.StudentController;

import static it.academy.utils.Constants.*;

public enum CommandEnum {
    CREATE(new StudentController(CREATE_ACTION)),
    UPDATE(new StudentController(UPDATE_ACTION)),
    DELETE(new StudentController(DELETE_ACTION));

    private Controller command;

    CommandEnum(Controller command) {
        this.command = command;
    }

    public Controller getCurrentCommand() {
        return command;
    }
}
