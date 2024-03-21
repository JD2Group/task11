<%@ page import="it.academy.dto.StudentDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.util.List" %>
<%@ page import="static it.academy.util.Constants.*" %>
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
    } catch (Exception ignored) {
    }

%>
<div class="container text-center">
    <table>
        <tr>
            <td>current count of rows on page:<%=countOnPage%>
            </td>
            <td></td>
            <td>
                <form action="readAll" method="get">
                    <input type="hidden" value="3" name="countOnPage">
                    <button class="btn btn-light" type="submit"> show 3</button>
                </form>
            </td>
            <td>
                <form action="readAll" method="get">
                    <input type="hidden" value="5" name="countOnPage">
                    <button class="btn btn-light" type="submit"> show 5</button>
                </form>
            </td>
            <td>
                <form action="readAll" method="get">
                    <input type="hidden" value="10" name="countOnPage">
                    <button class="btn btn-light" type="submit">show 10</button>
                </form>
            </td>
        </tr>
    </table>
    <br>
    <table>
        <tr>
            <th>No</th>
            <th>Surname</th>
            <th>Name</th>
            <th>AGE</th>
            <th>City</th>
            <th>Street</th>
            <th>Building</th>
            <th>Mark</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>

        <%for (int i = 0; i < listDto.size(); i++) {%>
        <%StudentDTO studentDTO = listDto.get(i);%>
        <tr>
            <td><%=(i + 1)%>
            </td>
            <td><%=studentDTO.getSurname()%>
            </td>
            <td><%=studentDTO.getName()%>
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
                    <input type="hidden" value="<%=s%>" name="id">
                    <button class="btn btn-light" type="submit">Edit</button>
                </form>
            </td>
            <td>
                <form action="delete" method="post">
                    <input type="hidden" value="<%=s%>" name="id">
                    <button class="btn btn-light" type="submit">Delete</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>

    <br>

    <form action="create" method="get">
        <button class="btn btn-success" type="submit">Add new person</button>
    </form>

    <br>
    <br>

    <table>
        <tr>
            <td>
                <form action="readAll" method="get">
                    <input type="hidden" value="<%=DEFAULT_FIRST_PAGE_NUMBER%>" name="currentPage">
                    <button class="btn btn-light" type="submit">First</button>
                </form>
            </td>
            <td>
                <form action="readAll" method="get">
                    <input type="hidden" value="<%=currentPage-1%>" name="currentPage">
                    <button class="btn btn-outline-secondary" type="submit">Previous</button>
                </form>
            </td>
            <td>
                <p><%=currentPage%>
                </p>
            </td>
            <td>
                <form action="readAll" method="get">
                    <input type="hidden" value="<%=currentPage+1%>" name="currentPage">
                    <button class="btn btn-outline-primary" type="submit">Next</button>
                </form>
            </td>
            <td>
                <form action="readAll" method="get">
                    <input type="hidden" value="<%=DEFAULT_LAST_PAGE_NUMBER%>" name="currentPage">
                    <button class="btn btn-light" type="submit">Last</button>
                </form>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
