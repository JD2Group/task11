<%@ page import="it.academy.dto.CountryDTO" %>
<%--
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
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>
<body>
<div class="container text-center">
    <%CountryDTO countryDTO = (CountryDTO) request.getAttribute("countryDTO");%>

    <form action="updCountry" method="post">
        <label>
            <input type="hidden" value="<%=countryDTO.getId()%>" name="id">
        </label><br/>

        Input country name:<label>
        <input type="text" value="<%=countryDTO.getCountryName()%>" name="countryName">
    </label><br/>
        <br>
        <br>

        <button class="btn btn-primary" type="submit">Update <%=countryDTO.getCountryName()%>
        </button>
    </form>

    <form action="getAllCountries" method="get">
        <button class="btn btn-secondary" type="submit">Return to list with all countries</button>
    </form>
</div>
</body>
</html>

