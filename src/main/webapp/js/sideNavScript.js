function toggleContent(tagID)
{
    $('#content #contentContainer .showing').addClass('hiding');
    $('#content #contentContainer .showing').removeClass('showing');
    $('#content #contentContainer ' + tagID).removeClass('hiding');
    $('#content #contentContainer ' + tagID).addClass('showing');
}

function showOrders()
{

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("showOrderHistory").innerHTML = this.responseText;
            tableEvent("#orderListTable", "FrontController?command=ShowOrder&orderID=", "#showOrderContent");

        }
    };
    xhttp.open("GET", "FrontController?command=ShowOrders", true);
    xhttp.send();
}

function showOrder(url)
{

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
//        var order = window.sessionStorage.getItem("enordre");
            document.getElementById("showOrderContent").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}