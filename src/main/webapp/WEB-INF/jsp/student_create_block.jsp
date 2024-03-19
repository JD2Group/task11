<%@ page contentType="text/html; charset=UTF8" %>
<div id="student_create">
    <div id="student_create_block">
        <div class="student_create_block_header">
            <div class="student_create_block_header_close" onclick="closeCreateForm(this)">
            </div>
        </div>
        <div class="student_create_block_body">
            <form id="student_create_block_body_form">
                <input type="hidden" value="0" name="id">
                <div class="student_create_block_body_form_field">
                    <label>Имя: </label>
                    <input name="name" value="" type="text">
                </div>
                <div class="student_create_block_body_form_field">
                    <label>Фамилия: </label>
                    <input name="surname" value="" type="text">
                </div>
                <div class="student_create_block_body_form_field">
                    <label>Возраст: </label>
                    <input name="age" value="" type="number">
                </div>
                <div class="student_create_block_body_form_field">
                    <label>Оценка: </label>
                    <input name="mark" value="" type="number">
                </div>
                <div class="student_create_block_body_form_field">
                    <label>Город: </label>
                    <input name="city" value="" type="text">
                </div>
                <div class="student_create_block_body_form_field">
                    <label>Улица: </label>
                    <input name="street" value="" type="text">
                </div>
                <div class="student_create_block_body_form_field">
                    <label>Номер дома: </label>
                    <input name="building" value="" type="number">
                </div>
                <div class="student_create_block_body_form_submit">
                    <input id="form_submit" type="submit" value="Confirm" onclick="">
                </div>
            </form>
        </div>
    </div>
</div>