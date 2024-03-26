<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF8" %>
<%--<c:if test="${sessionScope.get('isAuthenticated') == null}">
    <c:set var="isAuthenticated" value="false" scope="session"/>
</c:if>--%>
<html>
<head>
    <link href="${pageContext.request.contextPath}/static/styles/main.css" rel="stylesheet">
    <title>TASK11</title>
</head>
<body>
<%@include file="WEB-INF/jsp/response.jsp" %>
<%@include file="WEB-INF/jsp/header.jsp" %>
<div class="main_page">
<div class="container" <%--<c:if test="${sessionScope.get('isAuthenticated') != true}">--%>>
    <div class="main">
        <%@include file="WEB-INF/jsp/student_create_block.jsp" %>
        <div class="student_block">
            <div class="table_block">
                <jsp:include page="WEB-INF/jsp/database_table.jsp"/>
                <div class="table_block_pagination">
                    <a href="#" class="first-page">&laquo;&laquo;</a>
                    <a href="#" class="prev-page">&laquo;</a>
                    <a href="#" class="active">1</a>
                    <p>...</p>
                    <a href="#" class="movable-page-nav">2</a>
                    <a href="#" class="movable-page-nav">3</a>
                    <a href="#" class="movable-page-nav">4</a>
                    <p>...</p>
                    <a href="#">5</a>
                    <a href="#" class="next-page">&raquo;</a>
                    <a href="#" class="last-page">&raquo;&raquo;</a>
                </div>
            </div>
            <jsp:include page="WEB-INF/jsp/student.jsp"/>
        </div>
    </div>
    </div>
    <script src="${pageContext.request.contextPath}/static/scripts/studentInfoHelper.js"></script>
    <script src="${pageContext.request.contextPath}/static/scripts/ajaxMethods.js"></script>
    <script src="${pageContext.request.contextPath}/static/scripts/buttonsMethods.js"></script>

    </div>
    </body>
    </html>


</jsp:root>