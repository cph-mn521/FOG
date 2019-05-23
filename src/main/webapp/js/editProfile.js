/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function setValues(){
    var str = window.sessionStorage.getItem("user");
    var user = JSON.parse(str);  
    document.getElementById("name").value = (user.name);
    document.getElementById("oldPassword").value =(user.password) ;
    //document.getElementById("password").value = (user);
    document.getElementById("email").value = (user.email) ;
    document.getElementById("phonenumber").value = (user.phonenumber);
}

function editProfile(){
    var xhttp = XMLHttpRequest();
    var newName = document.getElementById("name");
    var oldPassword = document.getElementById("oldPassword");
    var newPassword = document.getElementById("password");
    var newEmail = document.getElementById("email");
    var newPhoneNumber = document.getElementById("phonenumber");

    xhttp.open("POST", "FrontController?command=editProfile&newName="+newName+
            "&newPassword="+newPassword+"&newEmail="+newEmail+"&newphoneNumber="+newPhoneNumber);
    xhttp.send();
}