package it.academy.dto;

import lombok.*;

import java.io.Serializable;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@ToString
@Builder
public class StudentDTO implements Serializable {

    private long id;

    private String name;

    private String surname;

    private int age;

    private int mark;

    private String city;

    private String street;

    private int building;

    private String countryName;
}