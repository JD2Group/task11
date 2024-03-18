package it.academy.services.impl;

import it.academy.dao.StudentDAO;
import it.academy.dao.impl.StudentDAOImpl;
import it.academy.dto.StudentDTO;
import it.academy.entities.Student;
import it.academy.services.StudentService;
import it.academy.utils.Converter;
import it.academy.utils.Builder;
import it.academy.utils.TransactionManager;

import java.util.List;
import java.util.function.Supplier;

import static it.academy.utils.Constants.LIST_SIZE;

public class StudentServiceImpl implements StudentService {
    private TransactionManager manager = TransactionManager.getInstance();
    private StudentDAO studentDAO = new StudentDAOImpl();

    @Override
    public List<StudentDTO> getAllStudents() {
        Supplier<List<Student>> getAll = studentDAO::getAll;
        List<StudentDTO> result = Converter.convertListToDTO(manager.executeTransaction(getAll));
        manager.closeEntityManager();
        return result;
    }

    @Override
    public List<StudentDTO> getStudentsForPage(int pageNumber, int listSize) {
        Supplier<List<Student>> getList = () -> studentDAO.getList(pageNumber, listSize);
        List<StudentDTO> result = Converter.convertListToDTO(manager.executeTransaction(getList));
        manager.closeEntityManager();
        return result;
    }

    @Override
    public boolean saveOrUpdateStudent(StudentDTO studentDTO) {
        Supplier<Student> save = () -> {
            Student student = Converter.convertToEntity(studentDTO);
            if (studentDAO.get(studentDTO.getId()) == null) {
                studentDAO.save(student);
            } else {
                studentDAO.update(student);
            }
            return student;
        };
        boolean result = manager.executeTransaction(save) != null;
        manager.closeEntityManager();
        return result;
    }

    @Override
    public StudentDTO findStudent(Long id) {
        Supplier<Student> find = () -> studentDAO.get(id);
        Student result = manager.executeTransaction(find);
        result = result == null ? Builder.buildStudent() : result;
        manager.closeEntityManager();
        return Converter.convertToDTO(result);
    }

    @Override
    public List<StudentDTO> findStudentsByParameter(String parameter) {
        Supplier<List<Student>> find = () -> studentDAO.getByParameter(parameter);
        List<StudentDTO> result = Converter.convertListToDTO(manager.executeTransaction(find));
        manager.closeEntityManager();
        return result;
    }

    @Override
    public boolean deleteStudent(Long id) {
        Supplier<Boolean> update = () -> studentDAO.delete(id);
        boolean result = manager.executeTransaction(update);
        manager.closeEntityManager();
        return result;
    }

    @Override
    public int getMaxPageNumber() {
        Supplier<Long> getTableSize = studentDAO::getNumberOfEntries;
        int result = (int) Math.ceil(((double) manager.executeTransaction(getTableSize) / LIST_SIZE));
        manager.closeEntityManager();
        return result;
    }

}
