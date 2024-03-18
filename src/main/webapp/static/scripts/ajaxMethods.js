function ajaxPost(url, data, callback) {
    let xmlDoc = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");

    xmlDoc.open('POST', url, true);
    xmlDoc.setRequestHeader("Content-type", "application/x-www-form-urlencoded");

    xmlDoc.onreadystatechange = function () {
        if (xmlDoc.readyState === 4 && xmlDoc.status === 200) {
            callback(xmlDoc);
        }
    }

    xmlDoc.send(data);
}

function ajaxGet(url, callback) {
    let xmlDoc = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");

    xmlDoc.open('GET', url, true);

    xmlDoc.onreadystatechange = function () {
        if (xmlDoc.readyState === 4 && xmlDoc.status === 200) {
            callback(xmlDoc);
        }
    }

    xmlDoc.send();
}