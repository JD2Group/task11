function ajaxPost(url, data, method, contentType) {
    let xmlDoc = window.XMLHttpRequest ? new XMLHttpRequest() : new ActiveXObject("Microsoft.XMLHTTP");
    let command = url.substring(url.lastIndexOf("/")+1);
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
