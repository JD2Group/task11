package it.academy;

import it.academy.dao.StudentDAO;
import it.academy.dao.impl.StudentDAOImpl;
import it.academy.dto.StudentDTO;
import it.academy.entities.Address;
import it.academy.entities.Student;
import it.academy.services.StudentService;
import it.academy.services.impl.StudentServiceImpl;
import it.academy.utils.TransactionManager;

import java.util.List;
import java.util.Random;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Test {

    public static void main(String[] args) {
        StudentDAO studentDAO = new StudentDAOImpl();

        List<Student> studentList = IntStream.range(0, 500)
                .mapToObj(i -> buildStudent())
                .collect(Collectors.toList());
        studentList.forEach(s -> {
            Supplier<Student> ss = () ->  studentDAO.save(s);
            TransactionManager.getInstance().executeTransaction(ss);
        });

        Supplier<List<Student>> ss1 = studentDAO::getAll;
        Supplier<List<Student>> ss = () -> studentDAO.getList(3, 30);


        List<Student> students1 = TransactionManager.getInstance().executeTransaction(ss1);
        List<Student> students = TransactionManager.getInstance().executeTransaction(ss);
        IntStream.range(0, students.size())
        .forEach(i -> System.out.println(i + 1 + ")" + students.get(i)));

        StudentService service = new StudentServiceImpl();
//        StudentDTO studentDTO = StudentDTO.builder()
//                .name("name")
//                .surname("surname")
//                .age(1)
//                .mark(1)
//                .city("city")
//                .street("street")
//                .houseNumber(5)
//                .build();
////        service.saveStudent(studentDTO);
//        StudentDTO studentDTO2 = service.findStudent(0L);
//        studentDTO2.setName("testsave");
//        service.saveOrUpdateStudent(studentDTO2);

        System.out.println(service.getMaxPageNumber());

    }

    public static Student buildStudent() {
        return Student.builder()
                .name("Name " + new Random().nextInt(100))
                .surname("Surname " + new Random().nextInt(100))
                .age(new Random().nextInt(51) + 18)
                .address(buildAddress())
                .mark(new Random().nextInt(11))
                .build();
    }

    public static Address buildAddress() {
        return Address.builder()
                .city("City " + new Random().nextInt(100))
                .street("Street " + new Random().nextInt(100))
                .houseNumber(new Random().nextInt(100))
                .build();
    }
}
