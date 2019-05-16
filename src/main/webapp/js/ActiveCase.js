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
    var CaseSON = {caseId: 0, customerId: 0, employeeId: 0, status: "", msg_owner: "", msg_status: document.getElementById("msgSTAT").innerHTML, type: ""};
    window.sessionStorage.setItem("CaseSON", JSON.stringify(CaseSON));
    var UserSON = {name: document.getElementById("OWNERNAME").innerHTML, email: document.getElementById("OWNEREMAIL").innerHTML, phone: document.getElementById("OWNERPHONE").innerHTML};
    window.sessionStorage.setItem("UserSON", JSON.stringify(UserSON));

    document.getElementById("CName").addEventListener('change', function () {
        alert("!");
        var el = document.getElementById("OWNERNAME");
        el.innerHTML = this.value;
        el.style.color = "green";
        updUserSON("name", this.value);
    });
    document.getElementById("CEmail").addEventListener('change', function () {
        var el = document.getElementById("OWNEREMAIL");
        el.innerHTML = this.value;
        el.style.color = "green";
        updUserSON("email", this.value);
    });
    document.getElementById("CTelefon").addEventListener('change', function () {
        var el = document.getElementById("OWNERPHONE");
        el.innerHTML = this.value;
        el.style.color = "green";
        updUserSON("phone", this.value);
    });
    document.getElementById("changeDescr").addEventListener('change', function () {
        
        var el = document.getElementById("msgOWNER");
        el.innerHTML = this.value;
        el.style.color = "green";
    });
    document.getElementById("addnote").addEventListener('change', function () {
        updCaseSON("msg_owner", this.value);
        var el = document.getElementById("msgSTAT");
        el.innerHTML += "<p style=\"color:green;\" >" + this.value + "</p>";
        updCaseSON("msg_owner", "<p>" + this.value + "</p>");
    });
    document.getElementById("newNote").addEventListener('change', function () {
        updCaseSON("msg_owner", this.value);
        var el = document.getElementById("msgSTAT");
        el.innerHTML = "<p style=\"color:green;\" >" + this.value + "</p>";
        updCaseSON("msg_owner", "<p>" + this.value + "</p>");
    });
}

function updUserSON(key, value) {
    var str = window.sessionStorage.getItem("UserSON");
    var ob = JSON.parse(str);
    ob[key] = value;
    window.sessionStorage.setItem("UserSON", JSON.stringify(ob));
}


function updCaseSON(key, value) {
    var str = window.sessionStorage.getItem("CaseSON");
    var ob = JSON.parse(str);
    ob[key] = value;
    window.sessionStorage.setItem("CaseSON", JSON.stringify(ob));
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

function UPDCase() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("content").innerHTML = this.responseText;
            ActiveCaseOnLoad();
            //document.getElementById("buybutton").onclick =funk;
        }
    };
    var CaseSON = window.sessionStorage.getItem("CaseSON");
    var UserSON = window.sessionStorage.getItem("UserSON");
    var url = "FrontController?command=caseAction&Action=updCase&case=" + CaseSON + "&user=" + UserSON;
    alert(url);
    xhttp.open("GET", url, true);
    xhttp.send();

}