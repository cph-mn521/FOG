function login() {
    var usn = document.getElementById("usn").value;
    var psw = document.getElementById("psw").value;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
             
          try {
                var User = JSON.parse((xhttp.responseText));              
                LOGINCHANGE(User);
            } catch (e) {
                document.getElementById("usn").value="FORKERT!";
            }

      
        //document.getElementById("buybutton").onclick =funk;
        }
    };
    var url = "FrontController?command=Login&username="+usn+"&password="+psw;
    xhttp.open("POST", url, true);
    xhttp.send();
}   


function LOGINCHANGE(user){
    var btn = document.getElementById("logbutton");
    btn.innerHTML="logout";
    btn.onclick= logout;
    document.getElementById("usn").style.display = "none";
    document.getElementById("psw").style.display = "none";
    document.getElementById("usnDispl").innerHTML = user.name;
    document.getElementById("usnDispl").style.display="block";
    alert(user.rank)
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        alert(xhttp.responstText);
        document.getElementById("sidenav").innerHTML = xhttp.responseText;
    }};
    xhttp.open("POST", "FrontController?command=getJSP&page=sidebar&rank?="+user.rank);
    xhttp.send();
}


function logout(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var btn = document.getElementById("logbutton");
        btn.innerHTML="login";
        btn.onclick = login;
        document.getElementById("usn").style.display = "block";
        document.getElementById("psw").style.display = "block";
        document.getElementById("usnDispl").style.display="none";
        var sidebar = document.getElementById("sidebar").innerHTML = "";
        sidebar.innerHTML = "";
    }};
    xhttp.open("POST", "FrontController?command=logout");
    xhttp.send();    

    
}


