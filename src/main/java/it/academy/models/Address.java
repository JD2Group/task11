package it.academy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address {

    @NotBlank
    @Size(min = 5, max = 30, message = "City must be between {min} and {max} characters long.")
    private String city;

    @NotBlank
    @Size(min = 5, max = 30, message = "Street must be between {min} and {max} characters long.")
    private String street;

    @Min(value = 1, message = "Building must be grater than or equal {value}.")
    @Max(value = 1000, message = "Building must be lesser than or equal {value}.")
    private int building;
}
