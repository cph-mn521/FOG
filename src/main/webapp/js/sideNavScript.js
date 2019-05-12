function toggleContent(tagID)
{
    $('#content #contentContainer .showing').addClass('hiding');
    $('#content #contentContainer .showing').removeClass('showing');
    $('#content #contentContainer ' + tagID).removeClass('hiding');
    $('#content #contentContainer ' + tagID).addClass('showing');
}

function clearContentDivShowObject()
{
    $("#showObject").html(" ");
}

function clearContentDivShowList()
{
    $("#showList").html(" ");
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
    xhttp.open("POST", "FrontController?command=ShowDrawing", true);
    xhttp.send();
}

function showOrders()
{

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("showOrders").innerHTML = this.responseText;
            tableEvent("#orderListTable", "FrontController?command=ShowOrder&orderID=", "#showOrderContent");

        }
    };
    xhttp.open("POST", "FrontController?command=ShowOrders", true);
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
            tableEventComp("#componentListTable", "FrontController?command=ChangingComponent&componentID=", "#changingComponents");
        }
    };
    xhttp.open("POST", url, true);
    xhttp.send();
}

function showComponents()
{
    showList("ShowComponents", "componentsListTable", "ChangingComponent", "componentID");
}

function showCustomers()
{
    showList("ShowCustomers", "customersListTable", "ChangingCustomer", "customerID");
}

function showEmployees()
{
    showList("ShowEmployees", "employeesListTable", "ChangingEmployee", "employeeID");
}

function showOrders()
{
    showList("ShowOrders", "ordersListTable", "ChangingOrder", "orderID");
}

function showList(command, listenerIDListTable, listenerDestCommand, listenerParameter)
{
    clearContentDivShowList();
    clearContentDivShowObject();
    
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("showList").innerHTML = this.responseText;
            var urlEvent = "FrontController?command=" + listenerDestCommand + "&" + listenerParameter + "=";
            tableEvent(listenerIDListTable, urlEvent);
        }
    };
    var url = "FrontController?command=" + command;
    xhttp.open("POST", url, true);
    xhttp.send();
}


function showObject(objectURL)
{
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("showObject").innerHTML = this.responseText;
            buttonEvent()
        }
    };
    xhttp.open("POST", objectURL, true);
    xhttp.send();
}


function newEmployee()
{
//    alert(url);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("showEmployee").innerHTML = this.responseText;
        }
    };
    xhttp.open("POST", "FrontController?command=NewFormEmployee", true);
    xhttp.send();
}

function newCustomer()
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
    xhttp.open("POST", "FrontController?command=NewFormCustomer", true);
    xhttp.send();
}

function newComponent()
{
//    alert(url);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("showComponent").innerHTML = this.responseText;
        }
    };
    xhttp.open("POST", "FrontController?command=NewFormComponent", true);
    xhttp.send();
}

function newOrder()
{
//    alert(url);
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("showOrder").innerHTML = this.responseText;
        }
    };
    xhttp.open("POST", "FrontController?command=NewFormOrder", true);
    xhttp.send();
}