var req;
var isIE;
var completeField;
var completeTable;
var autoRow;

function init() {
completeField = document.getElementById("searchId");
completeTable = document.getElementById("complete-table");
autoRow = document.getElementById("auto-row");

}


function doCompletion() {
var url = "autocomplete?action=complete&searchId=" + escape(completeField.value);
/*document.write("I AM IN doCompletion");
document.write(url);*/
req = initRequest();
/*document.write(" Return From init");
document.write(" Calling OPEN");*/
req.open("GET", url, true);
req.send();
/*document.write(" Return From OPEN");*/
req.onreadystatechange = callback;
}


function initRequest() {

//document.write("I AM IN initRequest");

if (window.XMLHttpRequest) {
    if (navigator.userAgent.indexOf('MSIE') != -1) {
    isIE = true;
}
    return new XMLHttpRequest();
} 
else if (window.ActiveXObject) {
        isIE = true;
        return new ActiveXObject("Microsoft.XMLHTTP");
    }
}



function appendProduct(productName,productId) {
//document.write("I AM IN appendProduct");
var row;
var cell;
var linkElement;
if (isIE) {
        completeTable.style.display = 'block';
        row = completeTable.insertRow(completeTable.rows.length);
        cell = row.insertCell(0);
} 

else {
        completeTable.style.display = 'table';
        row = document.createElement("tr");
        cell = document.createElement("td");
        row.appendChild(cell);
        completeTable.appendChild(row);
}

cell.className = "popupCell";
linkElement = document.createElement("a");
linkElement.className = "popupItem";
linkElement.setAttribute("href", "autocomplete?action=lookup&searchId="+productId);
linkElement.appendChild(document.createTextNode(productName));
cell.appendChild(linkElement);
}


function parseMessages(responseXML) {
// no matches returned
//document.write(" From parseMessages");
//document.write(responseXML);

if (responseXML == null) {
        return false;
} 
else {
        var products = responseXML.getElementsByTagName("products")[0];
        //document.write("From parseMessages_1");
        //document.write(products);
    if (products.childNodes.length > 0) {
        completeTable.setAttribute("bordercolor", "blue");
        completeTable.setAttribute("border", "1");
for (loop = 0; loop < products.childNodes.length; loop++) {
        //document.write("From parseMessages_2");
        
        var product = products.childNodes[loop];
        //document.write(product);
        var productName = product.getElementsByTagName("productName")[0];
        //document.write("From parseMessages_2");
        //document.write(productName);
        var productId = product.getElementsByTagName("id")[0];
        //document.write("From parseMessages_3");
        //document.write(productId);
        appendProduct(productName.childNodes[0].nodeValue,
        productId.childNodes[0].nodeValue);
        }
    }
  }
}


function callback() {
clearTable();
if (req.readyState == 4) {
    if (req.status == 200) {
    parseMessages(req.responseXML);
     }
  }
}


function clearTable() {
        if (completeTable.getElementsByTagName("tr").length > 0) {
        completeTable.style.display = 'none';
for (loop = completeTable.childNodes.length -1; loop >= 0 ; loop--) {
completeTable.removeChild(completeTable.childNodes[loop]);
}
}
}
