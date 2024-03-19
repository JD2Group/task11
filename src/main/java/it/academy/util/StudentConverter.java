package it.academy.util;

import it.academy.dto.StudentDTO;
import it.academy.pojo.Address;
import it.academy.pojo.Student;

public class StudentConverter {

    private StudentConverter() {
    }

    public static Student convertToEntity(StudentDTO studentFrom) {

        if (studentFrom == null) {
            return null;
        }
        return Student.builder()
                   .id(studentFrom.getId())
                   .name(studentFrom.getName())
                   .surname(studentFrom.getSurname())
                   .age(studentFrom.getAge())
                   .address(Address.builder()
                                .street(studentFrom.getStreet())
                                .city(studentFrom.getCity())
                                .building(studentFrom.getBuilding())
                                .build())
                   .mark(studentFrom.getMark())
                   .build();
    }

    public static StudentDTO convertToDTO(Student studentFrom) {

        if (studentFrom == null) {
            return null;
        }
        return StudentDTO.builder()
                   .id(studentFrom.getId())
                   .name(studentFrom.getName())
                   .surname(studentFrom.getSurname())
                   .age(studentFrom.getAge())
                   .street(studentFrom.getAddress().getStreet())
                   .city(studentFrom.getAddress().getCity())
                   .building(studentFrom.getAddress().getBuilding())
                   .mark(studentFrom.getMark())
                   .build();
    }
}
