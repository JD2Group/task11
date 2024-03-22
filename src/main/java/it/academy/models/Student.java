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
    @Size(min = 5, max = 20, message = "Name must be between {min} and {max} characters long.")
    private String name;

    @Column
    @NotBlank
    @Size(min = 5, max = 30, message = "Surname must be between {min} and {max} characters long.")
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
}
