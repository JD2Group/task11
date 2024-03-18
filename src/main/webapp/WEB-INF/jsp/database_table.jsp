<%@ page import="it.academy.service.impl.AdminServiceImpl" %>
<%@ page import="it.academy.dto.StudentDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF8" %>

<table class="database_table">
  <thead id="student_table_head">
  <tr>
    <th></th>
    <th key="name">Имя</th>
    <th key="surname">Фамилия</th>
    <th key="age">Возраст</th>
    <th key="mark">Оценка</th>
    <th key="city">Город</th>
    <th key="street">Улица</th>
    <th key="building">Номер дома</th>
  </tr>
  </thead>
  <tbody>
  <%
    AdminServiceImpl adminService = AdminServiceImpl.getInstance();
    String pageNum = request.getParameter("page");
    //String studentId = request.getParameter("student");
    List<StudentDTO> studentList;
    if (pageNum != null){
      studentList = adminService.getAllStudents(Integer.parseInt(pageNum), 10);
    }else {
      studentList = adminService.getAllStudents();
    }
    int i = 0;
    for (StudentDTO s : studentList) {
      i++;
      out.println("<tr onclick=\"completeInfo(this)\">");
      out.println(String.format("<td >%d</td>", i));
      out.println(String.format("<td>%s</td>", s.getName()));
      out.println(String.format("<td>%s</td>", s.getSurname()));
      out.println(String.format("<td>%d</td>", s.getAge()));
      out.println(String.format("<td>%d</td>", s.getMark()));
      out.println(String.format("<td>%s</td>", s.getCity()));
      out.println(String.format("<td>%s</td>", s.getStreet()));
      out.println(String.format("<td>%s</td>", s.getBuilding()));
      out.println("</tr>");
    }
  %>
  </tbody>
</table>
