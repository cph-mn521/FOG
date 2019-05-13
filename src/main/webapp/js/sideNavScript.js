function newComponent()
{
    showObject("FrontController?command=ComponentCommand&commandType=newform");
}

function newCustomer()
{
    showObject("FrontController?command=CustomerCommand&commandType=newform");
}

function newEmployee()
{
    showObject("FrontController?command=EmployeeCommand&commandType=newform");
}

function newOrder()
{
    showObject("FrontController?command=OrderCommand&commandType=newform");
}

function showComponents()
{
    showContent("ComponentCommand", "show", "componentsListTable", "prepare", "componentID");
}

function showCustomers()
{
    showContent("CustomerCommand", "show", "customersListTable", "prepare", "customerID");
}

function showEmployees()
{
    showContent("EmployeeCommand", "show", "employeesListTable", "prepare", "employeeID");
}

function showOrders()
{
    showContent("OrderCommand", "show", "ordersListTable", "prepare", "orderID");
}

function showContent(command, commandType, listenerIDListTable,
        listenerDestCommandType, listenerParameter)
{
//    checking if there's cas variable in session and if se removes it and put in div for showing List and drawings
    var caseVar = window.sessionStorage.getItem("currentwindow");
    if (caseVar != null && caseVar.includes("Case"))
    {
        makeDivs();
    } else
    {
        $("#showList").html(" ");
        $("#showDrawing").html(" ");
        $("#showObject").html(" ");
    }

    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function ()
    {
        if (this.readyState == 4 && this.status == 200)
        {
            document.getElementById("showList").innerHTML = this.responseText;
            var urlEvent = "FrontController?command=" + command +
                    "&commandType=" + listenerDestCommandType + "&" + listenerParameter + "=";
            tableEvent(listenerIDListTable, urlEvent);
        }
    };
    var url = "FrontController?command=" + command + "&commandType=" + commandType;
    xhttp.open("POST", url, true);
    xhttp.send();
}


function showObject(objectURL)
{
    //    checking if there's cas variable in session and if se removes it and put in div for showing List and drawings
    var caseVar = window.sessionStorage.getItem("currentwindow");
    if (caseVar != null && caseVar.includes("Case"))
    {
        makeDivs();
    } else
    {
        $("#showDrawing").html(" ");
        $("#showObject").html(" ");
    }
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

function showDrawing(url)
{
//    checking if there's cas variable in session and if se removes it and put in div for showing List and drawings
    var caseVar = window.sessionStorage.getItem("currentwindow");
    if (caseVar != null && caseVar.includes("Case"))
    {
        makeDivs();
    } else
    {
        $("#showList").html(" ");
        $("#showDrawing").html(" ");
        $("#showObject").html(" ");
    }

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

function makeDivs()
{
    document.getElementById("content").innerHTML = "<div id='showObject'></div><div id='showList'></div><div id='showDrawing'></div>";
    window.sessionStorage.setItem("currentwindow", "");
}