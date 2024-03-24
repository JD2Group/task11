<%@ page import="it.academy.service.impl.AdminServiceImpl" %>
<%@ page import="it.academy.dto.request.StudentDTORequest" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF8" %>

<table class="database_table" onload="fetchTable(1, 10)">
    <thead id="student_table_head">
    <tr>
        <th></th>
        <th key="name">Имя</th>
        <th key="surname">Фамилия</th>
        <th key="age">Возраст</th>
        <th key="mark">Оценка</th>
        <th key="country">Страна</th>
        <th key="city">Город</th>
        <th key="street">Улица</th>
        <th key="building">Номер дома</th>
    </tr>
    </thead>
    <tbody id="student_table_body">
    <%
        AdminServiceImpl adminService = AdminServiceImpl.getInstance();
        String pageNum = request.getParameter("page");
        //String studentId = request.getParameter("student");
        List<StudentDTORequest> studentList;
        if (pageNum != null) {
            studentList = adminService.getAllStudents(Integer.parseInt(pageNum), 10);
        } else {
            studentList = adminService.getAllStudents();
        }
        int i = 0;
        for (StudentDTORequest s : studentList) {
            i++;
            out.println("<tr key='" + s.getId() + "' onclick=\"completeInfo(this)\">");
            out.println(String.format("<td>%d</td>", i));
            out.println(String.format("<td>%s</td>", s.getName()));
            out.println(String.format("<td>%s</td>", s.getSurname()));
            out.println(String.format("<td>%d</td>", s.getAge()));
            out.println(String.format("<td>%d</td>", s.getMark()));
            out.println(String.format("<td>%s</td>", s.getCountry()));
            out.println(String.format("<td>%s</td>", s.getCity()));
            out.println(String.format("<td>%s</td>", s.getStreet()));
            out.println(String.format("<td>%s</td>", s.getBuilding()));
            out.println("</tr>");
        }
    %>
    </tbody>
</table>
