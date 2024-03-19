package it.academy.servlets.helpers;

import it.academy.servlets.commands.*;

public enum CommandEnum {
    SHOW_STUDENTS(new ChangePageCommand()),
    SAVE_STUDENT(new SaveOrUpdateCommand()),
    CHANGE_STUDENT(new ChangeCommand()),
    DELETE_STUDENT(new DeleteCommand()),
    NEXT_PAGE(new ChangePageCommand()),
    PREV_PAGE(new ChangePageCommand()),
    FIND_STUDENTS(new FindCommand());

    private ActionCommand command;

    CommandEnum(ActionCommand command) {
        this.command = command;
    }

    public ActionCommand getCurrentCommand() {
        return command;
    }
}
