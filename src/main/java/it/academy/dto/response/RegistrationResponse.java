package it.academy.dto.response;

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
public class RegistrationResponse implements Serializable {
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("message")
    @Expose
    private String message;

}
