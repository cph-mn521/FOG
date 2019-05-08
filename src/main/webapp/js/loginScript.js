function login() {
    var usn = document.getElementById('usn').value;
    var psw = document.getElementById('psw').value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        LOGINCHANGE();
        }
    };
    xhttp.open("GET", "FrontController?command=JSTEST", true);
    //xhttp.open("GET", "FrontController?command=login&username="+un+"&password="+psw, true);
    xhttp.send();
}

function LOGINCHANGE(){
    var btn = document.getElementById("logbutton");
    btn.innerHTML="logout";
    btn.onclick= logout;
    document.getElementById("loginform").style.display = "none";
    document.getElementById("usnDispl").value = "logget ind";
    document.getElementById("usnDispl").style.display="block";
    
}

function logout(){
    var btn = document.getElementById("logbutton");
    btn.innerHTML="login";
    btn.onclick = login;
    document.getElementById("loginform").style.display = "block";
    document.getElementById("usnDispl").style.display="none";
    
}

