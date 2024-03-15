package it.academy.util;

import java.util.Random;

public final class Constants {


    public static final String SELECT_ALL_FROM_TABLE = "SELECT c FROM %s c";
    public static final String SELECT_COUNT_FROM_TABLE = "SELECT COUNT (c) FROM %s c";
    public static final String SELECT_ALL_FROM_STUDENT_WITH_ORDER =
        "SELECT s FROM Student s ORDER BY s.surname ASC , s.name ASC, s.age ASC";
    public static final String SELECT_FROM_STUDENT_WITH_LIMIT_AND_OFFSET =
        "SELECT s FROM Student s ORDER BY s.surname AND s.name AND s.age LIMIT %d OFFSET %d";
    public static final String DELETE_ALL_FROM_TABLE = "DELETE FROM %s";

















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


    private Constants() {
    }
}
