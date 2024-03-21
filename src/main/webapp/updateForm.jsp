<%@ page import="it.academy.dto.StudentDTO" %><%--
  Created by IntelliJ IDEA.
  User: Timon
  Date: 15.03.2024
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Update current student</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>
<body>
<div class="container text-center">
    <%StudentDTO studentDTO = (StudentDTO) request.getAttribute("studentDTO");%>

    <form action="update" method="post">
        <label>
            <input type="hidden" value="<%=studentDTO.getId()%>" name="id">
        </label><br/>

        Input surname:<label>
        <input type="text" value="<%=studentDTO.getSurname()%>" name="surname">
    </label><br/>
        <br>

        Input name:<label>
        <input type="text" value="<%=studentDTO.getName()%>" name="name">
    </label><br/>
        <br>

        Input age:<label>
        <input type="text" value="<%=studentDTO.getAge()%>" name="age">
    </label><br/>
        <br>

        Input city:<label>
        <input type="text" value="<%=studentDTO.getCity()%>" name="city">
    </label><br/>
        <br>

        Input street:<label>
        <input type="text" value="<%=studentDTO.getStreet()%>" name="street">
    </label><br/>
        <br>

        Input building:<label>
        <input type="text" value="<%=studentDTO.getBuilding()%>" name="building">
    </label><br/>
        <br>

        Input mark:<label>
        <input type="text" value="<%=studentDTO.getMark()%>" name="mark">
    </label><br/>
        <br>
        <br>

        <button class="btn btn-primary" type="submit">Update Mr. <%=studentDTO.getSurname()%>
        </button>
    </form>

    <form action="readAll" method="get">
        <button class="btn btn-secondary" type="submit">Return to list with all students</button>
    </form>
</div>
</body>
</html>

