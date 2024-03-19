<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF8" %>


<html>
<head>
    <link href="${pageContext.request.contextPath}/static/styles/main.css" rel="stylesheet">
    <title>TASK11</title>
</head>
<body>
<div id="response">
    <div id="response_block">
        <div class="response_block_header">
            <div class="response_block_header_close" onclick="document.getElementById('response').style.display='none'">
            </div>
        </div>
        <div class="response_block_text">
            <p>200</p>
            <p>Test text. All good.</p>
        </div>
    </div>
</div>
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
    <script src="${pageContext.request.contextPath}/static/scripts/ajaxMethods.js"></script>
    <script src="${pageContext.request.contextPath}/static/scripts/buttonsMethods.js"></script>
</div>
</body>
</html>


