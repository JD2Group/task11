function ajaxPost(url, data, method, contentType) {
    let xmlDoc = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
    let command = url.substring(url.lastIndexOf("/") + 1);
    xmlDoc.open('POST', url, true);
    xmlDoc.setRequestHeader("Content-type", contentType);
    xmlDoc.setRequestHeader("Command", command);
    xmlDoc.onreadystatechange = function () {
        if (xmlDoc.readyState === 4 && (xmlDoc.status === 200 || xmlDoc.status === 201)) {
            let responseData = JSON.parse(xmlDoc.responseText)
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
    xmlDoc.setRequestHeader("Command", command)
    xmlDoc.onreadystatechange = function () {
        if (xmlDoc.readyState === 4 && xmlDoc.status === 200) {
            let responseData = JSON.parse(xmlDoc.responseText);
            method(responseData);
        }
    }
    console.log(url);
    xmlDoc.send();
}
