
function deleteStudent(){
    let id = document.getElementById("student_id").textContent;
    ajaxPost("api/student/delete", "id="+id, afterDeleteStudent, "application/x-www-form-urlencoded")
}

function afterDeleteStudent(data){
    let status = data["httpStatus"];
    showResponse(data)
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

}



function createNewStudent() {
    document.getElementById("student_create").style.display = "flex";
    document.getElementById("student_create_block_body_form").addEventListener("submit", submitCreateStudentForm)
    document.getElementById("student_create_block_body_form").firstElementChild.setAttribute("value","0");
}

function submitCreateStudentForm(event){
    event.preventDefault()

    let formData = new FormData(event.target)
    let obj = {};
    formData.forEach((value, key) => obj[key] = value);
    console.log(obj);
    ajaxPost("api/student/save", JSON.stringify(obj), afterCreateStudent, "application/json")
}

function afterCreateStudent(data){
    showResponse(data)
    if (data["httpStatus"] !== 500) {
        document.getElementById("student_create").style.display = "none";
        let fields = document.getElementsByClassName("student_create_block_body_form_field")
        for (let i = 0; i < fields.length; i++) {
            fields[i].children[1].value = "";
        }
        document.getElementById("student_create_block_body_form").removeEventListener("submit", submitCreateStudentForm)
    }

}

function updateStudent(){
    let stInfoBl = document.getElementsByClassName("student_info_block")[0];
    for (let i = 1; i < stInfoBl.children.length; i ++){
        let str = stInfoBl.children[i].getAttribute("class");
        str = str.replace(stInfoBl.getAttribute("class")+"_", "")
        let formFieldsCol =
            document.getElementsByClassName("student_create_block_body_form_field");
        let infoFieldValue = stInfoBl.children[i].children[2].textContent;
        for (let j = 0; j < formFieldsCol.length; j++){
            let inputField = formFieldsCol[j].children[1];
            if (inputField.getAttribute("name") === str){
                inputField.setAttribute("value", infoFieldValue);
                inputField.value = infoFieldValue;
            }
        }
    }
    document.getElementById("student_create_block_body_form").firstElementChild.setAttribute("value",
        document.getElementById("student_id").textContent);

    document.getElementById("student_create").style.display = "flex";
    document.getElementById("student_create_block_body_form").addEventListener("submit", submitUpdateStudentForm)
}

function submitUpdateStudentForm(event){
    event.preventDefault()
    let formData = new FormData(event.target)
    let obj = {};
    formData.forEach((value, key) => obj[key] = value);
    ajaxPost("api/student/update", JSON.stringify(obj), afterUpdateStudent, "application/json")
}

function afterUpdateStudent(data){
    let httpStatus = data["httpStatus"];
    showResponse(data)
    document.getElementById("student_create").style.display = "none";
    if (httpStatus !== 500) {
        let stInfoBl = document.getElementsByClassName("student_info_block")[0];
        for (let i = 1; i < stInfoBl.children.length; i ++){
            let str = stInfoBl.children[i].getAttribute("class");
            str = str.replace(stInfoBl.getAttribute("class")+"_", "")
            let formFieldsCol =
                document.getElementsByClassName("student_create_block_body_form_field");
            for (let j = 0; j < formFieldsCol.length; j++){
                let inputField = formFieldsCol[j].children[1];
                if (inputField.getAttribute("name") === str){
                    stInfoBl.children[i].children[2].innerHTML =
                        "<p>" + inputField.value + "</p>";
                    inputField.value = "";
                }
            }
        }
        document.getElementById("student_create_block_body_form").removeEventListener("submit", submitUpdateStudentForm)

    }


}

function closeCreateForm(elem){
    document.getElementById('student_create').style.display='none';
    let fields = document.getElementsByClassName("student_create_block_body_form_field")
    for (let i = 0; i < fields.length; i++) {
        fields[i].children[1].value = "";
    }
    document.getElementById("student_create_block_body_form").firstElementChild.setAttribute("value","0");
    document.getElementById("student_create_block_body_form").removeEventListener("submit", submitUpdateStudentForm)
    document.getElementById("student_create_block_body_form").removeEventListener("submit", submitCreateStudentForm)
}

function showResponse(data){
    let status = data["httpStatus"];
    let msg = data["message"]
    document.getElementsByClassName("response_block_text")[0].innerHTML =
        "<p>" + status + "</p>" +
        "<p>" + msg + "</p>";
    document.getElementById("response").style.display = "flex";
}