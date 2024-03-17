<%-- example.jsp --%>
<%@ page contentType="text/html; charset=UTF8" %>


<html>
<head>
    <link href="${pageContext.request.contextPath}/static/styles/main.css" rel="stylesheet">
</head>
<body>
<%@include file="header.jsp" %>
<div class="main_page">
    <div class="container">
        <div class="main">
            <table class="database_table">
                <tr>
                    <th>Key</th>
                    <td>Value</td>
                </tr>
            </table>
        </div>
    </div>
</div>
</body>
</html>


