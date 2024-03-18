package it.academy.utils;

public class Constants {
    public static final int LIST_SIZE = 30;
    public static final int FIRST_PAGE = 1;

    //MESSAGES
    public static final String UPDATE_ERROR_MESSAGE = "message.update.error";
    public static final String DELETE_ERROR_MESSAGE = "message.delete.error";

    //PATHS
    public static final String ERROR_PAGE_PATH = "path.page.error";
    public static final String START_PAGE_PATH = "list?command=show_students&&page=1";
    public static final String LIST_PAGE_PATH = "path.page.list";
    public static final String STUDENT_PAGE_PATH = "path.page.student";

    //ATTRIBUTES
    public static final String ERROR_PAGE_ATTRIBUTE = "errorPage";
    public static final String PAGE_NUMBER_ATTRIBUTE = "pageNumber";
    public static final String COMMAND_ATTRIBUTE = "command";
    public static final String PAGE_ATTRIBUTE = "page";
    public static final String STUDENTS_ATTRIBUTE = "students";
    public static final String STUDENT_ATTRIBUTE = "student";
    public static final String MAX_PAGE_ATTRIBUTE = "maxPage";

    //RESOURCES
    public static final String MESSAGES = "web/messages";
    public static final String PAGES = "web/pages";

    //STUDENT_ATTRIBUTES
    public static final String STUDENT_ID = "id";
    public static final String STUDENT_NAME = "name";
    public static final String STUDENT_SURNAME = "surname";
    public static final String STUDENT_AGE = "age";
    public static final String STUDENT_MARK = "mark";
    public static final String STUDENT_CITY = "city";
    public static final String STUDENT_STREET = "street";
    public static final String STUDENT_HOUSE = "house";

}
