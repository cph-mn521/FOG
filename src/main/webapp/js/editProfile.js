/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function setValues(){
    var form = document 
}




function editProfile(){
    var xhttp = XMLHttpRequest();
    var form = document.getElementById("editForm");
    var newName = form.getAttribute("name");
    var oldPassword = form.getAttribute("oldPassword");
    var newPassword = form.getAttribute("password");
    var newEmail = form.getAttribute("email");
    var newPhoneNumber = form.getAttribute("phonenumber");

    xhttp.open("POST", "FrontController?command=editProfile&newName="+newName+
            "&newPassword="+newPassword+"&newEmail="+newEmail+"&newphoneNumber="+newPhoneNumber);
    xhttp.send();
}