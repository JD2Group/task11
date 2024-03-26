package it.academy.service.impl;

import it.academy.components.JwtProvider;
import it.academy.dto.response.LoginResponse;
import it.academy.models.User;
import it.academy.service.JwtService;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

@Slf4j
public class JwtServiceImpl implements JwtService {

    private static final Map<String, String> REFRESH_TOKENS_STORAGE = new HashMap<>();
    private final JwtProvider jwtProvider = new JwtProvider();

    private boolean saveRefreshToken(@NonNull String email, @NonNull String refreshToken) {
        REFRESH_TOKENS_STORAGE.put(email, refreshToken);
        return true;
    }

    public boolean validateToken(@NonNull String email, @NonNull String refreshToken) {
        if (jwtProvider.validateRefreshToken(refreshToken)){
            String token = REFRESH_TOKENS_STORAGE.get(email);
            if (refreshToken.equals(token)){
                return true;
            }else if (token != null && !jwtProvider.validateRefreshToken(token)){
                REFRESH_TOKENS_STORAGE.remove(email);
            }
        }
        return false;
    }

    public LoginResponse getNewPairOfTokens(@NonNull User user){
        String refreshToken = jwtProvider.generateRefreshToken(user);
        saveRefreshToken(user.getEmail(), refreshToken);
        return LoginResponse.builder()
                .accessToken(jwtProvider.generateAccessToken(user))
                .refreshToken(refreshToken)
                .build();
    }

}
