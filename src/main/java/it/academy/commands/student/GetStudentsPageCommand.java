package it.academy.commands.student;

import com.google.gson.JsonSyntaxException;
import it.academy.commands.Command;
import it.academy.dto.response.StudentInfoResponse;
import it.academy.service.AdminService;
import it.academy.service.impl.AdminServiceImpl;
import it.academy.utils.Constants;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetStudentsPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            if (request.getParameter("page").isEmpty() || request.getParameter("students_per_page").isEmpty()) {
                return null;
            }
            AdminService adminService = AdminServiceImpl.getInstance();
            int numPage = Integer.parseInt(request.getParameter("page"));
            int countOfStudentsPerPage = Integer.parseInt(request.getParameter("students_per_page"));
            List<StudentInfoResponse> outList = adminService.getAllStudents(numPage, countOfStudentsPerPage);
            Map<String, Object> responseMap = new HashMap<>();
            responseMap.put("students", outList);
            Long count = adminService.getCountOfAllStudents();
            responseMap.put("countOfStudents", count);
            return Constants.GSON.toJson(responseMap);
        } catch (Exception e){
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, e.getMessage());
            return null;
        }
    }
}
