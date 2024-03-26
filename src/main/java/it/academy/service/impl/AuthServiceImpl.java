package it.academy.service.impl;

import it.academy.components.JwtProvider;
import it.academy.dao.RoleDAO;
import it.academy.dao.UserDAO;
import it.academy.dao.impl.RoleDAOImpl;
import it.academy.dao.impl.UserDAOImpl;
import it.academy.dto.request.LoginRequest;
import it.academy.dto.request.RegistrationRequest;
import it.academy.dto.response.LoginResponse;
import it.academy.dto.response.RegistrationResponse;
import it.academy.enums.RoleEnum;
import it.academy.exceptions.*;
import it.academy.models.Role;
import it.academy.models.User;
import it.academy.service.AuthService;
import it.academy.service.JwtService;
import it.academy.utils.Constants;
import it.academy.utils.ResponseHelper;
import it.academy.utils.TransactionHelper;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.mindrot.jbcrypt.BCrypt;

import java.util.Objects;
import java.util.Set;

@Slf4j
public class AuthServiceImpl implements AuthService {
    private final UserDAO userDAO = new UserDAOImpl();
    private final RoleDAO roleDAO = new RoleDAOImpl();
    private final JwtService jwtService = new JwtServiceImpl();
    private final TransactionHelper transactionHelper = TransactionHelper.getTransactionHelper();

    public RegistrationResponse userRegistration(@NonNull RegistrationRequest request) throws
            UserAlreadyExistsException,
            PasswordMatchException,
            EmailNullException {
        if (!Objects.equals(request.getPassword(), request.getConfirmPassword())) {
            throw new PasswordMatchException();
        }
        if (request.getEmail() == null) {
            log.warn("Email is null.");
            throw new EmailNullException();
        }
        if (userDAO.getUserByEmail(request.getEmail()) != null) {
            log.warn("User with {} already exist.", request.getEmail());
            throw new UserAlreadyExistsException();
        }
        try {
            transactionHelper.begin();
            Role default_user_role = roleDAO.getRoleByName(RoleEnum.DEFAULT_USER.name());
            if (default_user_role == null) {
                log.warn("Default role ({}) isn't recognize. Please check role table.", RoleEnum.DEFAULT_USER.name());
                return null;
            }
            User user = User.builder()
                    .email(request.getEmail())
                    .password(BCrypt.hashpw(request.getPassword(), BCrypt.gensalt()))
                    .roles(Set.of(default_user_role))
                    .build();
            log.info("User {} prepared to save.", request.getEmail());
            userDAO.create(user);
        /*if ( userDAO.create(user) == null) {
            log.warn("During the save, something went wrong. Please check the error message.");
            return null;
        }*/
            transactionHelper.commit();
        }catch (Exception e){
            e.printStackTrace();
            transactionHelper.rollback();
            return null;
        }
        log.info("User {} successfully saved.", request.getEmail());
        return ResponseHelper.getRegistrationResponse(request.getEmail(), Constants.USER_CREATED);

    }

    public LoginResponse userLogin(@NonNull LoginRequest request) throws UserNotFoundException, WrongPasswordException {
        User user = userDAO.getUserByEmail(request.getEmail());
        if (user == null) {
            log.warn("User with email {} not found.", request.getEmail());
            throw new UserNotFoundException();
        }
        if (!BCrypt.checkpw(request.getPassword(), user.getPassword())){
            log.warn("Wrong password. Current: {}; Except: {}", request.getPassword(), user.getPassword());
            throw new WrongPasswordException();
        }
        return jwtService.getNewPairOfTokens(user);
    }


}
