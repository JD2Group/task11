package it.academy.dto.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import it.academy.models.Role;
import lombok.*;

import java.io.Serializable;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LoginResponse implements Serializable {
    @SerializedName("type")
    @Expose
    private final String type = "Bearer";
    @SerializedName("access_token")
    @Expose
    private String accessToken;
    @SerializedName("refresh_token")
    @Expose
    private String refreshToken;
    @SerializedName("roles")
    @Expose
    private Set<Role> roles;
}