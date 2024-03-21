package it.academy.util;

public final class Constants {

    public static final String SELECT_ALL_FROM_TABLE = "SELECT c FROM %s c";
    public static final String SELECT_COUNT_FROM_TABLE = "SELECT COUNT (c) FROM %s c";
    public static final String SELECT_ALL_FROM_STUDENT_WITH_ORDER =
        "SELECT s FROM Student s ORDER BY s.surname ASC , s.name ASC, s.age ASC";
    public static final String DELETE_ALL_FROM_TABLE = "DELETE FROM %s";

    public static final int DEFAULT_COUNT_ON_PAGE = 5;
    public static final int DEFAULT_FIRST_PAGE_NUMBER = 1;
    public static final int DEFAULT_LAST_PAGE_NUMBER = 0;

    private Constants() {
    }
}
