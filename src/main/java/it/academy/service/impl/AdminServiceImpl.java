package it.academy.service.impl;

import it.academy.dao.CountryDAO;
import it.academy.dao.StudentDAO;
import it.academy.dao.impl.CountryDAOImpl;
import it.academy.dao.impl.StudentDAOImpl;
import it.academy.dto.request.StudentDTORequest;
import it.academy.dto.response.StudentDTOResponse;
import it.academy.dto.response.StudentInfoResponse;
import it.academy.models.Country;
import it.academy.models.Student;
import it.academy.service.AdminService;
import it.academy.utils.Constants;
import it.academy.utils.ResponseHelper;
import it.academy.utils.StudentConverter;
import it.academy.utils.TransactionHelper;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.*;
import static it.academy.utils.EntityValidator.validateEntity;
import static javax.servlet.http.HttpServletResponse.*;

@Slf4j
public final class AdminServiceImpl implements AdminService {
    private static AdminServiceImpl adminService;
    private final StudentDAO studentDAO = new StudentDAOImpl();
    private final CountryDAO countryDAO = new CountryDAOImpl();
    private final TransactionHelper transactionHelper = TransactionHelper.getTransactionHelper();

    private AdminServiceImpl() {

    }

    public static AdminServiceImpl getInstance() {
        if (adminService == null) {
            adminService = new AdminServiceImpl();
        }
        return adminService;
    }

    @Override
    public List<StudentInfoResponse> getAllStudents() {
        List<Student> studentList = transactionHelper.transaction(studentDAO::readAll);
        return studentList.stream().map(StudentConverter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<StudentInfoResponse> getAllStudents(int page, int count) {
        List<Student> studentList = transactionHelper.transaction(() -> studentDAO.readAll(page, count));
        return studentList.stream().map(StudentConverter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTOResponse createStudent(StudentDTORequest studentDTORequest) {
        studentDTORequest.setId(0);
        Student forSave = StudentConverter.convertToEntity(studentDTORequest);
        Supplier<Student> save = () -> {
            Country country = checkCountry(forSave.getCountry());
            forSave.setCountry(country);
            studentDAO.create(forSave);
            return studentDAO.update(forSave);
        };

        String validationResult = validateEntity(forSave, forSave.getCountry());
        if (!validationResult.isEmpty()) {
            log.warn(Constants.VALIDATION_ERROR, validationResult);
            return ResponseHelper.getStudentResponse(SC_BAD_REQUEST, validationResult);
        }

        Student student = transactionHelper.transaction(save);
        if (student != null) {
            log.info(Constants.STUDENT_SAVED_MESSAGE, student);
            return ResponseHelper.getStudentResponse(SC_CREATED, SUCCESSFULLY_CREATED);
        } else {
            log.error(Constants.SAVE_ERROR, forSave);
            return ResponseHelper.getStudentResponse(SC_INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @Override
    public StudentDTOResponse deleteStudent(Long id) {
        if (studentDAO.read(id) == null) {
            return ResponseHelper.getStudentResponse(SC_NOT_FOUND, STUDENT_NOT_FOUND);
        }
        boolean state = transactionHelper.transaction(() -> studentDAO.delete(id));
        if (state) {
            log.info(Constants.STUDENT_DELETED_MESSAGE, id);
            return ResponseHelper.getStudentResponse(SC_OK, SUCCESSFULLY_DELETED);
        } else {
            log.error(DELETE_ERROR, id);
            return ResponseHelper.getStudentResponse(SC_INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @Override
    public StudentDTOResponse updateStudent(StudentDTORequest studentDTORequest) {
        try {
            transactionHelper.begin();
            Student forUpdate = StudentConverter.convertToEntity(studentDTORequest);

            String validationResult = validateEntity(forUpdate, forUpdate.getCountry());
            if (!validationResult.isEmpty()) {
                log.warn(Constants.VALIDATION_ERROR, validationResult);
                return ResponseHelper.getStudentResponse(SC_BAD_REQUEST, validationResult);
            }

            if (studentDAO.read(forUpdate.getId()) == null) {
                log.warn(STUDENT_NOT_FOUND);
                return ResponseHelper.getStudentResponse(SC_NOT_FOUND, STUDENT_NOT_FOUND);
            }

            Country country = checkCountry(forUpdate.getCountry());
            forUpdate.setCountry(country);
            studentDAO.update(forUpdate);
            transactionHelper.commit();
            log.info(USER_UPDATED_MESSAGE, forUpdate);
        } catch (Exception e) {
            log.error(UPDATE_ERROR, StudentConverter.convertToEntity(studentDTORequest));
            transactionHelper.rollback();
            return ResponseHelper.getStudentResponse(SC_INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE);
        }
        return ResponseHelper.getStudentResponse(SC_OK, SUCCESSFULLY_UPDATED);

    }

    @Override
    public boolean clearBase() {
        return transactionHelper.transaction(studentDAO::clearTable);
    }

    @Override
    public long getCountOfAllStudents() {
        return transactionHelper.transaction(studentDAO::countOfEntitiesInBase);
    }

    private Country checkCountry(Country country) {
        try {
            return countryDAO.readByName(country.getName());
        } catch (NoResultException e) {
            return countryDAO.create(country);
        }
    }
}
