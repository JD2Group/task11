package it.academy.util;

import it.academy.dto.StudentDTO;
import it.academy.pojo.Address;
import it.academy.pojo.Country;
import it.academy.pojo.Student;

import java.util.Optional;

public class StudentConverter {

    private StudentConverter() {
    }

    public static Student convertToEntity(StudentDTO studentFrom) {

        if (studentFrom == null) {
            return null;
        }
        String countryName = Optional.ofNullable(studentFrom.getCountryName()).orElse("").trim().toUpperCase();
        Country country = null;
        if (!"".equals(countryName)) {
            country = Country.builder()
                          .countryName(countryName)
                          .build();
        }
        Student student = Student.builder()
                              .id(studentFrom.getId())
                              .name(studentFrom.getName())
                              .surname(studentFrom.getSurname())
                              .age(studentFrom.getAge())
                              .mark(studentFrom.getMark())
                              .address(Address.builder()
                                           .street(Optional.ofNullable(studentFrom.getStreet()).orElse("").trim())
                                           .city(Optional.ofNullable(studentFrom.getCity()).orElse("").trim())
                                           .building(studentFrom.getBuilding())
                                           .build())
                              .country(country)
                              .build();
        return student;
    }

    public static StudentDTO convertToDTO(Student studentFrom) {

        if (studentFrom == null) {
            return null;
        }
        Address address = Optional.ofNullable(studentFrom.getAddress()).orElse(new Address());
        Country country = Optional.ofNullable(studentFrom.getCountry()).orElse(new Country());
        return StudentDTO.builder()
                   .id(studentFrom.getId())
                   .name(studentFrom.getName())
                   .surname(studentFrom.getSurname())
                   .age(studentFrom.getAge())
                   .mark(studentFrom.getMark())
                   .street(address.getStreet())
                   .city(address.getCity())
                   .building(address.getBuilding())
                   .countryName(country.getCountryName())
                   .build();
    }
}
