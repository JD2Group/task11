package it.academy.service;

import it.academy.dto.response.LoginResponse;
import it.academy.models.User;
import lombok.NonNull;

public interface JwtService {

    boolean validateToken(@NonNull String email, @NonNull String refreshToken);

    LoginResponse getNewPairOfTokens(@NonNull User user);

    LoginResponse updateTokens(String refreshToken);

    String extractRolesFromToken(String token);
}
