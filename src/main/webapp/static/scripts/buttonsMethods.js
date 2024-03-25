//CONSTANTS
const STUDENTS_PER_PAGE = 15;
const START_PAGE = 1;

//END

document.addEventListener("DOMContentLoaded", function() {

    const paginationLinks = document.querySelectorAll(".table_block_pagination a");

    fetchTable(START_PAGE);
    refreshPaginationNavState(START_PAGE);

    paginationLinks.forEach(link => {
        link.addEventListener("click", function(event) {
            event.preventDefault();
            const currentPage = parseInt(document.querySelector(".table_block_pagination a.active").textContent);
            if (currentPage === parseInt(link.textContent)){
                return;
            }
            const lastPage = parseInt(paginationLinks[paginationLinks.length - 3].textContent);
            if (link.classList.contains("first-page")) {
                goToPage(1);
            } else if (link.classList.contains("last-page")) {
                goToPage(lastPage);
            } else if (link.classList.contains("prev-page")) {
                if (currentPage > 1) {
                    goToPage(currentPage - 1)
                }
            } else if (link.classList.contains("next-page")) {
                if (currentPage < lastPage){
                    goToPage(currentPage + 1)
                }
            } else {
                goToPage(parseInt(link.textContent));
            }
        });
    });
    function goToPage(pageNumber) {

        refreshPaginationNavState(pageNumber);
        console.log("Navigating to page:", pageNumber);
        fetchTable(pageNumber);
        // Update active link
        paginationLinks.forEach(link => {
            link.classList.remove("active");
            if (parseInt(link.textContent) === pageNumber) {
                link.classList.add("active");
            }
        });
    }
});


function deleteStudent() {
    let id = document.getElementById("student_id").textContent;
    ajaxPost("api/student/delete", "id=" + id, afterDeleteStudent, "application/x-www-form-urlencoded")
}

function afterDeleteStudent(data) {
    let status = data["httpStatus"];
    showResponse(data)
    if (status !== 500) {
        let id = document.getElementById("student_id").textContent;
        document.getElementsByClassName("student_info")[0].style.display = "none";
        let elem = document.getElementsByClassName("student_info_block")[0];
        for (let i = 0; i < elem.children.length; i++) {
            elem.children[i].innerHTML = "";
        }
        let tableBody = document.getElementById("student_table_body");
        for (let i = 0; i < tableBody.children.length; i++) {
            if (tableBody.children[i].getAttribute("key") === id) {
                tableBody.children[i].innerHTML = "";
                break;
            }
        }
    }

}


function createNewStudent() {
    document.getElementById("student_create").style.display = "flex";
    document.getElementById("student_create_block_body_form").addEventListener("submit", submitCreateStudentForm)
    document.getElementById("student_create_block_body_form").firstElementChild.setAttribute("value", "0");
}

function submitCreateStudentForm(event) {
    event.preventDefault()

    let formData = new FormData(event.target)
    let obj = {};
    formData.forEach((value, key) => obj[key] = value);
    console.log(obj);
    ajaxPost("api/student/create", JSON.stringify(obj), afterCreateStudent, "application/json")
}

function afterCreateStudent(data) {
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

function updateStudent() {
    let stInfoBl = document.getElementsByClassName("student_info_block")[0];
    for (let i = 1; i < stInfoBl.children.length; i++) {
        let str = stInfoBl.children[i].getAttribute("class");
        str = str.replace(stInfoBl.getAttribute("class") + "_", "")
        let formFieldsCol =
            document.getElementsByClassName("student_create_block_body_form_field");
        let infoFieldValue = stInfoBl.children[i].children[2].textContent;
        for (let j = 0; j < formFieldsCol.length; j++) {
            let inputField = formFieldsCol[j].children[1];
            if (inputField.getAttribute("name") === str) {
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

function submitUpdateStudentForm(event) {
    event.preventDefault()
    let formData = new FormData(event.target)
    let obj = {};
    formData.forEach((value, key) => obj[key] = value);
    ajaxPost("api/student/update", JSON.stringify(obj), afterUpdateStudent, "application/json")
}

function afterUpdateStudent(data) {
    let httpStatus = data["httpStatus"];
    showResponse(data)
    document.getElementById("student_create").style.display = "none";
    if (httpStatus !== 500) {
        let stInfoBl = document.getElementsByClassName("student_info_block")[0];
        for (let i = 1; i < stInfoBl.children.length; i++) {
            let str = stInfoBl.children[i].getAttribute("class");
            str = str.replace(stInfoBl.getAttribute("class") + "_", "")
            let formFieldsCol =
                document.getElementsByClassName("student_create_block_body_form_field");
            for (let j = 0; j < formFieldsCol.length; j++) {
                let inputField = formFieldsCol[j].children[1];
                if (inputField.getAttribute("name") === str) {
                    stInfoBl.children[i].children[2].innerHTML =
                        "<p>" + inputField.value + "</p>";
                    inputField.value = "";
                }
            }
        }
        document.getElementById("student_create_block_body_form").removeEventListener("submit", submitUpdateStudentForm)
    }
}

function closeCreateForm(elem) {
    document.getElementById('student_create').style.display = 'none';
    let fields = document.getElementsByClassName("student_create_block_body_form_field")
    for (let i = 0; i < fields.length; i++) {
        fields[i].children[1].value = "";
    }
    document.getElementById("student_create_block_body_form").firstElementChild.setAttribute("value", "0");
    document.getElementById("student_create_block_body_form").removeEventListener("submit", submitUpdateStudentForm)
    document.getElementById("student_create_block_body_form").removeEventListener("submit", submitCreateStudentForm)
}

function showResponse(data) {
    let status = data["httpStatus"];
    let msg = data["message"]
    document.getElementsByClassName("response_block_text")[0].innerHTML =
        "<p>" + status + "</p>" +
        "<p>" + msg + "</p>";
    document.getElementById("response").style.display = "flex";
}


function fetchTable(page){
    ajaxGet("api/students/students_page?page="+ page +"&students_per_page=" + STUDENTS_PER_PAGE, afterFetchTable);
}


function afterFetchTable(data){
    let paginationLinks = document.querySelectorAll(".table_block_pagination a");
    paginationLinks[paginationLinks.length-3].textContent = Math.ceil(data["countOfStudents"]/STUDENTS_PER_PAGE)

    let tableBody = document.getElementById('student_table_body');
    tableBody.innerHTML = '';
    let students = data.students;
    students.forEach(function(student, index) {
        let row = '<tr key="' + student.id + '" onclick="completeInfo(this)">';
        row += '<td>' + (index + 1) + '</td>';
        row += '<td>' + student.name + '</td>';
        row += '<td>' + student.surname + '</td>';
        row += '<td>' + student.age + '</td>';
        row += '<td>' + student.mark + '</td>';
        row += '<td>' + student.country + '</td>';
        row += '<td>' + student.city + '</td>';
        row += '<td>' + student.street + '</td>';
        row += '<td>' + student.building + '</td>';
        row += '</tr>';
        tableBody.innerHTML += row;
    });
    let countOfStudent = data.countOfStudents;
    console.log(countOfStudent);
}




function refreshPaginationNavState(pageNumber){
    let a = document.querySelectorAll(".table_block_pagination a");
    if (pageNumber === 1) {
        document.getElementsByClassName("first-page")[0].style.visibility = "hidden";
        document.getElementsByClassName("prev-page")[0].style.visibility = "hidden";
        let movableNav = document.getElementsByClassName("movable-page-nav");
        movableNav[2].textContent = pageNumber + 3;
        movableNav[1].textContent = pageNumber + 2;
        movableNav[0].textContent = pageNumber + 1;
    } else {
        document.getElementsByClassName("first-page")[0].style.visibility = "visible";
        document.getElementsByClassName("prev-page")[0].style.visibility = "visible";
    }
    if (pageNumber === parseInt(a[a.length-3].textContent)) {
        document.getElementsByClassName("next-page")[0].style.visibility = "hidden";
        document.getElementsByClassName("last-page")[0].style.visibility = "hidden";
        let movableNav = document.getElementsByClassName("movable-page-nav");
        movableNav[2].textContent = pageNumber - 1;
        movableNav[1].textContent = pageNumber - 2;
        movableNav[0].textContent = pageNumber - 3;
    } else {
        document.getElementsByClassName("next-page")[0].style.visibility = "visible";
        document.getElementsByClassName("last-page")[0].style.visibility = "visible";
    }
    if (pageNumber > 3){
        document.querySelectorAll(".table_block_pagination p")[0].style.display = "flex";
    } else {
        document.querySelectorAll(".table_block_pagination p")[0].style.display = "none";
    }
    if (pageNumber < parseInt(a[a.length-3].textContent)-2){
        document.querySelectorAll(".table_block_pagination p")[1].style.display = "flex";
    } else {
        document.querySelectorAll(".table_block_pagination p")[1].style.display = "none";
    }
    if (pageNumber > 2 && pageNumber < parseInt(a[a.length-3].textContent)-1){
        document.getElementsByClassName("movable-page-nav")[2].textContent = pageNumber+1;
        document.getElementsByClassName("movable-page-nav")[1].textContent = pageNumber;
        document.getElementsByClassName("movable-page-nav")[0].textContent = pageNumber-1;
    }

}