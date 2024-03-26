package it.academy.service.impl;

import it.academy.components.JwtProvider;
import it.academy.dao.UserDAO;
import it.academy.dao.impl.UserDAOImpl;
import it.academy.dto.response.LoginResponse;
import it.academy.models.User;
import it.academy.service.JwtService;
import it.academy.utils.Constants;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtServiceImpl implements JwtService {

    private static final Map<String, String> REFRESH_TOKENS_STORAGE = new HashMap<>();
    private final JwtProvider jwtProvider = new JwtProvider();
    private final UserDAO userDAO = new UserDAOImpl();

    private boolean saveRefreshToken(@NonNull String email, @NonNull String refreshToken) {
        REFRESH_TOKENS_STORAGE.put(email, refreshToken);
        log.info(Constants.REFRESH_TOKEN_ADDED, email);
        return true;
    }

    public boolean validateToken(@NonNull String email, @NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            String token = REFRESH_TOKENS_STORAGE.get(email);
            if (refreshToken.equals(token)) {
                return true;
            } else if (token != null && !jwtProvider.validateRefreshToken(token)) {
                REFRESH_TOKENS_STORAGE.remove(email);
                log.info(Constants.REFRESH_TOKEN_REMOVED, email);
            }
        }
        return false;
    }

    public LoginResponse updateTokens(String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)) {
            String email = jwtProvider.getRefreshClaims(refreshToken).getSubject();
            /*if (REFRESH_TOKENS_STORAGE.get(email) == null){
                return null;
            }*/
            User user = userDAO.getUserByEmail(email);
            if (user == null) {
                log.warn(Constants.USER_NOT_FOUND_ERROR, email);
                return null;
            }
            LoginResponse response = getNewPairOfTokens(user);
            //REFRESH_TOKENS_STORAGE.remove(email);
            REFRESH_TOKENS_STORAGE.put(email, response.getRefreshToken());
            log.info(Constants.REFRESH_TOKEN_ADDED, email);
            return response;
        }
        return null;
    }

    public LoginResponse getNewPairOfTokens(@NonNull User user) {
        String refreshToken = jwtProvider.generateRefreshToken(user);
        saveRefreshToken(user.getEmail(), refreshToken);
        return LoginResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user))
                .refreshToken(refreshToken)
                .roles(user.getRoles())
                .build();
    }

}
