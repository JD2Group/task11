package it.academy;


import it.academy.dao.RoleDAO;
import it.academy.dao.StudentDAO;
import it.academy.dao.impl.RoleDAOImpl;
import it.academy.dao.impl.StudentDAOImpl;
import it.academy.enums.RoleEnum;
import it.academy.models.Role;
import it.academy.models.Student;
import it.academy.service.AdminService;
import it.academy.service.impl.AdminServiceImpl;
import it.academy.utils.DataGenerator;
import it.academy.utils.TransactionHelper;

public class Runner {


    public static void main(String[] args) {

        //DataGenerator.generateCountries();

        StudentDAO studentDAO = new StudentDAOImpl();
        TransactionHelper transactionHelper = TransactionHelper.getTransactionHelper();
        AdminService admin = AdminServiceImpl.getInstance();
        RoleDAO roleDAO = new RoleDAOImpl();
/*        for (int i = 0; i < 1; i++) {
            Student student = DataGenerator.generateStudent();
            transactionHelper.transaction(() -> studentDAO.create(student));
        }

        try {
            System.out.println("Count of student got by .size(): "
                    + admin.getAllStudents().size());
        } catch (Exception e) {
            System.out.println("Ups.. Something went wrong: " + e);
            throw new RuntimeException();
        }

        try {
            System.out.println("Count of student got by special method: "
                    + admin.getCountOfAllStudents());
        } catch (Exception e) {
            System.out.println("Ups.. Something went wrong: " + e);
            throw new RuntimeException();
        }*/

        System.out.println("Time to setup role table.");

        transactionHelper.transaction(() -> roleDAO.create(Role.builder()
                .roleName(RoleEnum.DEFAULT_USER.name())
                .build()));

        System.out.println("\nAll generated successfully!");


    }
}
