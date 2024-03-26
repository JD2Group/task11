document.getElementById("auth_form_block_body_form").addEventListener("submit", submitLoginMethod);
function submitLoginMethod(event){
    event.preventDefault();
    let formData = new FormData(event.target)
    let obj = {};
    formData.forEach((value, key) => obj[key] = value);
    console.log(obj);
    ajaxPost("api/login", JSON.stringify(obj), afterLoginMethod, "application/json")

}

function afterLoginMethod(data){
    setCookie("refresh_token", data["refresh_token"])
    localStorage.setItem('access_token', data["access_token"]);
    setTimeout(this, 100)
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