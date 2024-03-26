package it.academy.commands.auth;

import com.google.gson.JsonSyntaxException;
import it.academy.commands.Command;
import it.academy.dto.request.RegistrationRequest;
import it.academy.dto.response.RegistrationResponse;
import it.academy.exceptions.EmailNullException;
import it.academy.exceptions.PasswordMatchException;
import it.academy.exceptions.UserAlreadyExistsException;
import it.academy.service.AuthService;
import it.academy.service.impl.AuthServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.GSON;

public class RegistrationCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthService authService = new AuthServiceImpl();
        try {
            String req = request.getReader().lines().collect(Collectors.joining());
            RegistrationRequest reg = GSON.fromJson(req, RegistrationRequest.class);
            RegistrationResponse out = authService.userRegistration(reg);
            if (out == null){
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return null;
            }
            return GSON.toJson(out);
        } catch (UserAlreadyExistsException | PasswordMatchException | EmailNullException | JsonSyntaxException e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return null;
        }
    }
}
