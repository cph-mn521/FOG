/**
 * 
 * @returns {undefined}
 */
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

/**
 * 
 * @returns {undefined}
 */
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

/**
 * 
 * @param {type} command
 * @param {type} commandType
 * @param {type} listenerIDListTable
 * @param {type} listenerDestCommandType
 * @param {type} listenerParameter
 * @returns {undefined}
 */
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

/**
 * 
 * @param {type} objectURL
 * @returns {undefined}
 */
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

/**
 * 
 * @param {type} url
 * @returns {undefined}
 */
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

/**
 * 
 * @returns {undefined}
 */
function makeDivs()
{
    document.getElementById("content").innerHTML = "<div id='showObject'></div><div id='showList'></div><div id='showDrawing'></div>";
    window.sessionStorage.setItem("currentwindow", "");
}

/**
 * 
 * @param {type} listTableID
 * @param {type} urlString
 * @returns {undefined}
 */
function tableEvent(listTableID, urlString)
{
//create mouseover table effect
    $('#' + listTableID + ':has(td)').mouseover(function (e)
    {
        $(this).css('cursor', 'crosshair');
    }); // end mouseover


//create table-click event
    $('#' + listTableID + ':has(td)').click(function (e)
    {
//clickedRow is the row you've clicked on
        var clickedRow = $(e.target).closest('tr');

//value is the value of the first cell in the row you've clicked on
        var value = clickedRow.find('td:eq(0)').text();
        var url = urlString + value;

//change shown div (in index.jsp/content.jsp)
        showObject(url);

        return;

    }); // end mouseover
}

/**
 * 
 * @param {type} buttonID
 * @param {type} urlString
 * @returns {undefined}
 */
function buttonEvent(buttonID, urlString)
{
    $('#' + listTableID + ':has(td)').click(function (e)
    {
        var clickedRow = $(e.target).closest('tr');
        showObject(urlString);

        return;

    }); // end mouseover
}
