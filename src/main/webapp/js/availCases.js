



function AvailCases() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("content").innerHTML = this.responseText;
            //document.getElementById("buybutton").onclick =funk;
        }
    };
    xhttp.open("GET", "FrontController?command=getJSP&page=availCases", true);
    xhttp.send();
}


function AvailCasesPop() {
//all JS functionality goes here.

}

function dispMessage(e) {

}

function getCase(e) {

    var caseID = e.id;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("selectedAC").innerHTML = xhttp.responseText;
        }
    };
    xhttp.open("GET", "FrontController?command=getJSP&page=viewCase&caseID=" + caseID, true);
    xhttp.send();
}



function getMsg(e) {
    var msgID = e.id;
    var msg = true;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("selectedAC").innerHTML = xhttp.responseText;
        }
    };
    xhttp.open("GET", "FrontController?command=getJSP&page=viewMessage&msgID=" + msgID, true);
    xhttp.send();
}
