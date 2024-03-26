package it.academy.service;

import it.academy.dto.CountryDTO;
import it.academy.dto.StudentDTO;

import java.util.List;

public interface AdminServise {

    StudentDTO getById(Long id) throws Exception;

    List<StudentDTO> getListOfStudents(int page, int count) throws Exception;

    List<StudentDTO> getListOfStudentsFromCountry(int page, int count, long countryId) throws Exception;

    List<CountryDTO> getAllCountries(int page, int count) throws Exception;

    void createStudent(StudentDTO studentDTO) throws Exception;

    void deleteStudent(Long id) throws Exception;

    void updateStudent(StudentDTO studentDTO) throws Exception;

    void updateCountry(CountryDTO countryDTO) throws Exception;

    void deleteCountry(Long longId) throws Exception;

    CountryDTO getCountryBYId(Long longId) throws Exception;

    long getCountOfAllStudents() throws Exception;

    long getCountOfAllStudentsFromCountry(Long countryId) throws Exception;

    long getCountOfAllCountries() throws Exception;
}
