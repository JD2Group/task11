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
</head>
<body>

<form action="create" method="post">
    Input name:<label>
    <input name="name" type="text">
</label><br/>
    Input surname:<label>
    <input name="surname" type="text">
</label><br/>
    Input age:<label>
    <input name="age" type="text">
</label><br/>
    Input city:<label>
    <input name="city" type="text">
</label><br/>
    Input street:<label>
    <input name="street" type="text">
</label><br/>
    Input building:<label>
    <input name="building" type="text">
</label><br/>
    Input mark:<label>
    <input name="mark" type="text">
</label><br/>

    <button class="red" type="submit"><i class="icon ion-md-lock"></i>Create new student</button>
</form>

<form action="readAll" method="get">
    <button class="red" type="submit"><i class="icon ion-md-lock"></i>Return to list with all students</button>
</form>

</body>
</html>
