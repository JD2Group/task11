package it.academy.commands.factory;


import it.academy.commands.Command;
import it.academy.commands.student.DeleteStudentCommand;
import it.academy.commands.student.GetStudentsPageCommand;
import it.academy.commands.student.SaveStudentCommand;
import it.academy.commands.student.UpdateStudentCommand;

public enum CommandEnum {
    POST_CREATE(new SaveStudentCommand()),
    POST_UPDATE(new UpdateStudentCommand()),
    POST_DELETE(new DeleteStudentCommand()),
    GET_STUDENTS_PAGE(new GetStudentsPageCommand());

    private final Command command;

    CommandEnum(Command command) {
        this.command = command;
    }

    public Command getCurrentCommand() {
        return command;
    }
}
