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
            document.getElementById("showOrderContent").innerHTML = this.responseText;
            tableEventComp("#componentListTable", "FrontController?command=ChangingComponents&componentID=", "#changingComponents");
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function showDrawing(url)
{

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("showDrawing").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", "FrontController?command=ShowDrawing", true);
    xhttp.send();
}

function showComponents()
{

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("changeContent").innerHTML = this.responseText;
            tableEventComp("#componentListTable", "FrontController?command=ChangingComponents&componentID=", "#changingComponents");
        }
    };
    xhttp.open("GET", "FrontController?command=ShowComponents", true);
    xhttp.send();
}


function showComponent(url)
{
//    alert(url);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("changeContent").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function showEmployees()
{

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("showEmployees").innerHTML = this.responseText;
            tableEventComp("#employeesListTable", "FrontController?command=ChangingEmployees&employeeID=", "#changingEmployees");
        }
    };
    xhttp.open("GET", "FrontController?command=ShowEmployees", true);
    xhttp.send();
}


function showEmployee(url)
{
//    alert(url);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("changeEmployee").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}

function showCustomers()
{

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("showCustomers").innerHTML = this.responseText;
            tableEventComp("#customersListTable", "FrontController?command=ChangingCustomers&customerID=", "#changingCustomers");
        }
    };
    xhttp.open("GET", "FrontController?command=ShowCustomers", true);
    xhttp.send();
}


function showCustomer(url)
{
//    alert(url);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("showCustomer").innerHTML = this.responseText;
        }
    };
    xhttp.open("GET", url, true);
    xhttp.send();
}