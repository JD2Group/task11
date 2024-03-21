<%@ page import="it.academy.dto.StudentDTO" %>
<%--
  Created by IntelliJ IDEA.
  User: Timon
  Date: 20.03.2024
  Time: 12:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Warning</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>
<body>
<% StudentDTO studentDTO = (StudentDTO) request.getAttribute("studentDTO"); %>
<div class="container text-center">

    <h3 class="modal-title">Do you really want to delete this student?
        <br>
        <%=studentDTO.getSurname()%>
        <%=" "%>
        <%=studentDTO.getName()%>
        <br></h3>
    <br>
    <br>
    <form action="readAll" method="get">
        <button class="btn btn-primary" type="submit">Return to list with all students
        </button>
    </form>

    <form action="delete" method="get">
        <input type="hidden" value="<%=studentDTO.getId()%>" name="id">
        <button class="btn btn-outline-danger" type="submit">Delete Mr. <%=studentDTO.getSurname()%>
        </button>
    </form>
</div>
</body>
</html>
