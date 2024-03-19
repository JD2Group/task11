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
</head>
<body>

<%StudentDTO studentDTO = (StudentDTO) request.getAttribute("studentDTO");%>

<form action="update" method="post">
   <label>
    <input type="hidden" value="<%=studentDTO.getId()%>" name="id">
    <%--<input name="name" type="text">--%>
</label><br/>
    Input name:<label>
    <input type="text" value="<%=studentDTO.getName()%>" name="name">
    <%--<input name="name" type="text">--%>
</label><br/>
    Input surname:<label>
    <input type="text" value="<%=studentDTO.getSurname()%>" name="surname">
    <%-- <input name="surname" type="text">--%>
</label><br/>
    Input age:<label>
    <input type="text" value="<%=studentDTO.getAge()%>" name="age">
    <%-- <input name="age" type="text">--%>
</label><br/>
    Input city:<label>
    <input type="text" value="<%=studentDTO.getCity()%>" name="city">
    <%-- <input name="city" type="text">--%>
</label><br/>
    Input street:<label>
    <input type="text" value="<%=studentDTO.getStreet()%>" name="street">
    <%-- <input name="street" type="text">--%>
</label><br/>
    Input building:<label>
    <input type="text" value="<%=studentDTO.getBuilding()%>" name="building">
    <%-- <input name="building" type="text">--%>
</label><br/>
    Input mark:<label>
    <input type="text" value="<%=studentDTO.getMark()%>" name="mark">
    <%--  <input name="mark" type="text">--%>
</label><br/>

    <button class="red" type="submit"><i class="icon ion-md-lock"></i>Update this student</button>
</form>

<form action="readAll" method="get">
    <button class="red" type="submit"><i class="icon ion-md-lock"></i>Return to list with all students</button>
</form>

</body>
</html>

