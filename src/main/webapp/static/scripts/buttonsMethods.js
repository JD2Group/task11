
function deleteStudent(){
    let id = document.getElementById("student_id").textContent;
    ajaxPost("api/student/delete", "id="+id, afterDeleteStudent, "application/x-www-form-urlencoded")
}

function afterDeleteStudent(data){
    let status = data["httpStatus"];
    let msg = data["message"]

    document.getElementsByClassName("response_block_text")[0].innerHTML =
        "<p>" + status + "</p>" +
        "<p>" + msg + "</p>"

    if (status !== 500){
        let id = document.getElementById("student_id").textContent;
        document.getElementsByClassName("student_info")[0].style.display = "none";
        let elem = document.getElementsByClassName("student_info_block")[0];
        for (let i = 0; i < elem.children.length; i++){
            elem.children[i].innerHTML = "";
        }
        let tableBody = document.getElementById("student_table_body");
        for (let i = 0; i < tableBody.children.length; i++){
            if (tableBody.children[i].getAttribute("key") === id){
                tableBody.children[i].innerHTML = "";
                break;
            }
        }
    }

    document.getElementById("response").style.display = "flex";
}



function createNewStudent() {
    document.getElementById("student_create").style.display = "flex";
    document.getElementById("student_create_block_body_form").addEventListener("submit", submitCreateStudentForm)
}

function submitCreateStudentForm(event){
    document.getElementById("student_create_block_body_form").removeEventListener("submit", submitCreateStudentForm)
    let formData = new FormData(event.target)

    let obj = {};
    formData.forEach((value, key) => obj[key] = value);
    ajaxPost("api/student/save", JSON.stringify(obj), afterCreateStudent, "application/json")
}

function afterCreateStudent(data){
    let status = data["httpStatus"];
    let msg = data["message"]
    window.alert(status + " " + msg)
    document.getElementsByClassName("response_block_text")[0].innerHTML =
        "<p>" + status + "</p>" +
        "<p>" + msg + "</p>"
    document.getElementById("response").style.display = "flex";
}