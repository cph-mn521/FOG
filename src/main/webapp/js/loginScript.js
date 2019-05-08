function login() {
    var usn = document.getElementById('usn').value;
    var psw = document.getElementById('psw').value;
    document.getElementById('USER').innerHTML = usn;
    document.getElementById('loginForm').style.display = "none";
    /*
    var req = "FrontController?command=LoginCustomer&Username="+un+"&Password="+pw;
    var xmlhttp = new XMLHttpRequest();
    xmlhttp.open("post", req, true);
    xmlhttp.onreadystatechange = function () {
        if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
            loginResults();
        }
    }
    */
}

