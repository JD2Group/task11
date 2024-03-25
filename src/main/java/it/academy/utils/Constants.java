package it.academy.utils;

import com.google.gson.Gson;

import java.util.Random;

public final class Constants {

    public static final Gson GSON = new Gson();

    //entity attributes
    public static final String ENTITY_ID = "id";

    //command names
    public static final String CREATE_ACTION = "create";
    public static final String UPDATE_ACTION = "update";
    public static final String DELETE_ACTION = "delete";


    public static final String SELECT_ALL_FROM_TABLE = "SELECT c FROM %s c";
    public static final String SELECT_COUNT_FROM_TABLE = "SELECT COUNT (c) FROM %s c";
    public static final String SELECT_ALL_FROM_STUDENT_WITH_ORDER =
            "select s from Student s";
    //"SELECT s FROM Student s ORDER BY s.surname ASC , s.name ASC, s.age ASC";
    public static final String SELECT_FROM_STUDENT_WITH_LIMIT_AND_OFFSET =
            "SELECT s FROM Student s ORDER BY s.surname AND s.name AND s.age LIMIT %d OFFSET %d";
    public static final String DELETE_ALL_FROM_TABLE = "DELETE FROM %s";
    public static final String NULL_EXCEPTION_MESSAGE = "Try to use null object. Aborted.";
    public static final String SELECT_COUNTRY_BY_NAME = "SELECT c FROM Country c where name = :name";
    public static final String STRING_N = "%n";
    public static final Random RANDOM = new Random();

    public static final int INITIAL_NUMBER_OF_STUDENTS = 6;

    public static final String RANDOM_NAME = "Name No.%d";
    public static final String RANDOM_SURNAME = "Surname  No.%d";
    public static final String RANDOM_EMAIL = "email@mail No.%d";

    public static final String SAVE_MESSAGE = "Object saved successfully: %s";
    public static final String SAVE_FAILED_MESSAGE = "Object saving failed: %s, cause: ";
    public static final String DELETE_FAILED_MESSAGE = "Object %s was not deleted, cause: ";
    public static final String UPDATE_MESSAGE = "Object updated successfully: %s";
    public static final String UPDATE_FAILED_MESSAGE = "Object update failed: %s, cause: ";
    public static final String DELETE_MESSAGE = "Object %s deleted successfully";
    public static final String MANAGER_CLOSED_MESSAGE = "EntityManager closed: %s";
    public static final String MANAGER_OPENED_MESSAGE = "EntityManager opened: %s";
    public static final String ENTITY_NOT_FOUND_MESSAGE = "Entity with id = %d not found";
    public static final String OBJECT_CREATED_MESSAGE = "Object created: %s";
    public static final String OBJECT_UPDATED_MESSAGE = "Object updated: %s";
    public static final String METHOD_SAVE = "SAVE";
    public static final String METHOD_UPDATE = "UPDATE";
    public static final int BAD_REQUEST_STATUS_CODE = 400;
    public static final int CREATED_STATUS_CODE = 201;
    public static final int OK_STATUS_CODE = 200;
    public static final int INTERNAL_SERVER_ERROR_STATUS_CODE = 500;
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
