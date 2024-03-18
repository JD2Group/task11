package it.academy.dto.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTOResponse implements Serializable {
    @SerializedName("httpStatus")
    @Expose
    private Integer httpStatus;
    @SerializedName("message")
    @Expose
    private String message;


}
