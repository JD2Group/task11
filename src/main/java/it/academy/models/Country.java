package it.academy.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "countries")
public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column
    @NotBlank
    @Size(min = 3, max = 20, message = "Country must be between {min} and {max} characters long.")
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]{2,19}-?[A-ZА-Я]*[a-zа-я]{2,19}",
            message = "Country cannot contain numbers! The first letter must be uppercase, subsequent letters must be lowercase!")
    private String name;
}
