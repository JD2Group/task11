package it.academy;


import it.academy.dao.StudentDAO;
import it.academy.dao.impl.StudentDAOImpl;
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
        for (int i = 0; i < 3; i++) {
            Student student = DataGenerator.generateStudent();
            transactionHelper.transaction(() -> studentDAO.create(student));
        }

        try {
            System.out.println("Count of student got by .size()"
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
        }

        System.out.println("\nAll generated successfully!");

    }
}
