
function createEmpCase() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            alert("Sag oprettet.");

            //document.getElementById("buybutton").onclick =funk;
        }
    };
    xhttp.open("POST", "FrontController?command=CaseAction&Action=new", true);   
    xhttp.send(""+$("#commentOwner").val());
}

function createMsg() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            alert("Besked sendt.");

            //document.getElementById("buybutton").onclick =funk;
        }
    };
    var obj = $("#targetSelect").val() + "--.--..-"+$("#Titel").val()+"--.--..-"+$("#msg").val();
    xhttp.open("POST", "FrontController?command=msgAction&Action=new", true);
    xhttp.send(obj);
}


