document.getElementById("auth_form_block_body_form").addEventListener("submit", submitLoginMethod);

function submitLoginMethod(event) {
    event.preventDefault();
    let formData = new FormData(event.target)
    let obj = {};
    formData.forEach((value, key) => obj[key] = value);
    console.log(obj);
    ajaxPost("api/login", JSON.stringify(obj), afterLoginMethod, "application/json")

}

function afterLoginMethod(data) {
    setCookie("refresh_token", data["refresh_token"])
    localStorage.setItem('access_token', data["access_token"]);
    setTimeout(this, 1000)
    window.location.reload();
}

function submitRegMethod(event) {
    event.preventDefault();
    let formData = new FormData(event.target)
    let obj = {};
    formData.forEach((value, key) => obj[key] = value);
    console.log(obj);
    ajaxPost("api/registration", JSON.stringify(obj), afterRegMethod, "application/json")
}

function afterRegMethod(data) {
    setTimeout(this, 1000)
    window.location.reload();
    showRegInfo(data)
}

function showRegInfo(data) {
    let status = data["email"];
    let msg = data["message"]
    document.getElementsByClassName("response_block_text")[0].innerHTML =
        "<p>Hello, " + status + "!</p>" +
        "<p>" + msg + "</p>";
    document.getElementById("response").style.display = "flex";
}

function logoutBTN() {
    localStorage.removeItem("access_token");
    document.cookie = "refresh_token" + "=; path=/"
    window.location.reload();
}

function setCookie(name, value, days) {
    let expires;
    if (days) {
        let date = new Date();
        date.setTime(date.getTime() + (days * 24 * 60 * 60 * 1000));
        expires = "; expires=" + date.toUTCString();
    }
    document.cookie = name + "=" + value + (expires || "") + "; path=/";
    console.log(name + "=" + value + (expires || "") + "; path=/");
}

let authBTN = document.querySelectorAll(".auth_type");
authBTN[0].addEventListener("click", printLoginForm);
authBTN[1].addEventListener("click", printRegForm)


function printLoginForm(elem) {

    authBTN.forEach(e => e.classList.remove("active_auth_type"))
    elem.target.classList.add("active_auth_type")

    let formBlock = document.querySelector(".auth_form_block_body");
    formBlock.innerHTML = "";
    let formBlockBody = document.createElement("form")
    formBlockBody.setAttribute("id", "auth_form_block_body_form");

    // Create email input field
    let emailDiv = document.createElement("div");
    emailDiv.classList.add("auth_form_block_body_form_field");
    let emailLabel = document.createElement("label");
    emailLabel.textContent = "Email: ";
    let emailInput = document.createElement("input");
    emailInput.setAttribute("name", "email");
    emailInput.setAttribute("value", "");
    emailInput.setAttribute("type", "text");
    emailDiv.appendChild(emailLabel);
    emailDiv.appendChild(emailInput);

    // Create password input field
    let passwordDiv = document.createElement("div");
    passwordDiv.classList.add("auth_form_block_body_form_field");
    let passwordLabel = document.createElement("label");
    passwordLabel.textContent = "Password: ";
    let passwordInput = document.createElement("input");
    passwordInput.setAttribute("name", "password");
    passwordInput.setAttribute("value", "");
    passwordInput.setAttribute("type", "password");
    passwordDiv.appendChild(passwordLabel);
    passwordDiv.appendChild(passwordInput);

    // Create submit button
    let submitDiv = document.createElement("div");
    submitDiv.classList.add("auth_form_block_body_form_submit");
    let submitInput = document.createElement("input");
    submitInput.setAttribute("id", "auth_submit");
    submitInput.setAttribute("type", "submit");
    submitInput.setAttribute("value", "Confirm");
    submitDiv.appendChild(submitInput);

    // Append elements to the form block body
    formBlockBody.appendChild(emailDiv);
    formBlockBody.appendChild(passwordDiv);
    formBlockBody.appendChild(submitDiv);
    formBlock.appendChild(formBlockBody);

    document.getElementById("auth_form_block_body_form").addEventListener("submit", submitLoginMethod);

}

function printRegForm(elem) {

    authBTN.forEach(e => e.classList.remove("active_auth_type"));
    console.log(elem.target);
    elem.target.classList.add("active_auth_type");

    let formBlock = document.querySelector(".auth_form_block_body");
    formBlock.innerHTML = "";
    let formBlockBody = document.createElement("form")
    formBlockBody.setAttribute("id", "auth_form_block_body_form");

    // Create email input field
    let emailDiv = document.createElement("div");
    emailDiv.classList.add("auth_form_block_body_form_field");
    let emailLabel = document.createElement("label");
    emailLabel.textContent = "Email: ";
    let emailInput = document.createElement("input");
    emailInput.setAttribute("name", "email");
    emailInput.setAttribute("value", "");
    emailInput.setAttribute("type", "text");
    emailDiv.appendChild(emailLabel);
    emailDiv.appendChild(emailInput);

    // Create password input field
    let passwordDiv = document.createElement("div");
    passwordDiv.classList.add("auth_form_block_body_form_field");
    let passwordLabel = document.createElement("label");
    passwordLabel.textContent = "Password: ";
    let passwordInput = document.createElement("input");
    passwordInput.setAttribute("name", "password");
    passwordInput.setAttribute("value", "");
    passwordInput.setAttribute("type", "password");
    passwordDiv.appendChild(passwordLabel);
    passwordDiv.appendChild(passwordInput);

    // Create confirm password input field
    let passwordConfDiv = document.createElement("div");
    passwordConfDiv.classList.add("auth_form_block_body_form_field");
    let passwordConfLabel = document.createElement("label");
    passwordConfLabel.textContent = "Confirm password: ";
    let passwordConfInput = document.createElement("input");
    passwordConfInput.setAttribute("name", "confirm_password");
    passwordConfInput.setAttribute("value", "");
    passwordConfInput.setAttribute("type", "password");
    passwordConfDiv.appendChild(passwordConfLabel);
    passwordConfDiv.appendChild(passwordConfInput);

    // Create submit button
    let submitDiv = document.createElement("div");
    submitDiv.classList.add("auth_form_block_body_form_submit");
    let submitInput = document.createElement("input");
    submitInput.setAttribute("id", "auth_submit");
    submitInput.setAttribute("type", "submit");
    submitInput.setAttribute("value", "Confirm");
    submitDiv.appendChild(submitInput);

    // Append elements to the form block body
    formBlockBody.appendChild(emailDiv);
    formBlockBody.appendChild(passwordDiv);
    formBlockBody.appendChild(passwordConfDiv)
    formBlockBody.appendChild(submitDiv);
    formBlock.appendChild(formBlockBody);

    document.getElementById("auth_form_block_body_form").addEventListener("submit", submitRegMethod);
}