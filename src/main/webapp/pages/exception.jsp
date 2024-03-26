<%--
  Created by IntelliJ IDEA.
  User: Timon
  Date: 15.03.2024
  Time: 16:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Exception</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>
<body>
<div class="container text-center">
    <p>Ups... It is problem:</p>
    <%Exception e = (Exception) request.getAttribute("exception");%>
    <p><%=e.getMessage()%>
    </p>
    <form action="readAll" method="get">
        <button class="btn btn-primary" type="submit">Return to list with all students</button>
    </form>
</div>
</body>
</html>
