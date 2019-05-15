//###########################
//##########Component#########
//###########################


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

    showObject(url);
}

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
    r;

    showContent2(url, "ComponentCommand", "componentsListTable", "prepare", "componentID");
}

function regretComponentForm()
{
    showContent("ComponentCommand", "show", "componentsListTable", "prepare", "componentID");
}



//###########################
//##########Customer#########
//###########################


function newCustomerForm()
{
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var password = document.getElementById("password").value;
    var phoneNumber = document.getElementById("phoneNumber").value;

    var url = "FrontController?command=CustomerCommand&commandType=newfinished" +
            "&name=" + name + "&email=" + email +
            "&password=" + password + "&cartportHeight=" + cartportHeight +
            "&shedLength=" + shedLength + "&phone_number=" + phone_number;
    showObject(url);
}

function changeCustomerForm()
{
    var name = document.getElementById("name").value;
    var email = document.getElementById("email").value;
    var phoneNumber = document.getElementById("phoneNumber").value;

    var url = "FrontController?command=CustomerCommand&commandType=changed" +
            "&name=" + name + "&email=" + email +
            "&cartportHeight=" + cartportHeight +
            "&shedLength=" + shedLength + "&phoneNumber=" + phoneNumber;

    showContent2(url, "CustomerCommand", "customersListTable", "prepare", "customerID");
}

function regretCustomerForm()
{
    showContent("CustomerCommand", "show", "customersListTable", "prepare", "customerID");
}

//###########################
//##########Employee#########
//###########################


function newEmployeeForm()
{
    var name = document.getElementById("name").value;
    var rank = document.getElementById("rank").value;
    var email = document.getElementById("email").value;
    var phoneNumber = document.getElementById("phoneNumber").value;

    var url = "FrontController?command=EmployeeCommand&commandType=newfinished" +
            "&name=" + name + "&rank=" + rank +
            "&email=" + email + "&phoneNumber=" + phoneNumber;
    showObject(url);
}

function changeEmployeeForm()
{
    var name = document.getElementById("name").value;
    var rank = document.getElementById("rank").value;
    var email = document.getElementById("email").value;
    var phoneNumber = document.getElementById("phoneNumber").value;

    var url = "FrontController?command=EmployeeCommand&commandType=changed" +
            "&name=" + name + "&rank=" + rank +
            "&email=" + email + "&phoneNumber=" + phoneNumber;
    alert(url);
    showContent2(url, "EmployeeCommand", "employeesListTable", "prepare", "employeeID");
}

function regretEmployeeForm()
{
    showContent("EmployeeCommand", "show", "employeesListTable", "prepare", "employeeID");
}


//###########################
//##########Order############
//###########################

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

    var url = "FrontController?command=OrderCommand&commandType=newfinished&customerAddress=" + customerAddress +
            "&roofTypeID=" + roofTypeID + "&cartportLength=" + cartportLength +
            "&cartportWidth=" + cartportWidth + "&cartportHeight=" + cartportHeight +
            "&shedLength=" + shedLength + "&shedWidth=" + shedWidth +
            "&shedHeight=" + shedHeight;
    showObject(url);
    W
}

function changeOrderForm()
{
    var customerAddress = document.getElementById("customerAddress").value;
    var roofTypeID = document.getElementById("roofTypeID").value;
    var cartportLength = document.getElementById("cartportLength").value;
    var cartportWidth = document.getElementById("cartportWidth").value;
    var cartportHeight = document.getElementById("cartportHeight").value;
    var shedLength = document.getElementById("shedLength").value;
    var shedWidth = document.getElementById("shedWidth").value;
    var shedHeight = document.getElementById("shedHeight").value;

    var url = "FrontController?command=OrderCommand&commandType=changed&customerAddress=" + customerAddress +
            "&roofTypeID=" + roofTypeID + "&cartportLength=" + cartportLength +
            "&cartportWidth=" + cartportWidth + "&cartportHeight=" + cartportHeight +
            "&shedLength=" + shedLength + "&shedWidth=" + shedWidth +
            "&shedHeight=" + shedHeight;

    showContent2(url, "OrderCommand", "ordersListTable", "prepare", "orderID");
}

function regretOrderForm()
{
    showContent("OrderCommand", "show", "ordersListTable", "prepare", "orderID");
}

