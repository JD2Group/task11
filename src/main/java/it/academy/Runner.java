package it.academy;

import it.academy.dto.StudentDTO;
import it.academy.service.AdminService;
import it.academy.service.impl.AdminServiceImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Runner {


    public static void main(String[] args) {

        AdminService admin = new AdminServiceImpl();
        for (int i = 0; i < 100; i++) {
            StudentDTO student = StudentDTO.builder()
                                     .name("s" + i)
                                     .surname("fe" + i)
                                     .age(i)
                                     .city("ugh" + i)
                                     .street("ouihu" + i)
                                     .building(54 + i)
                                     .build();

            try {
                admin.createStudent(student);
            } catch (IOException e) {
                System.out.println(student + "not saved. Cause problem with connection: " + e);
            } catch (Exception e) {
                System.out.println(student + "not saved. Cause: " + e);
            }
        }


        try {
            System.out.println(admin.getAllStudents().size());
        } catch (Exception e) {
            System.out.println("Ups.. You havea problem: " + e);
        }
        long count = 0L;
        try {
            count = admin.getCountOfAllStudents();
        } catch (Exception e) {
            System.out.println("Ups.. You havea problem: " + e);
        }
        System.out.println("count: "+count);
        List<StudentDTO> dtoList = new ArrayList<>();
        try {
            dtoList = admin.getAllStudents(3, 25);
        } catch (Exception e) {
            System.out.println("Ups.. You havea problem: " + e);
        }
        System.out.println("\nAll entities:");
        dtoList.forEach(System.out::println);
        System.out.println();

        Random random = new Random();
        StudentDTO studentDTO = dtoList.get(random.nextInt(dtoList.size()));
        studentDTO.setAge(99999);
        studentDTO.setName("_____________");
        studentDTO.setSurname("___________");
        try {
            admin.updateStudent(studentDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }


        studentDTO = dtoList.get(random.nextInt(dtoList.size()));
        try {
            admin.deleteStudent(studentDTO.getId());
            System.out.println(studentDTO + "has been deleted!");
        } catch (Exception e) {
            System.out.println(studentDTO + "has not delete");
            System.out.println("Ups.. You havea problem: " + e);
        }


        //DELET ALL
       /* try {
            admin.clearBase();
        } catch (Exception e) {
            e.printStackTrace();
        }*/
    }
}
