<%@ page contentType="text/html; charset=UTF8" %>
<div class="student_info">
    <div class="student_info_top_side_block">
        <div class="student_info_img">
            <img src="${pageContext.request.contextPath}/static/images/photo_default.jpg" alt="IMAGE">
        </div>
        <div class="student_info_block">
            <div id="student_id"></div>
            <div class="student_info_block_name">

            </div>
            <div class="student_info_block_surname">

            </div>
            <div class="student_info_block_age">

            </div>
            <div class="student_info_block_mark">

            </div>
            <div class="student_info_block_building">

            </div>
            <div class="student_info_block_city">

            </div>
            <div class="student_info_block_street">

            </div>
        </div>
    </div>
    <div class="student_info_down_side_block">
        <button class="student_info_buttons" onclick="updateStudent()">Обновить</button>
        <button class="student_info_buttons" onclick="deleteStudent()">Удалить</button>
    </div>
</div>