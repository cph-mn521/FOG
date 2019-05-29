
function checkReaction()
{
    var txt;
    var r = confirm("Er du sikker på at du vil fjerne!");
    if (r == true)
    {
        return true;
    } else
    {
        return false;
    }
}


//###########################
//##########Component########
//###########################

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @returns {undefined}
 */
function newComponentForm()
{
    var description = document.getElementById("description").value;
    var helpText = document.getElementById("helpText").value;
    var length = document.getElementById("length").value;
    var width = document.getElementById("width").value;
    var height = document.getElementById("height").value;
    var price = document.getElementById("price").value;

    var url = "FrontController?command=ComponentCommand&commandType=newfinished" +
            "&description=" + description + "&helpText=" + helpText +
            "&length=" + length + "&width=" + width +
            "&height=" + height + "&price=" + price;

    showContent2(url, "ComponentCommand", "componentsListTable", "prepare", "componentID");
}

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @returns {undefined}
 */
function changeComponentForm()
{
    var description = document.getElementById("description").value;
    var helpText = document.getElementById("helpText").value;
    var length = document.getElementById("length").value;
    var width = document.getElementById("width").value;
    var height = document.getElementById("height").value;
    var price = document.getElementById("price").value;

    var url = "FrontController?command=ComponentCommand&commandType=changed" +
            "&description=" + description + "&helpText=" + helpText +
            "&length=" + length + "&width=" + width +
            "&height=" + height + "&price=" + price;

    showContent2(url, "ComponentCommand", "componentsListTable", "prepare", "componentID");
}

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @param {type} componentID
 * @returns {undefined}
 */
function removeComponentForm(componentID)
{
    if (checkReaction())
    {
        var description = document.getElementById("description").value;

        var url = "FrontController?command=ComponentCommand&commandType=remove" +
                "&componentID=" + componentID;

        showContent2(url, "ComponentCommand", "componentsListTable", "prepare", "componentID");
    }
}

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @returns {undefined}
 */
function regretComponentForm()
{
    showContent("ComponentCommand", "show", "componentsListTable", "prepare", "componentID");
}



//###########################
//##########Customer#########
//###########################

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @returns {undefined}
 */
function newCustomerForm()
{
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var phoneNumber = document.getElementById("phoneNumber").value;

    var url = "FrontController?command=CustomerCommand&commandType=newfinished" +
            "&name=" + name + "&email=" + email +
            "&password=" + password + "&phoneNumber=" + phoneNumber;
    showContent2(url, "CustomerCommand", "customersListTable", "prepare", "customerID");
}

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @returns {undefined}
 */
function changeCustomerForm()
{
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var phoneNumber = document.getElementById("phoneNumber").value;

    var url = "FrontController?command=CustomerCommand&commandType=changed" +
            "&name=" + name + "&email=" + email +
            "&phoneNumber=" + phoneNumber;
    showContent2(url, "CustomerCommand", "customersListTable", "prepare", "customerID");
}

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @param {type} customerID
 * @returns {undefined}
 */
function removeCustomerForm(customerID)
{
    if (checkReaction())
    {
        var url = "FrontController?command=CustomerCommand&commandType=remove" +
                "&customerID=" + customerID;

        showContent2(url, "CustomerCommand", "customersListTable", "prepare", "customerID");
    }
}

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @returns {undefined}
 */
function regretCustomerForm()
{
    showContent("CustomerCommand", "show", "customersListTable", "prepare", "customerID");
}


//###########################
//##########Employee#########
//###########################

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @returns {undefined}
 */
function newEmployeeForm()
{
    var name = document.getElementById("name").value;
    var rank = document.getElementById("rank").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var phoneNumber = document.getElementById("phoneNumber").value;

    var url = "FrontController?command=EmployeeCommand&commandType=newfinished" +
            "&name=" + name + "&rank=" + rank +
            "&email=" + email + "&phoneNumber=" + phoneNumber +
            "&password=" + password;
    showContent2(url, "EmployeeCommand", "employeesListTable", "prepare", "employeeID");

}

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @returns {undefined}
 */
function changeEmployeeForm()
{
    var name = document.getElementById("name").value;
    var rank = document.getElementById("rank").value;
    var email = document.getElementById("email").value;
    var phoneNumber = document.getElementById("phoneNumber").value;

    var url = "FrontController?command=EmployeeCommand&commandType=changed" +
            "&name=" + name + "&rank=" + rank +
            "&email=" + email + "&phoneNumber=" + phoneNumber;
    showContent2(url, "EmployeeCommand", "employeesListTable", "prepare", "employeeID");
}

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @param {type} employeeID
 * @returns {undefined}
 */
function removeEmployeeForm(employeeID)
{
    if (checkReaction())
    {
        var url = "FrontController?command=EmployeeCommand&commandType=remove" +
                "&employeeID=" + employeeID;

        showContent2(url, "EmployeeCommand", "employeesListTable", "prepare", "employeeID");
    }
}

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @returns {undefined}
 */
function regretEmployeeForm()
{
    showContent("EmployeeCommand", "show", "employeesListTable", "prepare", "employeeID");
}


//###########################
//##########Order############
//###########################

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @returns {undefined}
 */
function newOrderForm()
{
    var customerAddress = document.getElementById("customerAddress").value;
    var roofTypeID = document.getElementById("roofTypeID").value;
    var cartportLength = document.getElementById("cartportLength").value;
    var cartportWidth = document.getElementById("cartportWidth").value;
    var cartportHeight = document.getElementById("cartportHeight").value;
    var shedLength = 0; // document.getElementById("shedLength").value; //  Shed is future upgrade
    var shedWidth = 0; // document.getElementById("shedWidth").value;   //  Shed is future upgrade
    var shedHeight = 0; // document.getElementById("shedHeight").value; //  Shed is future upgrade
    var msg = document.getElementById("msg").value;

    var url = "FrontController?command=OrderCommand&commandType=newfinished&customerAddress=" + customerAddress +
            "&roofTypeID=" + roofTypeID + "&cartportLength=" + cartportLength +
            "&cartportWidth=" + cartportWidth + "&cartportHeight=" + cartportHeight +
            "&shedLength=" + shedLength + "&shedWidth=" + shedWidth +
            "&shedHeight=" + shedHeight + "&msg=" + msg;
    showContent2(url, "OrderCommand", "ordersListTable", "prepare", "orderID");
}

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @returns {undefined}
 */
function changeOrderForm()
{
    var totalPrice = document.getElementById("totalPrice").value;
    var url = "FrontController?command=OrderCommand&commandType=changed" +
            "&totalPrice=" + totalPrice;

    showContent2(url, "OrderCommand", "ordersListTable", "prepare", "orderID");
}

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @param {type} orderID
 * @returns {undefined}
 */
function removeOrderForm(orderID)
{
    if (checkReaction())
    {
        var url = "FrontController?command=OrderCommand&commandType=remove" +
                "&orderID=" + orderID;

        showContent2(url, "OrderCommand", "ordersListTable", "prepare", "orderID");
    }
}

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @returns {undefined}
 */
function regretOrderForm()
{
    showContent("OrderCommand", "show", "ordersListTable", "prepare", "orderID");
}

/**
 * Receive form inputs and feeds AJAX function with attibutes
 * 
 * @param {type} orderID
 * @returns {undefined}
 */
function orderSent(orderID)
{
    var url = "FrontController?command=OrderCommand&commandType=ordersent" +
            "&orderID=" + orderID;
    showContent2(url, "OrderCommand", "ordersListTable", "prepare", "orderID");
}

