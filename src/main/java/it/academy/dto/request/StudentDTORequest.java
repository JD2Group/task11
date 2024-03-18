package it.academy.dto.request;

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
public class StudentDTORequest implements Serializable {
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("surname")
    @Expose
    private String surname;
    @SerializedName("age")
    @Expose
    private Integer age;
    @SerializedName("mark")
    @Expose
    private Double mark;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("street")
    @Expose
    private String street;
    @SerializedName("building")
    @Expose
    private Integer building;

    public boolean validate(String method) {
        switch (method) {
            case "SAVE":
                return id != 0;
            case "UPDATE":
            case "DELETE":
                return id == 0;
        }
        return true;
    }
}
