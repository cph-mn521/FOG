


function getCJSP(el) {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("Customercontent").innerHTML = this.responseText;
            addEventListeners(el.id);
            //document.getElementById("buybutton").onclick =funk;
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

function AECarp() {
    if (window.sessionStorage.getItem("Order") === null) { 
        var Order = {adress: "", length: 0, width: 0, Heigth: 0, roof: 0, ShedL: 0, ShedW: 0, comment: ""};
        window.sessionStorage.setItem("Order", JSON.stringify(Order));
    }else{
        FillbuySide();
    }


    $("#shedbox").click(function () {
        $("#shedinfo").slideToggle();
    });

    $("#Adr").focus(function () {
        $("#msgBox").html("Skriv din adresse i det her felt");
    });
    $("#Adr").blur(function () {
        sesOrderUpd("adress", $("#Adr").val());
    });
    $("#length").focus(function () {
        document.getElementById("length").value = "";
        $("#msgBox").html("Indtast Længden på din carport. <br>" +
                "Vær opmærksom på at længden skal være mellem 2400mm og 8800mm. ");

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

    $("#width").focus(function () {
        document.getElementById("width").value = "";
        $("#msgBox").html("Indtast Længden på din carport. <br>" +
                "Vær opmærksom på at længden skal være mellem 2400mm og 7500mm. ");
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

    $("#heigth").focus(function () {
        document.getElementById("heigth").value = "";
        $("#msgBox").html("Indtast Længden på din carport. <br>" +
                "Vær opmærksom på at længden skal være mellem 2100mm og 5100mm. ");
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

    $("#ShedL").focus(function () {
        document.getElementById("ShedL").value = "";
        $("#msgBox").html("Indtast Længden på din carport. <br>" +
                "Vær opmærksom på at længden skal være mellem 1200mm og " + document.getElementById("length").value);
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

    $("#ShedW").focus(function () {
        document.getElementById("ShedW").value = "";
        $("#msgBox").html("Indtast Længden på din carport. <br>" +
                "Vær opmærksom på at længden skal være mellem 1200mm og " + document.getElementById("width").value);
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


    $("#commentOwner").focus(function () {
        document.getElementById("ShedW").value = "";
        $("#msgBox").html("kort beskrivelse til vores medarbajdere, Skriv gerne spørgsmål og en kort beskrivelse om dit projekt");
    });
    $("#commentOwner").blur(function () {
        sesOrderUpd("comment", $("#commentOwner").val());

    });
    
    $("#targetSelect").change(function(){
        sesOrderUpd("roof",$("#targetSelect").val());
    });
}

function FillbuySide(){
    var str = window.sessionStorage.getItem("Order");
    var Order = JSON.parse(str);
    document.getElementById("Adr").value =(Order.adress);
    document.getElementById("length").value =(Order.length);
    document.getElementById("width").value =(Order.width);
    document.getElementById("heigth").value =(Order.Heigth);
    document.getElementById("ShedL").value =(Order.ShedL);
    document.getElementById("ShedW").value =(Order.ShedW);
    document.getElementById("targetSelect").value =(Order.roof);
    document.getElementById("commentOwner").value =(Order.comment);
    
}



function buy(){
    var O = {adress: "", length: 0, width: 0, Heigth: 0, roof: 0, ShedL: 0, ShedW: 0, comment: ""};
    var url = "FrontController?command=OrderCommand&commandType=newfinished&customerAddress=" + O.adress +
            "&roofTypeID=" + O.roof + "&cartportLength=" + O.length +
            "&cartportWidth=" + O.width + "&cartportHeight=" + O.Heigth +
            "&shedLength=" + O.ShedL + "&shedWidth=" + O.ShedW +
            "&shedHeight=" + O.Heigth + "&msg =" + O.comment;    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("Customercontent").innerHTML = this.responseText;
            getCJSP(document.getElementById("MyCase"));
            //document.getElementById("buybutton").onclick =funk;
        }
    };
    xhttp.open("POST", url, true);
    xhttp.send();    
}
/*
           var url = "FrontController?command=OrderCommand&commandType=newfinished&customerAddress=" + customerAddress +
            "&roofTypeID=" + roofTypeID + "&cartportLength=" + cartportLength +
            "&cartportWidth=" + cartportWidth + "&cartportHeight=" + cartportHeight +
            "&shedLength=" + shedLength + "&shedWidth=" + shedWidth +
            "&shedHeight=" + shedHeight + "&msg =" + msg;
    showContent2(url, "OrderCommand", "ordersListTable", "prepare", "orderID");

url = FrontController?command=Customer&Action=
commandType = newform


*/


