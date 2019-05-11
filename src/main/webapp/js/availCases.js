

function AvailCases(){
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        document.getElementById("content").innerHTML = this.responseText;
        AvailCasesPop();
        //document.getElementById("buybutton").onclick =funk;
        }
    };
    xhttp.open("GET", "FrontController?command=getJSP&page=availCases", true);
    xhttp.send();
}


function AvailCasesPop(){
    //all JS functionality goes here.
    
}


function getCase(e){
    document.getElementById("selectedAC").innerHTML = "<h3>du klikkede på: " +e.target.id +"</h3>";
}