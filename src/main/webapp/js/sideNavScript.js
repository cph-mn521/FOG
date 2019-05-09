function toggleContent(tagID)
{
    $('#content #contentContainer .showing').addClass('hiding');
    $('#content #contentContainer .showing').removeClass('showing');
    $('#content #contentContainer ' + tagID).removeClass('hiding');
    $('#content #contentContainer ' + tagID).addClass('showing');
}

function showOrders() {
  
  var xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function() {
    if (this.readyState == 4 && this.status == 200) {
        var order = sessionStorage.getItem("enordre");
      document.getElementById("ajax").innerHTML = order;
      document.getElementById("test").innerHTML = this.responseText;
//      document.getElementById("buybutton").addEventListener("click", funk());
    }
  };
  xhttp.open("GET", "FrontController?command=ShowOrders", true);
  xhttp.send();
}