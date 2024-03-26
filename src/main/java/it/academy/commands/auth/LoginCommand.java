package it.academy.commands.auth;

import com.google.gson.JsonSyntaxException;
import it.academy.commands.Command;
import it.academy.dto.request.LoginRequest;
import it.academy.dto.response.LoginResponse;
import it.academy.exceptions.UserNotFoundException;
import it.academy.exceptions.WrongPasswordException;
import it.academy.service.AuthService;
import it.academy.service.JwtService;
import it.academy.service.impl.AuthServiceImpl;
import it.academy.service.impl.JwtServiceImpl;
import it.academy.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.GSON;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        AuthService authService = new AuthServiceImpl();
        JwtService jwtService = new JwtServiceImpl();
        try {
            String req = request.getReader().lines().collect(Collectors.joining());
            LoginRequest reg = GSON.fromJson(req, LoginRequest.class);
            LoginResponse out = authService.userLogin(reg);
            if (out == null) {
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                return null;
            }
            String roles = jwtService.extractRolesFromToken(out.getAccessToken());
            request.getSession().setAttribute("isAuthenticated", "true");
            request.getSession().setAttribute(Constants.ROLES_KEY, roles);

            return GSON.toJson(out);
        } catch (UserNotFoundException | WrongPasswordException | JsonSyntaxException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return null;
        }
    }
}
