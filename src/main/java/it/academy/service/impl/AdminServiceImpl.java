package it.academy.service.impl;

import it.academy.dao.CountryDAo;
import it.academy.dao.StudentDao;
import it.academy.dao.impl.CountryDaoImpl;
import it.academy.dao.impl.StudentDaoImpl;
import it.academy.dto.CountryDTO;
import it.academy.dto.StudentDTO;
import it.academy.pojo.Country;
import it.academy.pojo.Student;
import it.academy.service.AdminServise;
import it.academy.util.CountryConverter;
import it.academy.util.StudentConverter;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class AdminServiceImpl implements AdminServise {

    private final StudentDao studentDao = StudentDaoImpl.getInstance();
    private final CountryDAo countryDao = CountryDaoImpl.getInstance();

    @Override
    public StudentDTO getById(Long id) throws Exception {

        AtomicReference<StudentDTO> studentDTO = new AtomicReference<>();
        studentDao.executeInOneTransaction(() -> studentDTO.set(StudentConverter.convertToDTO(studentDao.get(id))));
        return studentDTO.get();
    }

    @Override
    public List<StudentDTO> getListOfStudents(int page, int count) throws Exception {

        List<Student> studentList = new ArrayList<>();
        studentDao.executeInOneTransaction(() -> studentList.addAll(studentDao.getAll(page, count)));
        return studentList.stream().map(StudentConverter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getListOfStudentsFromCountry(int page, int count, long countryId) throws Exception {

        List<Student> studentList = new ArrayList<>();
        studentDao.executeInOneTransaction(() -> studentList.addAll(studentDao.getAllFromCountry(page, count, countryId)));
        return studentList.stream().map(StudentConverter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<CountryDTO> getAllCountries(int page, int count) throws Exception {

        List<CountryDTO> dtoList = new ArrayList<>();
        countryDao.executeInOneTransaction(() -> {
            List<Country> list = countryDao.getAll(page, count);
            dtoList.addAll(list.stream()
                               .map(CountryConverter::convertToDTO)
                               .collect(Collectors.toList()));
        });
        return dtoList;
    }

    @Override
    public void createStudent(StudentDTO studentDTO) throws Exception {

        Student student = StudentConverter.convertToEntity(studentDTO);
        Country country = student.getCountry();

        studentDao.executeInOneTransaction(() -> {
            try {
                AtomicReference<Country> countryFromBase = new AtomicReference<>();
                if (country != null) {
                    countryDao.executeInOneTransaction(() -> {

                        Country country1 = countryDao.getByCountryName(country.getCountryName());
                        countryFromBase.set(country1);
                    });
                } else {
                    countryFromBase.set(null);
                }
                student.setCountry(countryFromBase.get());
            } catch (NoResultException e) {
                countryDao.executeInOneTransaction(() -> countryDao.create(country));
            }
            studentDao.create(student);
        });
        studentDao.closeManager();
    }

    @Override
    public void deleteStudent(Long id) throws Exception {

        studentDao.executeInOneTransaction(() -> studentDao.delete(id));
        studentDao.closeManager();
    }

    @Override
    public void updateStudent(StudentDTO studentDTO) throws Exception {

        Student student = StudentConverter.convertToEntity(studentDTO);
        Country country = student.getCountry();

        studentDao.executeInOneTransaction(() -> {
            try {
                AtomicReference<Country> countryFromBase = new AtomicReference<>();
                if (country != null) {
                    countryDao.executeInOneTransaction(() -> {
                        Country country1 = countryDao.getByCountryName(country.getCountryName());
                        countryFromBase.set(country1);
                    });
                } else {
                    countryFromBase.set(null);
                }
                student.setCountry(countryFromBase.get());
            } catch (NoResultException e) {
                countryDao.executeInOneTransaction(() -> countryDao.create(country));
            }
            studentDao.update(student);
        });
        studentDao.closeManager();
    }

    @Override
    public void updateCountry(CountryDTO countryDTO) throws Exception {

        Country country = CountryConverter.convertToEntity(countryDTO);
        countryDao.executeInOneTransaction(() -> countryDao.update(country));
        countryDao.closeManager();
    }

    @Override
    public long getCountOfAllStudents() throws Exception {

        AtomicLong count = new AtomicLong(0L);
        studentDao.executeInOneTransaction(() -> count.set(studentDao.countOfEntitiesInBase()));
        return count.get();
    }

    @Override
    public long getCountOfAllStudentsFromCountry(Long id) throws Exception {

        AtomicLong count = new AtomicLong(0L);
        studentDao.executeInOneTransaction(() -> count.set(studentDao.countOfStudentsFrmCountry(id)));
        return count.get();
    }

    @Override
    public long getCountOfAllCountries() throws Exception {

        AtomicLong count = new AtomicLong(0L);
        countryDao.executeInOneTransaction(() -> count.set(countryDao.countOfEntitiesInBase()));
        return count.get();
    }

    @Override
    public void deleteCountry(Long id) throws Exception {

        countryDao.executeInOneTransaction(() -> {

            countryDao.get(id).getStudents().forEach(s -> s.setCountry(null));
            countryDao.delete(id);
        });
        countryDao.closeManager();
    }

    @Override
    public CountryDTO getCountryBYId(Long id) throws Exception {

        AtomicReference<CountryDTO> countryDTO = new AtomicReference<>();
        countryDao.executeInOneTransaction(() -> countryDTO.set(CountryConverter.convertToDTO(countryDao.get(id))));
        return countryDTO.get();
    }
}
