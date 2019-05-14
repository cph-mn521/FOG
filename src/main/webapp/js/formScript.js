function newOrderForm()
{
    var customerAddress = document.getElementById("customerAddress").value;
    var roofTypeID = document.getElementById("roofTypeID").value;
    var cartportLength = document.getElementById("cartportLength").value;
    var cartportWidth = document.getElementById("cartportWidth").value;
    var cartportHeight = document.getElementById("cartportHeight").value;
    var shedLength = document.getElementById("shedLength").value;
    var shedWidth = document.getElementById("shedWidth").value;
    var shedHeight = document.getElementById("shedHeight").value;

//    //    checking if there's cas variable in session and if se removes it and put in div for showing List and drawings
//    var caseVar = window.sessionStorage.getItem("currentwindow");
//    if (caseVar != null && caseVar.includes("Case"))
//    {
//        makeDivs();
//    } else
//    {
//        $("#showList").html(" ");
//        $("#showDrawing").html(" ");
//        $("#showObject").html(" ");
//    }
//
//
//    var xhttp = new XMLHttpRequest();
//    xhttp.onreadystatechange = function ()
//    {
//        if (this.readyState == 4 && this.status == 200)
//        {
//            document.getElementById("showList").innerHTML = this.responseText;
//
//        }
//    };
    var url = "FrontController?command=OrderCommand&commandType=newfinished&customerAddress=" + customerAddress +
            "&roofTypeID=" + roofTypeID + "&cartportLength=" + cartportLength +
            "&cartportWidth=" + cartportWidth + "&cartportHeight=" + cartportHeight +
            "&shedLength=" + shedLength + "&shedWidth=" + shedWidth +
            "&shedHeight=" + shedHeight;
    showObject(url);
}




