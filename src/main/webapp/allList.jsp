<%@ page import="it.academy.dto.StudentDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %><%--
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
</head>
<body>

<%--

<c:choose>
    <c:when test="${requestScope.people.size() > 0}">
        <table>
            <tr>
                <th>&#8470;</th>
                <th>Name</th>
                <th>Surname</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="elem" items="${requestScope.people}" varStatus="status">
                <tr>
                    <td>${status.count}</td>
                    <td>${elem.name}</td>
                    <td>${elem.surname}</td>
                    <td>
                        <form method="get" action="${pageContext.request.contextPath}/dispatcher">
                            <input type="hidden" name="command" value="update">
                            <input type="hidden" name="personId" value=${elem.id}>
                            <button type="submit">Edit</button>
                        </form>
                    </td>
                    <td>
                        <form method="post" action="${pageContext.request.contextPath}/dispatcher">
                            <input type="hidden" name="command" value="delete">
                            <input type="hidden" name="personId" value=${elem.id}>
                            <button type="submit">Delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </c:when>
    <c:otherwise>
        <div class="emptyList">
            <h1>No people added yet!</h1>
        </div>
    </c:otherwise>
</c:choose>
<form class="addForm" method="get" action="${pageContext.request.contextPath}/dispatcher">
    <input type="hidden" name="command" value="create">
    <button class="red" type="submit"><i class="icon ion-md-lock"></i>Add new person</button>
</form>
--%>


<table>
    <tr>
        <th>No</th>
        <th>Name</th>
        <th>Surname</th>
        <th>AGE</th>
        <th>City</th>
        <th>Street</th>
        <th>Building</th>
        <th>Mark</th>
        <th></th>
        <th></th>
        <th></th>
    </tr>

    <%
        Object atribute = request.getAttribute("listDto");
        List<StudentDTO> listDto = new ArrayList<>();
        try {
            listDto = ((List<StudentDTO>) atribute);
        } catch (Exception ignored) {
        }


    %>
    <%for (int i = 0; i < listDto.size(); i++) {%>
    <%StudentDTO studentDTO = listDto.get(i);%>
    <tr>
        <td><%=(i + 1)%>
        </td>
        <td><%=studentDTO.getName()%>
        </td>
        <td><%=studentDTO.getSurname()%>
        </td>
        <td><%=studentDTO.getAge()%>
        </td>
        <td><%=studentDTO.getCity()%>
        </td>
        <td><%=studentDTO.getStreet()%>
        </td>
        <td><%=studentDTO.getBuilding()%>
        </td>
        <td><%=studentDTO.getMark()%>
        </td>

        <%String s = String.valueOf(studentDTO.getId());%>

        <td>
            <form action="update" method="get">
                <%--  <%request.setAttribute("id",Long.parseLong(s));%>--%>
                <input type="hidden" value="<%=s%>" name="id">
                <button type="submit">Edit</button>
            </form>
        </td>

        <td>
            <form action="delete" method="get">
                <%-- <%request.setAttribute("id",Long.parseLong(s));%>--%>
                <input type="hidden" value="<%=s%>" name="id">
                <button type="submit">Delete</button>
            </form>
        </td>

    </tr>
    <%
        }
    %>
</table>

<form action="create" method="get">
    <button class="red" type="submit"><i class="icon ion-md-lock"></i>Add new person</button>
</form>
</body>
</html>
