<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF8" %>


<html>
<head>
    <link href="${pageContext.request.contextPath}/static/styles/main.css" rel="stylesheet">
</head>
<body>
<%@include file="WEB-INF/jsp/header.jsp" %>
<div class="main_page">
    <div class="container">
        <div class="main">
            <div class="student_block">
                <div class="table_block">
                    <jsp:include page="WEB-INF/jsp/database_table.jsp"/>
                </div>
                <jsp:include page="WEB-INF/jsp/student.jsp"/>
            </div>
        </div>
    </div>
    <script src="${pageContext.request.contextPath}/static/scripts/studentInfoHelper.js"></script>
</div>
</body>
</html>


