function login() {
  var usn = document.getElementById('usn').value;
  var psw = document.getElementById('psw').value;
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
            try {
                alert("hej");
                var User = JSON.parse((xhttp.responseText));              
                
            } catch (e) {
                alert("nej");
                document.getElementById("usn").value="FORKERT!";
            }

      
        //document.getElementById("buybutton").onclick =funk;
    }
  };
  xhttp.open("POST", "FrontController?command=Login&username?="+usn+"&password="+psw, true);
  xhttp.send();
}   


function LOGINCHANGE(user){
    var btn = document.getElementById("logbutton");
    btn.innerHTML="logout";
    btn.onclick= logout;
    document.getElementById("usn").style.display = "none";
    document.getElementById("psw").style.display = "none";
    document.getElementById("usnDispl").value = user.name;
    document.getElementById("usnDispl").style.display="block";
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var sidebar = document.getElementById("sidebar").innerHTML = xhttp.responseText;
    }};
    xhttp.open("POST", "FrontController?command=Sidebar&rank?="+user.rank);
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
    xhttp.open("POST", "FrontController?command=sidebar&rank?="+user.rank);
    xhttp.send();    

    
}


