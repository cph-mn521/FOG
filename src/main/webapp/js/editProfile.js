/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function profileLoad(){
   
    $("#dialog1").dialog({
        autoOpen: false,
        draggable: false,
        resizable: false,
        height: 500,
        width: 510,
        show: {
            effect: 'fade',
            duration: 200
        }
        , hide: {
            effect: 'fade',
            duration: 200
        }
    });
$(".ui-dialog-titlebar").remove();
}

function showProfile()
{
//    checking if there's case variable in session and if se removes it and put in div for showing List and drawings
    var caseVar = window.sessionStorage.getItem("currentwindow");
    if (caseVar != null && caseVar.includes("Case"))
    {
        makeDivs();
    } else
    {
        $("#showList").html(" ");
        $("#showDrawing").html(" ");
        $("#showObject").html(" ");
    }

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("content").innerHTML = this.responseText;
           // profileLoad();
        }
    };
        var url = "FrontController?command=getJSP&page=profile";
    xhttp.open("GET", url, true);
    xhttp.send();
}

function editProfile(){
    var xhttp = new XMLHttpRequest();
    var newName = document.getElementById("name").value;
//    var oldPassword = document.getElementById("oldPassword").value;
    var newPassword = document.getElementById("password").value;
    var newEmail = document.getElementById("email").value;
    var newPhoneNumber = document.getElementById("phoneNumber").value;

    xhttp.open("POST", "FrontController?command=editProfile&newName="+newName+
            "&newPassword="+newPassword+"&newEmail="+newEmail+"&newphoneNumber="+newPhoneNumber);
    xhttp.send();
}