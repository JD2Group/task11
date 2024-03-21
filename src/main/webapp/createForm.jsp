<%--
  Created by IntelliJ IDEA.
  User: Timon
  Date: 15.03.2024
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Create new student</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>
<body>

<div class="container text-center">
    <form action="create" method="post">

        Input surname: <label>
        <input name="surname" type="text">
    </label><br/>
        <br>

        Input name: <label>
        <input name="name" type="text">
    </label><br/>
        <br>

        Input age: <label>
        <input name="age" type="text">
    </label><br/>
        <br>

        Input city: <label>
        <input name="city" type="text">
    </label><br/>
        <br>

        Input street: <label>
        <input name="street" type="text">
    </label><br/>
        <br>

        Input building:<label>
        <input name="building" type="text">
    </label><br/>
        <br>

        Input mark: <label>
        <input name="mark" type="text">
    </label><br/>
        <br>
        <br>

        <button class="btn btn-primary" type="submit">Create new student</button>
    </form>

    <form action="readAll" method="get">
        <button class="btn btn-secondary" type="submit">Return to list with all students</button>
    </form>
</div>
</body>
</html>
