package it.academy.utils;

import it.academy.dto.request.StudentDTORequest;
import it.academy.models.Address;
import it.academy.models.Student;

public class StudentConverter {

    private StudentConverter() {
    }

    public static Student convertToEntity(StudentDTORequest studentFrom) {

        if (studentFrom == null) {
            return null;
        }
        return Student.builder()
                .id(studentFrom.getId())
                .name(studentFrom.getName())
                .surname(studentFrom.getSurname())
                .mark(studentFrom.getMark())
                .age(studentFrom.getAge())
                .address(Address.builder()
                        .street(studentFrom.getStreet())
                        .city(studentFrom.getCity())
                        .building(studentFrom.getBuilding())
                        .build())
                .build();
    }

    public static StudentDTORequest convertToDTO(Student studentFrom) {

        if (studentFrom == null) {
            return null;
        }
        return StudentDTORequest.builder()
                .id(studentFrom.getId())
                .name(studentFrom.getName())
                .surname(studentFrom.getSurname())
                .mark(studentFrom.getMark())
                .age(studentFrom.getAge())
                .street(studentFrom.getAddress().getStreet())
                .city(studentFrom.getAddress().getCity())
                .building(studentFrom.getAddress().getBuilding())
                .build();
    }
}
