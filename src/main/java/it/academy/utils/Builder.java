package it.academy.utils;

import it.academy.entities.Address;
import it.academy.entities.Student;

public class Builder {

    private Builder() {
    }

    public static Student buildStudent() {
        return Student.builder()
                .id(0L)
                .name("")
                .surname("")
                .age(0)
                .mark(0)
                .address(Address.builder()
                        .city("")
                        .street("")
                        .building(0)
                        .build())
                .build();
    }
}
