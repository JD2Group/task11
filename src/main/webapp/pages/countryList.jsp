<%@ page import="it.academy.dto.CountryDTO" %>
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
    <title>List of all countries</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
          crossorigin="anonymous">
</head>

<body>
<%
    String action = "getAllCountries";
    Object atribute = request.getAttribute("listCountryDto");
    Object countAtr = session.getAttribute("countOnPage");
    Object pageAtr = session.getAttribute("currentPage");
    List<CountryDTO> listDto = new ArrayList<>();
    Integer countOnPage = null;
    Integer currentPage = null;
    try {
        countOnPage = (Integer) ((countAtr == null ?
                DEFAULT_COUNT_ON_PAGE : countAtr));
        currentPage = (Integer) ((pageAtr == null ?
                DEFAULT_FIRST_PAGE_NUMBER : pageAtr));
        listDto = ((List<CountryDTO>) atribute);
    } catch (Exception e) {
        request.setAttribute("exception", e);
        response.sendRedirect("/pages/exception.jsp");
    }
%>
<div class="container text-center">
    <%@ include file="../start_end/showOnPage.jsp" %>
</div>
<br>

<div class="container text-center">
    <%@ include file="../start_end/pagination.jsp" %>
</div>
<br>

<div class="container text-center">
    <table>
        <tr>
            <th>No</th>
            <th>Country</th>
            <th></th>
            <th></th>
            <th></th>
        </tr>

        <%for (int i = 0; i < listDto.size(); i++) {%>
        <%CountryDTO countryDTO = listDto.get(i);%>
        <%String s = String.valueOf(countryDTO.getId());%>

        <tr>
            <td><%=(i + 1)%>
            </td>
            <td><%=countryDTO.getCountryName()%>
            </td>
            <td>
                <form action="getStud" method="get">
                    <input type="hidden" value="<%=s%>" name="countryId">
                    <button class="btn btn-light" type="submit">Show students from this country</button>
                </form>
            </td>
            <td>
                <form action="updCountry" method="get">
                    <input type="hidden" value="<%=s%>" name="id">
                    <button class="btn btn-light" type="submit">Edit country name</button>
                </form>
            </td>
            <td>
                <form action="delCountry" method="get">
                    <input type="hidden" value="<%=s%>" name="id">
                    <button class="btn btn-light" type="submit">Delete country</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
</div>
<br>

<div class="container text-center">
    <%@ include file="../start_end/pagination.jsp" %>
</div>
<br>

<div class="container text-center">
    <table>
        <tr>
            <td>
                <%@ include file="../start_end/students.jsp" %>
            </td>
            <td>
                <%@ include file="../start_end/exit.jsp" %>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
