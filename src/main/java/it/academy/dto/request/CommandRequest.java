package it.academy.dto.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CommandRequest {
    @SerializedName("id")
    @Expose
    private long id;
    @SerializedName("command")
    @Expose
    private String command;
}
