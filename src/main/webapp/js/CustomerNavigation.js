


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

function AECarp() {
    $("#shedbox").click(function () {
        $("#shedinfo").slideToggle();
    });
    $("#Adr").focus(function () {
        $("#msgBox").text("Skriv din adresse i det her felt");

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
            document.getElementById("heigth").value = num + "mm";
            document.getElementById("heigth").style.backgroundColor = "";
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
            document.getElementById("heigth").value = num + "mm";
            document.getElementById("heigth").style.backgroundColor = "";
        }
    });
}










