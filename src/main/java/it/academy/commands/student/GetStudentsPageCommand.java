package it.academy.commands.student;

import it.academy.commands.Command;
import it.academy.dto.request.StudentDTORequest;
import it.academy.dto.response.StudentInfoResponse;
import it.academy.service.AdminService;
import it.academy.service.impl.AdminServiceImpl;
import it.academy.utils.Constants;
import it.academy.utils.ResponseHelper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class GetStudentsPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request) {
        if (request.getParameter("page").isEmpty() || request.getParameter("students_per_page").isEmpty()){
            return null;
        }
        AdminService adminService = AdminServiceImpl.getInstance();
        int numPage = Integer.parseInt(request.getParameter("page"));
        int countOfStudentsPerPage = Integer.parseInt(request.getParameter("students_per_page"));
        List<StudentInfoResponse> outList = adminService.getAllStudents(numPage, countOfStudentsPerPage);
        return Constants.GSON.toJson(outList);
    }
}
