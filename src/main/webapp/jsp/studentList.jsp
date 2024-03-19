<%@ page import="it.academy.dto.StudentDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="static it.academy.utils.Constants.PAGE_ATTRIBUTE" %>
<%@ page import="static it.academy.utils.Constants.STUDENTS_ATTRIBUTE" %>
<%@ page import="static it.academy.utils.Constants.*" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
    <meta charset="UTF-8">
    <%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <title>Список студентов</title>
</head>
<body>
<section>
    <div class=" container">

        <%
            int pageNumber = (int) request.getAttribute(PAGE_ATTRIBUTE);
            List<StudentDTO> list = (List<StudentDTO>) request.getAttribute(STUDENTS_ATTRIBUTE);
        %>


        <h1>Список студентов</h1>
        <form action="list" method="post">
            <input type="hidden" name="command" value="find_students">
            <input type="hidden" name="page" value="<%=pageNumber%>">
            <input class="search" type="search" name="param" placeholder="Поиск по студентам">
            <select class="filter" name="filter" size="1">
                <option selected value="<%=STUDENT_NAME%>">Имя</option>
                <option selected value="<%=STUDENT_SURNAME%>">Фамилия</option>
                <option selected value="<%=STUDENT_AGE%>">Возраст</option>
                <option selected value="<%=STUDENT_MARK%>">Оценка</option>
                <option selected value="<%=STUDENT_ADDRESS%>">Адрес</option>
            </select>
            <input class="button light" type="submit" value="Найти">
        </form>
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
                        <input type="hidden" name="page" value="<%=(int) request.getAttribute(PAGE_ATTRIBUTE) == 1?
                                                                        (int) request.getAttribute(MAX_PAGE_ATTRIBUTE)
                                                                        : (int) request.getAttribute(PAGE_ATTRIBUTE) - 1%>">
                        <input class="button light" type="submit" name="button" value="Предыдущая">
                    </form>

                    <p><%=pageNumber%></p>

                    <form action="list" method="post">
                        <input type="hidden" name="command" value="show_students">
                        <input type="hidden" name="page" value="<%=(int) request.getAttribute(PAGE_ATTRIBUTE) == (int) request.getAttribute(MAX_PAGE_ATTRIBUTE)?
                                                                          1 : (int) request.getAttribute(PAGE_ATTRIBUTE) + 1%>">
                        <input class="button light" type="submit" name="button" value="Следующая">
                    </form>
                </div>
    </div>
</section>
</body>