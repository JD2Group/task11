package it.academy.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull(message = "ID must not be null.")
    private long id;

    @Column
    @NotBlank
    @Size(min = 2, max = 20, message = "Name must be between {min} and {max} characters long.")
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]{2,19}",
            message = "Name cannot contain numbers! The first letter must be uppercase, subsequent letters must be lowercase!")
    private String name;

    @Column
    @NotBlank
    @Size(min = 3, max = 30, message = "Surname must be between {min} and {max} characters long.")
    @Pattern(regexp = "[A-ZА-Я][a-zа-я]{2,}-? ?([A-ZА-Я][a-zа-я]{2,})*",
            message = "Surname cannot contain numbers! The first letter must be uppercase, subsequent letters must be lowercase!")
    private String surname;

    @Column
    @Min(value = 1, message = "Age must be grater than or equal {value}.")
    @Max(value = 100, message = "Age must be lesser than or equal {value}.")
    private int age;

    @Embedded
    @ToString.Exclude
    private Address address;

    @Column
    @Min(value = 0, message = "Mark must be grater than or equal {value}.")
    @Max(value = 10, message = "Mark must be lesser than or equal {value}.")
    private int mark;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "country_id")
    private Country country;
}
