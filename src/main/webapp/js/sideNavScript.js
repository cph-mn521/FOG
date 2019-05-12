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
        
function newComponent(){
    showObject("FrontController?command=NewFormComponent");
}

function newCustomer(){
    showObject("FrontController?command=NewFormCustomer");
}

function newEmployee(){
    showObject("FrontController?command=NewFormEmployee");
}

function newOrder(){
    showObject("FrontController?command=NewFormOrder");
}

function showComponents()
{
    showContent("ShowComponents", "componentsListTable", "ChangingComponent", "componentID");
}

function showCustomers()
{
    showContent("ShowCustomers", "customersListTable", "ChangingCustomer", "customerID");
}

function showEmployees()
{
    showContent("ShowEmployees", "employeesListTable", "ChangingEmployee", "employeeID");
}

function showOrders()
{
    showContent("ShowOrders", "ordersListTable", "ChangingOrder", "orderID");
}

function showContent(command, listenerIDListTable, listenerDestCommand, listenerParameter)
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
        }
    };
    xhttp.open("POST", objectURL, true);
    xhttp.send();
}

function toggleContent(tagID)
{
    $('#content #contentContainer .showing').addClass('hiding');
    $('#content #contentContainer .showing').removeClass('showing');
    $('#content #contentContainer ' + tagID).removeClass('hiding');
    $('#content #contentContainer ' + tagID).addClass('showing');
}