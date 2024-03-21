package it.academy.controllers.factory;


import it.academy.controllers.Controller;
public class ControllerFactory {

    public Controller defineCommand(String req) {
        Controller current = null;

        try {
            CommandEnum currentEnum = CommandEnum.valueOf(req.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }

        return current;
    }
}
