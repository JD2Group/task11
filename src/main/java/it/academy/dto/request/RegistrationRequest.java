package it.academy.dto.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RegistrationRequest implements Serializable {

    @SerializedName(value = "email")
    @Expose
    private String email;
    @SerializedName(value = "password")
    @Expose
    private String password;
    @SerializedName(value = "confirm_password")
    @Expose
    private String confirmPassword;

}
