package it.academy.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class StudentDTO {

    private long id;

    private String name;

    private String surname;

    private int age;

    private int mark;

    private String city;

    private String street;

    private int building;
}
