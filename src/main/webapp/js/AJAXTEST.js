function TEST() {
  
  document.getElementById("test1").innerHTML = "Knappen er trykken på";
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        document.getElementById("test").innerHTML = this.responseText;
      
        //document.getElementById("buybutton").onclick =funk;
    }
  };
  xhttp.open("GET", "FrontController?command=JSTEST", true);
  xhttp.send();
}


function funk(){
    
    document.getElementById("buybox").innerHTML = "du har købt en karport.. yaaay.";
}



