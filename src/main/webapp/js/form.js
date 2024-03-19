const name = document.getElementById("name");
const surname = document.getElementById("surname");
const city = document.getElementById("city");
const street = document.getElementById("street");

name.addEventListener("input", function (event) {
    if (name.validity.patternMismatch) {
        name.setCustomValidity("Ты правда знаешь кого-то с таким именем? Давай только буквы =)");
    } else {
        name.setCustomValidity("");
    }
});

surname.addEventListener("input", function (event) {
    if (surname.validity.patternMismatch) {
        surname.setCustomValidity("Ты правда знаешь кого-то с такой фамилией? Давай только буквы, ну максисмум еще дефис добавить можешь =)");
    } else {
        surname.setCustomValidity("");
    }
});

city.addEventListener("input", function (event) {
    if (city.validity.patternMismatch) {
        city.setCustomValidity("Хмм... Подозрительный город... Так не пойдет =)");
    } else {
        city.setCustomValidity("");
    }
});

street.addEventListener("input", function (event) {
    if (street.validity.patternMismatch) {
        street.setCustomValidity("Да ладно тебе! Введи хоть одну букву =)");
    } else {
        street.setCustomValidity("");
    }
});