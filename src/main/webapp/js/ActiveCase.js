function ActiveCase() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("content").innerHTML = this.responseText;
            ActiveCaseOnLoad();
            //document.getElementById("buybutton").onclick =funk;
        }
    };
    var url = "FrontController?command=getJSP&page=ActiveCase";
    xhttp.open("GET", url, true);
    xhttp.send();
}


function ActiveCaseOnLoad() {
    window.sessionStorage.setItem("currentwindow", "ActiveCase");
    var obj = {caseId: 0, customerId: 0, employeeId: 0, status: "", msg_owner: "", msg_status: "", type: ""};
    window.sessionStorage.setItem("CaseSON", obj);
    document.getElementById("CName").addEventListener('blur',Name());
    document.getElementById("CEmail").addEventListener('blur',Name());
    document.getElementById("CTelefon").addEventListener('blur',Name());

}




function updCaseSON(key, value) {
    var obj = window.sessionStorage.getItem("CaseSON");
    obj[key] = value;
    window.sessionStorage.setItem("CaseSON", obj);
}

function requestAction(action) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            alert(xhttp.responseText);
        }
    };
    var url = "FrontController?command=CaseAction&Action=" + action;
    xhttp.open("POST", url, true);
    xhttp.send();
}


