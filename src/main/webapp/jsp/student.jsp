<%@ page import="it.academy.dto.StudentDTO" %>
<%@ page import="static it.academy.utils.Constants.STUDENT_ATTRIBUTE" %>
<%@ page import="static it.academy.utils.Constants.PAGE_ATTRIBUTE" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <title>Студент</title>
    </head>
    <body>

        <section>

            <%
                StudentDTO student = (StudentDTO) request.getAttribute(STUDENT_ATTRIBUTE);
            %>

            <div class="container hidden" id="student">

                <div class="button-container">
                    <h1>Студент</h1>
                    <form action = "list" method="post" id="exit">
                        <input type="hidden" name="page" value="<%=request.getAttribute(PAGE_ATTRIBUTE)%>">
                        <input type="hidden" name="command" value="show_students">
                    </form>
                </div>

                <form action = "list" method="post" id="save">
                    <div class="form-container">
                        <input type="hidden" name="command" value="save_student" id="action">
                        <input type="hidden" name="id" value="<%=student.getId()%>">
                        <input type="hidden" name="page" value="<%=request.getAttribute(PAGE_ATTRIBUTE)%>">
                        <label class="form-el" for="name">Имя</label>
                        <input class="form-el" required type="text" name="name" placeholder="Введите имя" value="<%=student.getName()%>" id="name"
                                pattern="[a-zA-Zа-яА-Я]+">
                        <label class="form-el" for="surname">Фамилия</label>
                        <input class="form-el" required type="text" name="surname" placeholder="Введите фамилию" value="<%=student.getSurname()%>" id="surname"
                               pattern="[a-zA-Zа-яА-Я]+-?[a-zA-Zа-яА-Я]*">
                        <label class="form-el" for="age">Возраст</label>
                        <input class="form-el" required type="number" name="age" placeholder="Введите возраст" value="<%=student.getAge()%>"
                               id="age" min="15" step="1" max="100">
                        <label class="form-el" for="mark">Оценка</label>
                        <input class="form-el" type="number" name="mark" placeholder="Введите оценку" value="<%=student.getMark()%>"
                               id="mark" min="0" step="1" max="10">
                        <label class="form-el" for="city">Город</label>
                        <input class="form-el" required type="text" name="city" placeholder="Введите город" value="<%=student.getCity()%>" id="city"
                               pattern="[a-zA-Zа-яА-Я]+-?[a-zA-Zа-яА-Я]*">
                        <label class="form-el" for="street">Улица</label>
                        <input class="form-el" required type="text" name="street" placeholder="Введите улицу" value="<%=student.getStreet()%>" id="street"
                               pattern="[a-zA-Zа-яА-Я]+d*-?[a-zA-Zа-яА-Я]*">
                        <label class="form-el" for="house">Дом</label>
                        <input class="form-el" required type="number" name="house" placeholder="Введите номер дома" value="<%=student.getHouseNumber()%>"
                               id="house" min="1" step="1">
                    </div>

                    <div class="button-container">
                        <input class="button" type="submit" value="Сохранить" form="save"/>
                        <input class="button" type="submit" value="Отменить" form="exit"/>
                    </div>
                </form>

            </div>

        </section>

        <script rel="script" src="${pageContext.request.contextPath}/js/form.js"></script>

    </body>
</html>
