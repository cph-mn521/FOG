
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
 * Receive form inputs and feeds AJAX function with attributes
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

    if (checkComponentForms(length, width, height, price)) //check input syntax
    {
        var newPrice = price.replace(",", ".");
        //    command/URL to send til AJAX function
        var url = "FrontController?command=ComponentCommand&commandType=newfinished" +
                "&description=" + description + "&helpText=" + helpText +
                "&length=" + length + "&width=" + width +
                "&height=" + height + "&price=" + newPrice;

        showContent2(url, "ComponentCommand", "componentsListTable", "prepare", "componentID");
    }
}

/**
 * Receive form inputs and feeds AJAX function with attributes
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

    if (checkComponentForms(length, width, height, price)) //check input syntax
    {
        var newPrice = price.replace(",", "."); //using . in DB
//    command/URL to send til AJAX function
        var url = "FrontController?command=ComponentCommand&commandType=changed" +
                "&description=" + description + "&helpText=" + helpText +
                "&length=" + length + "&width=" + width +
                "&height=" + height + "&price=" + newPrice;

        showContent2(url, "ComponentCommand", "componentsListTable", "prepare", "componentID");
    }
}

/**
 * Check if input is OK
 * 
 * @returns {undefined}
 */
function checkComponentForms(length, width, height, price)
{
    //  logic or regex checks
    if (isNaN(length) || isNaN(width) || isNaN(height))
    {
        alert('Indtast et nummer under dimensioner');
        return false;
    }

    var priceRegex = /^[\d]+((\.|,)[\d]{1,2}){0,1}$/g; //checks for 
    var priceResult = priceRegex.test(price);
    if (priceResult == false)
    {
        alert('Indtast en passende pris');
        return false;
    }
    return true;
}


/**
 * Receive form inputs and feeds AJAX function with attributes
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

//    command/URL to send til AJAX function
        showContent2(url, "ComponentCommand", "componentsListTable", "prepare", "componentID");
    }
}

/**
 * Receive form inputs and feeds AJAX function with attributes
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
 * Receive form inputs and feeds AJAX function with attributes
 * 
 * @returns {undefined}
 */
function newCustomerForm()
{
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var phoneNumber = document.getElementById("phoneNumber").value;
    if (checkCustomerForms(name, email, phoneNumber)) //check input syntax
    {
//    command/URL to send til AJAX function
        var url = "FrontController?command=CustomerCommand&commandType=newfinished" +
                "&name=" + name + "&email=" + email +
                "&password=" + password + "&phoneNumber=" + phoneNumber;
        showContent2(url, "CustomerCommand", "customersListTable", "prepare", "customerID");
    }
}

/**
 * Receive form inputs and feeds AJAX function with attributes
 * 
 * @returns {undefined}
 */
function changeCustomerForm()
{
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var phoneNumber = document.getElementById("phoneNumber").value;

    if (checkCustomerForms(name, email, phoneNumber)) //check input syntax
    {
//    command/URL to send til AJAX function
        var url = "FrontController?command=CustomerCommand&commandType=changed" +
                "&name=" + name + "&email=" + email +
                "&phoneNumber=" + phoneNumber;

        showContent2(url, "CustomerCommand", "customersListTable", "prepare", "customerID");
    }
}

/**
 * Check if input is OK
 * 
 * @returns {undefined}
 */
function checkCustomerForms(name, email, phoneNumber)
{
    //  logic or regex checks
    if (name === "" || !isNaN(name))
    {
        alert('Skriv et navn');
        return false;
    }

    //checks for email (taken from stackoverflow)
    var emailRegex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var emailResult = emailRegex.test(email);
    if (emailResult == false)
    {
        alert('Indtast en passende email adresse');
        return false;
    }

    var phoneNumberRegex = /^[+]{0,1}[\d]+$/;
    var phoneNumberResult = phoneNumberRegex.test(phoneNumber);
    if (phoneNumberResult == false)
    {
        alert('Indtast et passende telefonnummer');
        return false;
    }

    return true;
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
 * Receive form inputs and feeds AJAX function with attributes
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
 * Receive form inputs and feeds AJAX function with attributes
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

    if (checkEmployeeForms(name, rank, email, phoneNumber)) //check input syntax
    {
//    command/URL to send til AJAX function
        var url = "FrontController?command=EmployeeCommand&commandType=newfinished" +
                "&name=" + name + "&rank=" + rank +
                "&email=" + email + "&phoneNumber=" + phoneNumber +
                "&password=" + password;
        showContent2(url, "EmployeeCommand", "employeesListTable", "prepare", "employeeID");
    }
}

/**
 * Receive form inputs and feeds AJAX function with attributes
 * 
 * @returns {undefined}
 */
function changeEmployeeForm()
{
    var name = document.getElementById("name").value;
    var rank = document.getElementById("rank").value;
    var email = document.getElementById("email").value;
    var phoneNumber = document.getElementById("phoneNumber").value;

    if (checkEmployeeForms(name, rank, email, phoneNumber)) //check input syntax
    {
//    command/URL to send til AJAX function
        var url = "FrontController?command=EmployeeCommand&commandType=changed" +
                "&name=" + name + "&rank=" + rank +
                "&email=" + email + "&phoneNumber=" + phoneNumber;
        showContent2(url, "EmployeeCommand", "employeesListTable", "prepare", "employeeID");
    }
}

/**
 * Check if input is OK
 * 
 * @returns {undefined}
 */
function checkEmployeeForms(name, rank, email, phoneNumber)
{
    //  logic or regex checks
    if (name === "" || !isNaN(name))
    {
        alert('Skriv et navn');
        return false;
    }

    if (rank !== "storeworker" && rank !== "salesperson" &&
            rank !== "superadmin" && rank !== "admin")
    {
        alert('Rigtig rang skal benyttes\n(storeworker, salesperson, superadmin, admin)');
        return false;
    }

    //checks for email (taken from stackoverflow)
    var emailRegex = /^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
    var emailResult = emailRegex.test(email);
    if (emailResult == false)
    {
        alert('Indtast en passende email adresse');
        return false;
    }

    var phoneNumberRegex = /^[+]{0,1}[\d]+$/;
    var phoneNumberResult = phoneNumberRegex.test(phoneNumber);
    if (phoneNumberResult == false)
    {
        alert('Indtast et passende telefonnummer');
        return false;
    }

    return true;
}

/**
 * Receive form inputs and feeds AJAX function with attributes
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
 * Receive form inputs and feeds AJAX function with attributes
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
 * Receive form inputs and feeds AJAX function with attributes
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

//    command/URL to send til AJAX function
    var url = "FrontController?command=OrderCommand&commandType=newfinished&customerAddress=" + customerAddress +
            "&roofTypeID=" + roofTypeID + "&cartportLength=" + cartportLength +
            "&cartportWidth=" + cartportWidth + "&cartportHeight=" + cartportHeight +
            "&shedLength=" + shedLength + "&shedWidth=" + shedWidth +
            "&shedHeight=" + shedHeight + "&msg=" + msg;
    showContent2(url, "OrderCommand", "ordersListTable", "prepare", "orderID");
}

/**
 * Check if input is OK
 * 
 * @returns {undefined}
 */
function checknewOrderForms(customerAddress, cartportLength, cartportWidth, cartportHeight,
        shedLength, shedWidth, shedHeight)
{
    //  logic or regex checks
    if (isNaN(cartportLength) || isNaN(cartportWidth) || isNaN(cartportHeight) ||
            isNaN(shedLength) || isNaN(shedWidth) || isNaN(shedHeight))
    {
        alert('Indtast et nummer under dimensioner');
        return false;
    }

    var priceRegex = /^[\d]+((\.|,)[\d]{1,2}){0,1}$/g; //checks for numbers being either integer or float
    var priceResult = priceRegex.test(price);
    if (priceResult == false)
    {
        alert('Indtast en passende pris');
        return false;
    }
    return true;
}

/**
 * Receive form inputs and feeds AJAX function with attributes
 * 
 * @returns {undefined}
 */
function changeOrderForm()
{
    var totalPrice = document.getElementById("totalPrice").value;

//    command/URL to send til AJAX function
    var url = "FrontController?command=OrderCommand&commandType=changed" +
            "&totalPrice=" + totalPrice;

    showContent2(url, "OrderCommand", "ordersListTable", "prepare", "orderID");
}

/**
 * Check if input is OK
 * 
 * @returns {undefined}
 */
function checkchangeOrderForms(name, email, phoneNumber)
{


    return true;
}

/**
 * Receive form inputs and feeds AJAX function with attributes
 * 
 * @param {type} orderID
 * @returns {undefined}
 */
function removeOrderForm(orderID)
{
    if (checkReaction())
    {
//    command/URL to send til AJAX function
        var url = "FrontController?command=OrderCommand&commandType=remove" +
                "&orderID=" + orderID;

        showContent2(url, "OrderCommand", "ordersListTable", "prepare", "orderID");
    }
}

/**
 * Receive form inputs and feeds AJAX function with attributes
 * 
 * @returns {undefined}
 */
function regretOrderForm()
{
    showContent("OrderCommand", "show", "ordersListTable", "prepare", "orderID");
}

/**
 * Receive form inputs and feeds AJAX function with attibrutes
 * 
 * @param {type} orderID
 * @returns {undefined}
 */
function orderSent(orderID)
{
//    command/URL to send til AJAX function
    var url = "FrontController?command=OrderCommand&commandType=ordersent" +
            "&orderID=" + orderID;
    showContent2(url, "OrderCommand", "ordersListTable", "prepare", "orderID");
}

