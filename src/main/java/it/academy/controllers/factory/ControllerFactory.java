package it.academy.controllers.factory;


import it.academy.controllers.Controller;

public class ControllerFactory {

    private static ControllerFactory factory;

    private ControllerFactory(){

    }

    public static ControllerFactory getFactory() {
        if (factory == null){
            factory = new ControllerFactory();
        }
        return factory;
    }

    public Controller defineCommand(String command) {
        if (command != null && !command.isEmpty()) {
            try {
                return CommandEnum.valueOf(command.toUpperCase()).getCurrentCommand();
            } catch (IllegalArgumentException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
