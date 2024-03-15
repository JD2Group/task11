package it.academy.service.impl;

import it.academy.dao.StudentDAO;
import it.academy.dao.impl.StudentDAOImpl;
import it.academy.dto.StudentDTO;
import it.academy.models.Student;
import it.academy.service.AdminService;
import it.academy.util.StudentConverter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

public class AdminServiceImpl implements AdminService {

    private final StudentDAO studentDao = StudentDAOImpl.getInstance();

    @Override
    public List<StudentDTO> getAllStudents() throws Exception {

        List<Student> studentList = new ArrayList<>();
        studentDao.executeInOneTransaction(() -> studentList.addAll(studentDao.getAll()));
        return studentList.stream().map(StudentConverter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public List<StudentDTO> getAllStudents(int page, int count) throws Exception {

        List<Student> studentList = new ArrayList<>();
        studentDao.executeInOneTransaction(() -> studentList.addAll(studentDao.getAll(page, count)));
        return studentList.stream().map(StudentConverter::convertToDTO).collect(Collectors.toList());
    }

    @Override
    public void createStudent(StudentDTO studentDTO) throws Exception {

        studentDao.executeInOneTransaction(() -> studentDao.create(StudentConverter.convertToEntity(studentDTO)));
    }

    @Override
    public void deleteStudent(Long id) throws Exception {

        studentDao.executeInOneTransaction(() -> studentDao.delete(id));
        studentDao.closeManager();
    }

    @Override
    public void updateStudent(StudentDTO studentDTO) throws Exception {

        studentDao.executeInOneTransaction(() -> studentDao.update(StudentConverter.convertToEntity(studentDTO)));
        studentDao.closeManager();
    }

    @Override
    public void clearBase() throws Exception {

        studentDao.executeInOneTransaction(studentDao::clearTable);
        studentDao.closeManager();
    }

    @Override
    public long getCountOfAllStudents() throws Exception {

        AtomicLong count = new AtomicLong(0L);
        studentDao.executeInOneTransaction(() -> count.set(studentDao.countOfEntitiesInBase()));
        return count.get();
    }
}
