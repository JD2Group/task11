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

public final class AdminServiceImpl implements AdminService {
    private static AdminServiceImpl adminService;
    private final StudentDAO studentDAO = new StudentDAOImpl();
    private final TransactionHelper transactionHelper = TransactionHelper.getTransactionHelper();
    private AdminServiceImpl(){

    }
    public static AdminServiceImpl getInstance(){
        if (adminService == null){
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
        if (!studentDTORequest.validate(METHOD_SAVE)) {
            return ResponseHelper.getStudentResponse(BAD_REQUEST_STATUS_CODE, "Try to save existing student.");
        }
        Student student = transactionHelper.transaction(() -> studentDAO.create(StudentConverter.convertToEntity(studentDTORequest)));
        if(student != null){
            return ResponseHelper.getStudentResponse(CREATED_STATUS_CODE, SUCCESSFULLY_CREATED);
        }else {
            return ResponseHelper.getStudentResponse(INTERNAL_SERVER_ERROR_STATUS_CODE, INTERNAL_SERVER_ERORR_MESSAGE);
        }
    }

    @Override
    public StudentDTOResponse deleteStudent(Long id) {
        if (id == 0) {
            return ResponseHelper.getStudentResponse(BAD_REQUEST_STATUS_CODE, "Try to delete non-existent student.");
        }
        boolean state = transactionHelper.transaction(() -> studentDAO.delete(id));
        if (state){
            return ResponseHelper.getStudentResponse(OK_STATUS_CODE, SUCCESSFULLY_DELETED);
        }else {
            return ResponseHelper.getStudentResponse(INTERNAL_SERVER_ERROR_STATUS_CODE, INTERNAL_SERVER_ERORR_MESSAGE);
        }
    }

    @Override
    public StudentDTOResponse updateStudent(StudentDTORequest studentDTORequest) {
        if (!studentDTORequest.validate(METHOD_UPDATE)) {
            return ResponseHelper.getStudentResponse(BAD_REQUEST_STATUS_CODE, "Try to update non-existent student.");
        }
        Student student = transactionHelper.transaction(() -> studentDAO.update(StudentConverter.convertToEntity(studentDTORequest)));
        if(student != null){
            return ResponseHelper.getStudentResponse(OK_STATUS_CODE, SUCCESSFULLY_UPDATED);
        }else {
            return ResponseHelper.getStudentResponse(INTERNAL_SERVER_ERROR_STATUS_CODE, INTERNAL_SERVER_ERORR_MESSAGE);
        }
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
