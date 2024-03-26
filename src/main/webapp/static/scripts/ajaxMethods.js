function ajaxPost(url, data, method, contentType) {
    let xmlDoc = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
    let command = url.substring(url.lastIndexOf("/") + 1);
    xmlDoc.open('POST', url, true);
    extractTokenIfPresent(xmlDoc);
    console.log(url)
    xmlDoc.setRequestHeader("Content-type", contentType);
    xmlDoc.setRequestHeader("Command", command);
    xmlDoc.onreadystatechange = function () {
        if (xmlDoc.readyState === 4 && (xmlDoc.status === 200 || xmlDoc.status === 201)) {
            let responseData = JSON.parse(xmlDoc.responseText)
            updateAccessTokenIfPresent(xmlDoc)
            method(responseData)
        }
    }
    console.log(data)
    xmlDoc.send(data);
}

function ajaxGet(url, method) {
    let xmlDoc = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
    let command = url.substring(url.lastIndexOf("/") + 1, url.lastIndexOf("?"));
    xmlDoc.open('GET', url, true);
    extractTokenIfPresent(xmlDoc);
    xmlDoc.setRequestHeader("Command", command)
    xmlDoc.onreadystatechange = function () {
        if (xmlDoc.readyState === 4 && xmlDoc.status === 200) {
            let responseData = JSON.parse(xmlDoc.responseText);
            updateAccessTokenIfPresent(xmlDoc)
            method(responseData);
        }
    }
    console.log(url);
    xmlDoc.send();
}

function extractTokenIfPresent(xmlDoc) {
    let token = localStorage.getItem("access_token")
    if (token == null) {
        return
    }
    xmlDoc.setRequestHeader('Authorization', 'Bearer ' + token);
}

function updateAccessTokenIfPresent(xmlDoc) {
    let access = xmlDoc.getResponseHeader("updated-access");
    if (access == null) {
        return
    }
    localStorage.setItem("access_token", access);
}