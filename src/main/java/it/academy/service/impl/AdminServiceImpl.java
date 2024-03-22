package it.academy.service.impl;

import it.academy.dao.StudentDAO;
import it.academy.dao.impl.StudentDAOImpl;
import it.academy.dto.request.StudentDTORequest;
import it.academy.dto.response.StudentDTOResponse;
import it.academy.models.Student;
import it.academy.service.AdminService;
import it.academy.utils.ResponseHelper;
import it.academy.utils.StudentConverter;
import it.academy.utils.TransactionHelper;

import java.util.List;
import java.util.stream.Collectors;

import static it.academy.utils.Constants.*;
import static it.academy.utils.EntityValidator.validateStudent;
import static javax.servlet.http.HttpServletResponse.*;

public final class AdminServiceImpl implements AdminService {
    private static AdminServiceImpl adminService;
    private final StudentDAO studentDAO = new StudentDAOImpl();
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
    public List<StudentDTORequest> getAllStudents() {
        List<Student> studentList = transactionHelper.transaction(studentDAO::readAll);
        return studentList.stream().map(StudentConverter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<StudentDTORequest> getAllStudents(int page, int count) {
        List<Student> studentList = transactionHelper.transaction(() -> studentDAO.readAll(page, count));
        return studentList.stream().map(StudentConverter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public StudentDTOResponse createStudent(StudentDTORequest studentDTORequest) {
        studentDTORequest.setId(0);
        Student forSave = StudentConverter.convertToEntity(studentDTORequest);

        String validationResult = validateStudent(forSave);
        if (validationResult != null){
            return ResponseHelper.getStudentResponse(SC_BAD_REQUEST, validationResult);
        }

        Student student = transactionHelper.transaction(() -> studentDAO.create(forSave));
        if (student != null) {
            return ResponseHelper.getStudentResponse(SC_CREATED, SUCCESSFULLY_CREATED);
        } else {
            return ResponseHelper.getStudentResponse(SC_INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @Override
    public StudentDTOResponse deleteStudent(Long id) {
        if (studentDAO.read(id)== null) {
            return ResponseHelper.getStudentResponse(SC_NOT_FOUND, STUDENT_NOT_FOUND);
        }
        boolean state = transactionHelper.transaction(() -> studentDAO.delete(id));
        if (state) {
            return ResponseHelper.getStudentResponse(SC_OK, SUCCESSFULLY_DELETED);
        } else {
            return ResponseHelper.getStudentResponse(SC_INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE);
        }
    }

    @Override
    public StudentDTOResponse updateStudent(StudentDTORequest studentDTORequest) {
        try {
            transactionHelper.begin();
            Student forUpdate = StudentConverter.convertToEntity(studentDTORequest);
            String validationResult = validateStudent(forUpdate);
            if (validationResult != null){
                return ResponseHelper.getStudentResponse(SC_BAD_REQUEST, validationResult);
            }
            if (studentDAO.read(forUpdate.getId()) == null) {
                return ResponseHelper.getStudentResponse(SC_NOT_FOUND, STUDENT_NOT_FOUND);
            }
            studentDAO.update(forUpdate);
            transactionHelper.commit();
        } catch (Exception e){
            transactionHelper.rollback();
            return ResponseHelper.getStudentResponse(SC_INTERNAL_SERVER_ERROR, INTERNAL_SERVER_ERROR_MESSAGE);
        }
        return ResponseHelper.getStudentResponse(OK_STATUS_CODE, SUCCESSFULLY_UPDATED);

    }

    @Override
    public boolean clearBase() {
        return transactionHelper.transaction(studentDAO::clearTable);
    }

    @Override
    public long getCountOfAllStudents() {
        return transactionHelper.transaction(studentDAO::countOfEntitiesInBase);
    }
}
