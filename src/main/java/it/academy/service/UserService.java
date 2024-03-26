package it.academy.service;

import it.academy.dto.StudentDTO;
import it.academy.pojo.Country;

import java.util.List;

public interface UserService {


    List<StudentDTO> getAllStudents(int page, int count) throws Exception;

    List<StudentDTO> getAllStudents(long countryId) throws Exception;

    List<Country> getAllCountries();
}
