package it.academy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Embeddable
public class Address {

    @NotBlank
    @Size(min = 5, max = 30, message = "City must be between {min} and {max} characters long.")
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]{2,}-? ?([A-ZА-Я][a-zа-я]{2,})*",
            message = "City cannot contain numbers! The first letter must be uppercase, subsequent letters must be lowercase!")
    private String city;

    @NotBlank
    @Size(min = 5, max = 30, message = "Street must be between {min} and {max} characters long.")
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]{2,}-? ?([A-ZА-Я][a-zа-я]{2,})*",
            message = "Street cannot contain numbers! The first letter must be uppercase, subsequent letters must be lowercase!")
    private String street;

    @Min(value = 1, message = "Building must be grater than or equal {value}.")
    @Max(value = 1000, message = "Building must be lesser than or equal {value}.")
    private int building;
}
