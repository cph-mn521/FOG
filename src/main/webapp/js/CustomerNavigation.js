$(function () {
    $(document).tooltip();
});


function getCJSP(el) {

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("Customercontent").innerHTML = this.responseText;
            addEventListeners(el.id);
        }
    };
    var url = "FrontController?command=Customer&Action=" + el.id;
    xhttp.open("GET", url, true);
    xhttp.send();
}


function addEventListeners(id) {
    switch (id) {
        case "Buy":
            AECarp();
            break;
    }
}

function sesOrderUpd(key, value) {
    var str = window.sessionStorage.getItem("Order");
    var ob = JSON.parse(str);
    ob[key] = value;
    window.sessionStorage.setItem("Order", JSON.stringify(ob));
}

function sesUserUpd(key, value) {
    var str = window.sessionStorage.getItem("User");
    var ob = JSON.parse(str);
    ob[key] = value;
    window.sessionStorage.setItem("User", JSON.stringify(ob));
}


function AECarp() {

    if (window.sessionStorage.getItem("Order") === null) {
        var Order = {adress: "", length: 0, width: 0, heigth: 0, roof: 1, ShedL: 0, ShedW: 0, comment: ""};
        window.sessionStorage.setItem("Order", JSON.stringify(Order));
    } else {
        FillbuySide();
    }


    $("#shedbox").click(function () {
        $("#shedinfo").slideToggle();
    });
    $("#Adr").blur(function () {
        sesOrderUpd("adress", $("#Adr").val());
    });
    $("#length").blur(function () {
        var input = document.getElementById("length").value;
        input.replace(",", ".");
        input.replace("mm", "");
        var num = Number("" + input);
        if (Number.isNaN(num) || num < 2400 || num > 8800) {
            document.getElementById("length").style.backgroundColor = "red";
        } else {
            if (num % 30 < 15) {
                num = num - num % 30;
            } else {
                num = num + (30 - num % 30);
            }
            sesOrderUpd("length", num);
            document.getElementById("length").value = num + "mm";
            document.getElementById("length").style.backgroundColor = "";
        }
    });
    $("#width").blur(function () {
        var input = document.getElementById("width").value;
        input.replace(",", ".");
        input.replace("mm", "");
        var num = Number("" + input);
        if (Number.isNaN(num) || num < 2400 || num > 7500) {
            document.getElementById("width").style.backgroundColor = "red";
        } else {
            if (num % 30 < 15) {
                num = num - num % 30;
            } else {
                num = num + (30 - num % 30);
            }
            sesOrderUpd("width", num);
            document.getElementById("width").value = num + "mm";
            document.getElementById("width").style.backgroundColor = "";
        }
    });
    $("#heigth").blur(function () {
        var input = document.getElementById("heigth").value;
        input.replace(",", ".");
        input.replace("mm", "");
        var num = Number("" + input);
        if (Number.isNaN(num) || num < 2100 || num > 5100) {
            document.getElementById("heigth").style.backgroundColor = "red";
        } else {
            if (num % 30 < 15) {
                num = num - num % 30;
            } else {
                num = num + (30 - num % 30);
            }
            sesOrderUpd("heigth", num);
            document.getElementById("heigth").value = num + "mm";
            document.getElementById("heigth").style.backgroundColor = "";
        }
    });
    $("#ShedL").blur(function () {
        var input = document.getElementById("ShedL").value;
        input = input.replace(",", ".");
        input = input.replace("mm", "");
        var num = Number("" + input);
        if (Number.isNaN(num) || num < 1200 || num > Number(document.getElementById("length").value.replace("mm", ""))) {
            document.getElementById("ShedL").style.backgroundColor = "red";
        } else {
            if (num % 30 < 15) {
                num = num - num % 30;
            } else {
                num = num + (30 - num % 30);
            }
            sesOrderUpd("ShedL", num);
            document.getElementById("ShedL").value = num + "mm";
            document.getElementById("ShedL").style.backgroundColor = "";
        }
    });
    $("#ShedW").blur(function () {
        var input = document.getElementById("ShedW").value;
        input = input.replace(",", ".");
        input = input.replace("mm", "");
        var num = Number("" + input);
        if (Number.isNaN(num) || num < 1200 || num > Number(document.getElementById("width").value.replace("mm", ""))) {
            document.getElementById("ShedW").style.backgroundColor = "red";
        } else {
            if (num % 30 < 15) {
                num = num - num % 30;
            } else {
                num = num + (30 - num % 30);
            }
            sesOrderUpd("ShedW", num);
            document.getElementById("ShedW").value = num + "mm";
            document.getElementById("ShedW").style.backgroundColor = "";
        }
    });
    $("#commentOwner").blur(function () {
        sesOrderUpd("comment", $("#commentOwner").val());
    });
    $("#targetSelect").change(function () {        
        sesOrderUpd("roof", $("#targetSelect").val());
    });
    $("#dialog1").dialog({autoOpen: false});
    $("#dialog1").dialog({
        autoOpen: false,
        draggable: false,
        resizable: false,
        height: 300,
        width: 510,
        show: {
            effect: 'fade',
            duration: 200
        }
    });
    // Finally open the dialog! The open function above will run once
    // the dialog has opened. It runs the close function! After it has
    // faded out the dialog is destroyed

    if (window.sessionStorage.getItem("user") === null) {
        $("#buybutton").on().click(function () {
            $("#dialog1").dialog("open");
        });
    } else {
        $("#buybutton").on().click(function () {
            buy();
        });
    }
    $("#dialog2").dialog({autoOpen: false});
    $("#dialog2").dialog({
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

function showRegisterBox() {
    $("#dialog1").dialog("close");
    $("#dialog2").dialog("open");
}
function showLoginBox() {
    $("#dialog2").dialog("close");
    $("#dialog1").dialog("open");
}



function AEReg() {
    if (window.sessionStorage.getItem("User") === null) {
        let user = {name: "", email: "", phone_number: "", password: ""};
        window.sessionStorage.setItem("User", JSON.stringify(user));
    } else {
        FillRegister();
    }

    $("1").change(function () {
        sesUserUpd("name", $("1").val())
    });
    $("2").change(function () {
        sesUserUpd("email", $("1").val())
    });
    $("3").change(function () {
        sesUserUpd("phone_number", $("1").val())
    });
    $("5").change(function () {
        if ($("5").val() != $("4").val()) {
            alert("password matcher ikke!");
            $("4").val("");
            $("5").val("");
        } else {
            sesUserUpd("password", $("4").val());
        }
    });
}

function FillbuySide() {
    var str = window.sessionStorage.getItem("Order");
    var Order = JSON.parse(str);
    document.getElementById("Adr").value = (Order.adress);
    document.getElementById("length").value = (Order.length);
    document.getElementById("width").value = (Order.width);
    document.getElementById("heigth").value = (Order.heigth);
    document.getElementById("ShedL").value = (Order.ShedL);
    document.getElementById("ShedW").value = (Order.ShedW);
    document.getElementById("targetSelect").value = (Order.roof);
    document.getElementById("commentOwner").value = (Order.comment);
}

function FillRegister() {

}

function buy() {
    var str = window.sessionStorage.getItem("Order");
    //alert(str);
    var O = JSON.parse(str);
    var url = "FrontController?command=OrderCommand&commandType=newfinished&customerAddress=" + O.adress +
            "&roofTypeID=" + O.roof + ":0" + "&cartportLength=" + O.length +
            "&cartportWidth=" + O.width + "&cartportHeight=" + O.heigth +
            "&shedLength=" + O.ShedL + "&shedWidth=" + O.ShedW +
            "&shedHeight=" + O.heigth + "&msg=" + O.comment;
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("Customercontent").innerHTML = this.responseText;
            //document.getElementById("buybutton").onclick =funk;
        }
    };
    xhttp.open("POST", url, true);
    xhttp.send();
}


function register() {

    var url = "FrontController?command=CustomerCommand&commandType=newfinished" +
            "&email=" + $("#1").val() + "&name=" + $("#2").val() + "&phoneNumber=" + $("#3").val() +
            "&password=" + $("#4").val();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var user = {email: $("#1").val(), name: $("#2").val(), phone_number: $("#3").val(), password: $("#4").val()};
            $("#dialog2").dialog("close");
            window.sessionStorage.setItem("user", JSON.stringify(user));
            $("#usn").fadeOut();
            $("#psw").fadeOut();
            $("#register").slideUp();
            $("#profile").slideDown();
            $("#MyCase").slideDown();
            $("#logbutton").text("Log Ud");
            $("#buybutton").on().click(function () {
                logout();
            });
            $("#buybutton").on().click(function () {
                buy();
            });
            //document.getElementById("buybutton").onclick =funk;
        }
    };
    xhttp.open("POST", url, true);
    xhttp.send();
}

function btnpushin() {
    var url = "FrontController?command=LoginCustomer&username=" + $("#usn").val() +
            "&password=" + $("#psw").val();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var user = JSON.parse(xhttp.responseText);
            $("#dialog1").dialog("close");
            window.sessionStorage.setItem("user", JSON.stringify(user));
            $("#usn").fadeOut();
            $("#psw").fadeOut();
            $("#register").slideUp();
            $("#profile").slideDown();
            $("#MyCase").slideDown();
            $("#logbutton").html("Log Ud");
            $("#logbutton").on().click(function () {
                logout();
            });
            //document.getElementById("buybutton").onclick =funk;
        }
    };
    xhttp.open("POST", url, true);
    xhttp.send();

}

function login() {
    var url = "FrontController?command=LoginCustomer&username=" + $("#1l").val() +
            "&password=" + $("#2l").val();
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            var user = JSON.parse(xhttp.responseText);
            $("#dialog1").dialog("close");
            window.sessionStorage.setItem("user", JSON.stringify(user));
            $("#usn").fadeOut();
            $("#psw").fadeOut();
            $("#register").slideUp();
            $("#profile").slideDown();
            $("#MyCase").slideDown();
            $("#logbutton").html("Log Ud");
            $("#logbutton").on().click(function () {
                logout();
            });
            $("#buybutton").on().click(function () {
                buy();
            });
            //document.getElementById("buybutton").onclick =funk;
        }
    };
    xhttp.open("POST", url, true);
    xhttp.send();
}

function logout() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            window.sessionStorage.clear();
            location.reload();
        }
    };
    xhttp.open("POST", "FrontController?command=Logout");
    xhttp.send();
}
/*
 var xhttp = new XMLHttpRequest();
 
 
 
 */

