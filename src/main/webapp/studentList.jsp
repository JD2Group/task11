<%@ page import="it.academy.dto.StudentDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <link rel="stylesheet" href="css/style.css">
    <title>Список студентов</title>
</head>
<body>
<section>
    <div class=" container">
        <h1>Список студентов</h1>
    </div>

    <div class="container">

        <table>
            <tr>
                <th>Имя</th>
                <th>Фамилия</th>
                <th>Возраст</th>
                <th>Оценка</th>
                <th>Город</th>
                <th>Улица</th>
                <th>Дом</th>
                <th class="menu">Управление</th>
            </tr>


            <%
                int pageNumber = (int) request.getAttribute("page");
                List<StudentDTO> list = (List<StudentDTO>) request.getAttribute("students");
            %>
            <% for(StudentDTO student : list) { %>
            <tr>
                <td><%=student.getName()%></td>
                <td><%=student.getSurname()%></td>
                <td class="number"><%=student.getAge()%></td>
                <td class="number"><%=student.getMark()%></td>
                <td><%=student.getCity()%></td>
                <td><%=student.getStreet()%></td>
                <td class="number"><%=student.getHouseNumber()%></td>
                <td>
                    <div class="button-container">
                        <form action="list" method="post">
                            <input type="hidden" name="command" value="change_student">
                            <input type="hidden" name="id" value="<%=student.getId()%>">
                            <input type="hidden" name="addressId" value="<%=student.getAddressId()%>">
                            <input type="hidden" name="page" value="<%=pageNumber%>">
                            <input class="button" type="submit" value="Изменить">
                        </form>
                        <form action="list" method="post">
                            <input type="hidden" name="command" value="delete_student">
                            <input type="hidden" name="id" value="<%=student.getId()%>">
                            <input type="hidden" name="page" value="<%=pageNumber%>">
                            <input class="button" type="submit" value="Удалить">
                        </form>
                    </div>
                </td>
            </tr>
            <% } %>
        </table>

                <div class="footer">
                    <form action="list" method="post">
                        <input type="hidden" name="command" value="change_student">
                        <input type="hidden" name="id" value="0">
                        <input type="hidden" name="addressId" value="0">
                        <input type="hidden" name="page" value="<%=pageNumber%>">
                        <input class="button add" type="submit" value="Добавить">
                    </form>
                </div>

                <div class="button-container">
                    <form action="list" method="post">
                        <input type="hidden" name="command" value="show_students">
                        <input type="hidden" name="page" value="<%=(int) request.getAttribute("page") == 1?
                                                                        (int) request.getAttribute("maxPage")
                                                                        : (int) request.getAttribute("page") - 1%>">
                        <input class="button light" type="submit" name="button" value="Предыдущая">
                    </form>

                    <p><%=pageNumber%></p>

                    <form action="list" method="post">
                        <input type="hidden" name="command" value="show_students">
                        <input type="hidden" name="page" value="<%=(int) request.getAttribute("page") == (int) request.getAttribute("maxPage")?
                                                                          1 : (int) request.getAttribute("page") + 1%>">
                        <input class="button light" type="submit" name="button" value="Следующая">
                    </form>
                </div>
    </div>
</section>
</body>