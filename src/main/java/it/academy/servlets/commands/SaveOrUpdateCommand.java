package it.academy.servlets.commands;

import it.academy.dto.StudentDTO;
import it.academy.services.StudentService;
import it.academy.services.impl.StudentServiceImpl;
import javax.servlet.http.HttpServletRequest;

public class SaveOrUpdateCommand extends ChangePageCommand {
    private StudentService service = new StudentServiceImpl();

    @Override
    public String execute(HttpServletRequest req) {

        StudentDTO studentDTO = StudentDTO.builder()
                .id(Long.parseLong(req.getParameter("id")))
                .name(req.getParameter("name"))
                .surname(req.getParameter("surname"))
                .age(Integer.parseInt(req.getParameter("age")))
                .mark(Integer.parseInt(req.getParameter("mark")))
                .addressId(Long.parseLong(req.getParameter("addressId")))
                .city(req.getParameter("city"))
                .street(req.getParameter("street"))
                .houseNumber(Integer.parseInt(req.getParameter("house")))
                .build();

        service.saveOrUpdateStudent(studentDTO);

        return changePage(req);
    }
}