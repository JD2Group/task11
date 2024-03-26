<%@ page contentType="text/html; charset=UTF8" %>

<div class="auth_form" onload="loginMethod()">
    <div class="auth_form_block">
        <div class="auth_form_block_title">
            <p class="active_auth_type auth_type">Login</p>
            <p>/</p>
            <p class="auth_type">Registration</p>
        </div>
        <div class="auth_form_block_body">
            <form id="auth_form_block_body_form">
                <div class="auth_form_block_body_form_field">
                    <label>Email: </label>
                    <input name="email" value="" type="text">
                </div>
                <div class="auth_form_block_body_form_field">
                    <label>Password: </label>
                    <input name="password" value="" type="password">
                </div>
                <div class="auth_form_block_body_form_submit">
                    <input id="auth_submit" type="submit" value="Confirm">
                </div>
            </form>
        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/static/scripts/authForm.js"></script>


