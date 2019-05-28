

function oldCases() {
    getJSPCases("oldCases");
    window.sessionStorage.setItem("currentwindow", "oldCases");
}

function ActiveCases() {
    getJSPCases("activeCases");
    window.sessionStorage.setItem("currentwindow", "ActiveCases");
}


function AvailCases() {
    getJSPCases("availCases");
    window.sessionStorage.setItem("currentwindow", "availCases");
}



function getJSPCases(page) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("content").innerHTML = this.responseText;
            if (page == "ActiveCase") {
                ActiveCaseOnLoad();
            }
            //document.getElementById("buybutton").onclick =funk;
        }
    };
    var url = "FrontController?command=getJSP&page=" + page;
    xhttp.open("GET", url, true);
    xhttp.send();
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


function buttonPush() {
    var action = "404";
    var goTopg;
    switch ("" + window.sessionStorage.getItem("currentwindow")) {
        case "oldCases":
            action = "reopenCase";
            goTopg = "ActiveCase";
            break;
        case "ActiveCases":
            action = "workCase";
            goTopg = "ActiveCase";
            document.getElementById("ActiveCase").style.display = "block";
            document.getElementById("CaseInfo").style.display = "block";
            break;
        case "availCases":
            action = "takeCase";
            goTopg = "ActiveCases";
            break;
        default :
            alert("something went wrong");
            return "0";
            break;
    }
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            getJSPCases(goTopg);
            //document.getElementById("buybutton").onclick =funk;
        }
    };
    var url = "FrontController?command=CaseAction&Action=" + action;
    xhttp.open("POST", url, true);
    xhttp.send();


}
