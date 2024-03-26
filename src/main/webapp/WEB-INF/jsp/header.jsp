<%@ page contentType="text/html; charset=UTF8" %>
<header class="header">
    <div class="container">
        <div class="header_body">
            <div class="logo">
                <p>JD2 Group Task</p>
            </div>
            <div class="header_case">
                <c:if test="${sessionScope.get('isAuthenticated') == 'true'
                && sessionScope.get('roles').contains('DEFAULT_USER')}">
                    <button onclick="createNewStudent()">Добавить студента</button>
                </c:if>
            </div>
        </div>
    </div>
</header>