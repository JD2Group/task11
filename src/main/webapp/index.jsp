<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <meta charset="UTF-8">
        <%--    <meta name="viewport" content="width=device-width, initial-scale=1.0">--%>
        <link rel="stylesheet" href="css/style.css">
        <title>Стартовая страница</title>
    </head>

    <body>
        <%
            response.sendRedirect("list?command=show_students&&page=1");
        %>
    </body>

</html>