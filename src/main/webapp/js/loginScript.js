$( document ).ready(function() {
    var xhttp = new XMLHttpRequest();
    var url = "FrontController?command=JSTEST";
    xhttp.open("POST", url, true);
});

function login() {
    var usn = document.getElementById("usn").value;
    var psw = document.getElementById("psw").value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            try {
                var User = JSON.parse((xhttp.responseText));
                window.sessionStorage.setItem("user", JSON.stringify(User));
                LOGINCHANGE(User);
            } catch (e) {
                alert("Burgenavn eller Password er forkert.");
            }
        }
    };
    var url = "FrontController?command=Login&username=" + usn + "&password=" + psw;
    xhttp.open("POST", url, true);
    xhttp.send();
}


function LOGINCHANGE(user) {
    document.location.reload();
    /*
    var btn = document.getElementById("logbutton");

    btn.innerHTML="logout";
    btn.onclick= logout;
    document.getElementById("usn").style.display = "none";
    document.getElementById("psw").style.display = "none";
    document.getElementById("usnDispl").innerHTML = user.name;
    document.getElementById("usnDispl").style.display = "block";
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("sidenav").innerHTML = xhttp.responseText;
            document.location.reload(true);
        }
    };
    xhttp.open("POST", "FrontController?command=getJSP&page=sidebar&rank=" + user.rank);
    xhttp.send();
    */
}


function logout() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
//        var btn = document.getElementById("logbutton");
//        btn.innerHTML="login";
//        btn.onclick = login;
//        document.getElementById("usn").style.display = "block";
//        document.getElementById("psw").style.display = "block";
//        document.getElementById("usnDispl").style.display="none";
//        var sidebar = document.getElementById("sidebar").innerHTML = "";
            sidebar.innerHTML = "";
            document.location.reload();
        }
    };
    xhttp.open("POST", "FrontController?command=Logout");
    xhttp.send();


}


