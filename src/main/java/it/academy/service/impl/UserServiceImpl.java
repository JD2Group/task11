package it.academy.service.impl;

import it.academy.dto.StudentDTO;
import it.academy.pojo.Country;
import it.academy.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    @Override
    public List<StudentDTO> getAllStudents(int page, int count) throws Exception {
        return null;
    }

    @Override
    public List<StudentDTO> getAllStudents(long countryId) throws Exception {
        return null;
    }

    @Override
    public List<Country> getAllCountries() {
        return null;
    }
}
