package it.academy.service.impl;

import it.academy.dao.StudentDAO;
import it.academy.dao.impl.StudentDAOImpl;
import it.academy.dto.StudentDTO;
import it.academy.models.Student;
import it.academy.service.AdminService;
import it.academy.utils.StudentConverter;
import it.academy.utils.TransactionHelper;

import java.util.List;
import java.util.stream.Collectors;

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
    public List<StudentDTO> getAllStudents() {
        List<Student> studentList = transactionHelper.transaction(studentDAO::readAll);
        return studentList.stream().map(StudentConverter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getAllStudents(int page, int count) {
        List<Student> studentList = transactionHelper.transaction(() -> studentDAO.readAll(page, count));
        return studentList.stream().map(StudentConverter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public boolean createStudent(StudentDTO studentDTO) {
        Student student = transactionHelper.transaction(() -> studentDAO.create(StudentConverter.convertToEntity(studentDTO)));
        return student != null;
    }

    @Override
    public boolean deleteStudent(Long id) {
        return transactionHelper.transaction(() -> studentDAO.delete(id));
    }

    @Override
    public boolean updateStudent(StudentDTO studentDTO) {
        Student student = transactionHelper.transaction(() -> studentDAO.update(StudentConverter.convertToEntity(studentDTO)));
        return student != null;
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
