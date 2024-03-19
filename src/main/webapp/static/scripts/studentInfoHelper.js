function completeInfo(elem) {
    let student_column_title = document.getElementById("student_table_head").children[0].children;
    document.getElementById("student_id").innerHTML = elem.getAttribute("key");
    document.getElementsByClassName("student_info")[0].style.display = "flex";
    for (let i = 0; i < student_column_title.length; i++) {
        let child = elem.children[i];
        let title = student_column_title[i];
        let student_field = document.getElementsByClassName("student_info_block_" +
            title.getAttribute("key"))[0];
        if (student_field != null) {
            student_field.innerHTML =
                "<p>" + title.textContent + "</p>"
                + "<p>:</p>"
                + "<p>" + child.textContent + "</p>";
        }
    }
}

