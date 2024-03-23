package it.academy.commands.factory;


import it.academy.commands.Command;
import it.academy.commands.student.DeleteStudentCommand;
import it.academy.commands.student.SaveStudentCommand;
import it.academy.commands.student.UpdateStudentCommand;

public enum CommandEnum {
    CREATE(new SaveStudentCommand()),
    UPDATE(new UpdateStudentCommand()),
    DELETE(new DeleteStudentCommand());

    private Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCurrentCommand() {
        return command;
    }
}
