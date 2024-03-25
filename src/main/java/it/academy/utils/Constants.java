package it.academy.utils;

import com.google.gson.Gson;

import java.util.Random;

public final class Constants {

    public static final Gson GSON = new Gson();

    //entity attributes
    public static final String ENTITY_ID = "id";
    public static final String SELECT_ALL_FROM_TABLE = "SELECT c FROM %s c";
    public static final String SELECT_COUNT_FROM_TABLE = "SELECT COUNT (c) FROM %s c";
    public static final String SELECT_ALL_FROM_STUDENT_WITH_ORDER =
            "select s from Student s";
    //"SELECT s FROM Student s ORDER BY s.surname ASC , s.name ASC, s.age ASC";
    public static final String DELETE_ALL_FROM_TABLE = "DELETE FROM %s";
    public static final String NULL_EXCEPTION_MESSAGE = "Try to use null object. Aborted.";
    public static final String SELECT_COUNTRY_BY_NAME = "SELECT c FROM Country c where name = :name";
    public static final Random RANDOM = new Random();
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong!";
    public static final String SUCCESSFULLY_CREATED = "Successfully created!";
    public static final String SUCCESSFULLY_DELETED = "Successfully deleted!";
    public static final String SUCCESSFULLY_UPDATED = "Successfully updated!";
    public static final String REQUEST_RESOURCE_NOT_FOUND = "Request resource not found!";
    public static final String COMMAND_HEADER = "Command";
    public static final String STUDENT_NOT_FOUND = "Student not found.";


    private Constants() {
    }
}
