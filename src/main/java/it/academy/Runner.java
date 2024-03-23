package it.academy;


import it.academy.dto.request.StudentDTORequest;
import it.academy.service.AdminService;
import it.academy.service.impl.AdminServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Runner {


    public static void main(String[] args) {


        AdminService admin = AdminServiceImpl.getInstance();
        for (int i = 1; i < 100; i++) {
            StudentDTORequest student = StudentDTORequest.builder()
                    .name("ssdfsf" + i)
                    .surname("fesdfdsf" + i)
                    .age(i)
                    .mark(i)
                    .country("country" + i)
                    .city("ughgg" + i)
                    .street("ouihugg" + i)
                    .building(54 + i)
                    .build();

            try {
                admin.createStudent(student);
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
        System.out.println("count: " + count);
        List<StudentDTORequest> dtoList = new ArrayList<>();
        try {
            dtoList = admin.getAllStudents(3, 25);
        } catch (Exception e) {
            System.out.println("Ups.. You havea problem: " + e);
        }
        System.out.println("\nAll entities:");
        dtoList.forEach(System.out::println);
        System.out.println();

        Random random = new Random();
        StudentDTORequest studentDTORequest = dtoList.get(random.nextInt(dtoList.size()));
        studentDTORequest.setAge(99999);
        studentDTORequest.setName("_____________");
        studentDTORequest.setSurname("___________");
        try {
            admin.updateStudent(studentDTORequest);
        } catch (Exception e) {
            e.printStackTrace();
        }


        studentDTORequest = dtoList.get(random.nextInt(dtoList.size()));
        try {
            admin.deleteStudent(studentDTORequest.getId());
            System.out.println(studentDTORequest + "has been deleted!");
        } catch (Exception e) {
            System.out.println(studentDTORequest + "has not delete");
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
