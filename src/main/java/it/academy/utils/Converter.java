package it.academy.utils;

import it.academy.dto.StudentDTO;
import it.academy.entities.Address;
import it.academy.entities.Student;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    private Converter() {
    }

    public static List<StudentDTO> convertListToDTO(List<Student> students) {
        return students.stream()
                .map(Converter::convertToDTO)
                .collect(Collectors.toList());
    }

    public static List<Student> convertDTOListToEntityList(List<StudentDTO> students) {
        return students.stream()
                .map(Converter::convertToEntity)
                .collect(Collectors.toList());
    }

    public static StudentDTO convertToDTO(Student student) {
        return StudentDTO.builder()
                .id(student.getId())
                .name(student.getName())
                .surname(student.getSurname())
                .age(student.getAge())
                .mark(student.getMark())
                .city(student.getAddress().getCity())
                .street(student.getAddress().getStreet())
                .houseNumber(student.getAddress().getBuilding())
                .build();
    }

    public static Student convertToEntity(StudentDTO studentDTO) {
        return Student.builder()
                .id(studentDTO.getId() == 0? null : studentDTO.getId())
                .name(studentDTO.getName())
                .surname(studentDTO.getSurname())
                .age(studentDTO.getAge())
                .mark(studentDTO.getMark())
                .address(Address.builder()
                        .city(studentDTO.getCity())
                        .street(studentDTO.getStreet())
                        .building(studentDTO.getHouseNumber())
                        .build())
                .build();
    }
}
