package it.academy.service;

import it.academy.dto.request.LoginRequest;
import it.academy.dto.request.RegistrationRequest;
import it.academy.dto.response.LoginResponse;
import it.academy.dto.response.RegistrationResponse;
import it.academy.exceptions.*;
import lombok.NonNull;

public interface AuthService {
    LoginResponse userLogin(@NonNull LoginRequest request) throws UserNotFoundException, WrongPasswordException;

    RegistrationResponse userRegistration(@NonNull RegistrationRequest request) throws
            UserAlreadyExistsException,
            PasswordMatchException,
            EmailNullException;
}
