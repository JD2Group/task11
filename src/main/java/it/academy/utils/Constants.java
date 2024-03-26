package it.academy.utils;

import com.google.gson.Gson;

import java.util.Random;

public final class Constants {

    public static final Gson GSON = new Gson();
    public static final Random RANDOM = new Random();
    public static final String JWT_ACCESS_SECRET = "gpgdKY9zR4hebFb2wT67AlgsKmbTOmKoyqmGym1dKmM=";
    public static final String JWT_REFRESH_SECRET = "7+bbUaAIaRFDce4rxQLu5QFPqooqHLgfN5mnDB7PNT4=";
    public static final Integer JWT_ACCESS_EXPIRATION = 1;
    public static final Integer JWT_REFRESH_EXPIRATION = 60;
    public static final String FALSE = "false";
    public static final String TRUE = "true";
    public static final String ENCODING = "UTF8";

    //entity attributes
    public static final String ENTITY_ID = "id";
    public static final String ENTITY_NAME = "name";

    //Queries
    public static final String SELECT_ALL_FROM_TABLE = "SELECT c FROM %s c";
    public static final String SELECT_COUNT_FROM_TABLE = "SELECT COUNT (c) FROM %s c";
    public static final String SELECT_ALL_FROM_STUDENT_WITH_ORDER =
            "select s from Student s";
    //"SELECT s FROM Student s ORDER BY s.surname ASC , s.name ASC, s.age ASC";
    public static final String DELETE_ALL_FROM_TABLE = "DELETE FROM %s";
    public static final String SELECT_COUNTRY_BY_NAME = "SELECT c FROM Country c where name = :name";
    public static final String SELECT_ALL_FROM_USER_BY_EMAIL = "select u from User u where u.email ='%s'";
    public static final String SELECT_ALL_FROM_ROLE_BY_NAME = "select r from Role r where r.roleName='%s'";


    //Error messages
    public static final String PASSWORD_MATCH_ERROR = "Password and password confirmation do not match.";
    public static final String USER_NOT_FOUND = "User not found.";
    public static final String EMAIL_NULL_MESSAGE = "Email cannot be null.";
    public static final String WRONG_PASSWORD = "Wrong password!";
    public static final String USER_ALREADY_EXISTS = "User already exists!";
    public static final String STUDENT_NOT_FOUND = "Student not found.";
    public static final String REQUEST_RESOURCE_NOT_FOUND = "Request resource not found!";
    public static final String NULL_EXCEPTION_MESSAGE = "Try to use null object. Aborted.";
    public static final String INTERNAL_SERVER_ERROR_MESSAGE = "Something went wrong!";
    public static final String WRONG_SECRET_KEY = "Wrong secret key";
    public static final String TOKEN_ERROR = "Token expired! ";
    public static final String TOKEN_MISSING_ERROR = "Token missing.";
    public static final String UNSUPPORTED_JWT_ERROR = "Unsupported jwt: ";
    public static final String MALFORMED_JWT_ERROR = "Malformed jwt: ";
    public static final String SIGNATURE_ERROR = "Invalid signature: ";
    public static final String INVALID_TOKEN_ERROR = "invalid token: ";
    public static final String EMAIL_NULL_ERROR = "Email is null.";
    public static final String EMAIL_ALREADY_EXIST_ERROR = "User with {} already exist.";
    public static final String ROLE_ERROR = "Default role ({}) isn't recognize. Please check role table.";
    public static final String USER_NOT_FOUND_ERROR = "User with email {} not found.";
    public static final String WRONG_PASSWORD_ERROR = "Wrong password. Current: {}; Except: {}";
    public static final String COMMAND_INVALID_ERROR = "Command invalid.";
    public static final String UNDEFINED_COMMAND_ERROR = "Command is undefined.";
    public static final String INVALID_RESPONSE_ERROR = "Response is null.";
    public static final String COOKIES_ERROR = "Cookies is empty.";
    public static final String VALIDATION_ERROR = "Validation is failed {}";
    public static final String SAVE_ERROR = "Error while saving {}";
    public static final String DELETE_ERROR = "Error while deletion id{}";
    public static final String UPDATE_ERROR = "Error while update {}";
    //Success messages
    public static final String USER_CREATED = "User created!";
    public static final String USER_CREATED_MESSAGE = "User {} prepared to save.";
    public static final String USER_SAVED_MESSAGE = "User {} successfully saved.";
    public static final String USER_UPDATED_MESSAGE = "User {} successfully updated.";
    public static final String STUDENT_SAVED_MESSAGE = "Student {} successfully saved.";
    public static final String STUDENT_DELETED_MESSAGE = "Student with id {} successfully deleted.";
    public static final String SUCCESSFULLY_CREATED = "Successfully created! ";
    public static final String SUCCESSFULLY_DELETED = "Successfully deleted! ";
    public static final String SUCCESSFULLY_UPDATED = "Successfully updated! ";
    public static final String AUTHENTICATION_SUCCESSFUL = "Authentication successful!";
    public static final String REFRESH_TOKEN_ADDED = "Refresh token for email {} added.";
    public static final String REFRESH_TOKEN_REMOVED = "Refresh token for email {} removed.";

    //Keys
    public static final String COMMAND_HEADER = "Command";
    public static final String AUTHORIZATION_HEADER = "Authorization";
    public static final String TOKEN_PATTERN = "Bearer ";
    public static final String COMMAND_PATTERN = "%s_%s";
    public static final String ROLES_KEY = "roles";
    public static final String POST_METHOD = "POST";
    public static final String GET_METHOD = "GET";
    public static final String PARAMETER_KEY = "?";
    public static final String AUTHENTICATION_KEY = "isAuthenticated";
    public static final String REFRESH_TOKEN_KEY = "refresh_token";

    //Headers
    public static final String UPDATED_HEADER = "updated-access";


    private Constants() {
    }
}
