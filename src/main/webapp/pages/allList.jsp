<%@ page import="it.academy.dto.StudentDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="static it.academy.util.Constants.*" %>
<%@ page import="java.util.Optional" %>
<%--
  Created by IntelliJ IDEA.
  User: Timon
  Date: 15.03.2024
  Time: 16:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>List of all students</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>

<body>
<%
    String action = "readAll";
    Object atribute = request.getAttribute("listDto");
    Object countAtr = session.getAttribute("countOnPage");
    Object pageAtr = session.getAttribute("currentPage");

    Integer countOnPage = (Integer) ((countAtr == null ?
            DEFAULT_COUNT_ON_PAGE : countAtr));
    Integer currentPage = (Integer) ((pageAtr == null ?
            DEFAULT_FIRST_PAGE_NUMBER : pageAtr));

    List<StudentDTO> listDto = new ArrayList<>();
    try {
        listDto = ((List<StudentDTO>) atribute);
    } catch (Exception e) {
        request.setAttribute("exception", e);
        response.sendRedirect("/pages/exception.jsp");
    }
%>
<div class="container text-center">
    <%@ include file="../start_end/showOnPage.jsp" %>
</div>
<br>

<div class="container text-center">
    <%@ include file="../start_end/pagination.jsp" %>
</div>
<br>

<div class="container text-center">
    <%@ include file="../start_end/tableWithStudents.jsp" %>
</div>
<br>

<div class="container text-center">
    <%@ include file="../start_end/createStudent.jsp" %>
</div>
<br>
<br>

<div class="container text-center">
    <%@ include file="../start_end/pagination.jsp" %>
</div>

<div class="container text-center">
    <table>
        <tr>
            <td>
                <%@ include file="../start_end/countries.jsp" %>
            </td>
            <td>
                <%@ include file="../start_end/exit.jsp" %>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
