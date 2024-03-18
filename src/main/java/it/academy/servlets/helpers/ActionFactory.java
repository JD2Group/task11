package it.academy.servlets.helpers;

import it.academy.servlets.commands.ActionCommand;

import javax.servlet.http.HttpServletRequest;

import static it.academy.utils.Constants.COMMAND_ATTRIBUTE;

public class ActionFactory {

    public ActionCommand defineCommand(HttpServletRequest req) {
        ActionCommand current= null;

        String action = req.getParameter(COMMAND_ATTRIBUTE);

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return current;
    }
}
