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
}

function changeOrderForm()
{
//    var name = document.getElementById("name").value;
//    var rank = document.getElementById("rank").value;
//    var email = document.getElementById("email").value;
//    var phone_number = document.getElementById("phone_number").value;

    var url = "FrontController?command=OrderCommand&commandType=changed&name=" + name +
            "&rank=" + rank + "&email=" + email +
            "&phone_number=" + phone_number;
    
    showContent2(url, "OrderCommand", "ordersListTable", "prepare", "orderID");
}

function regretChangeOrderForm()
{
    showContent("OrderCommand", "show", "ordersListTable", "prepare", "orderID");
}



//###########################
//##########Employee#########
//###########################


function newEmployeeForm()
{
    var name = document.getElementById("name").value;
    var rank = document.getElementById("rank").value;
    var email = document.getElementById("email").value;
    var phone_number = document.getElementById("phone_number").value;

    var url = "FrontController?command=EmployeeCommand&commandType=newfinished&" +
            "&name=" + name + "&rank=" + rank +
            "&email=" + email;
    showObject(url);
}

function changeEmployeeForm()
{
    var name = document.getElementById("name").value;
    var rank = document.getElementById("rank").value;
    var email = document.getElementById("email").value;
    var phone_number = document.getElementById("phone_number").value;

    var url = "FrontController?command=EmployeeCommand&commandType=changed&" +
            "&name=" + name + "&rank=" + rank +
            "&email=" + email;
    
    showContent2(url, "EmployeeCommand", "employeesListTable", "prepare", "employeeID");
}

function regretChangeEmployeeForm()
{
    showContent("EmployeeCommand", "show", "employeesListTable", "prepare", "employeeID");
}



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
            "&height=" + height + "&price=" + price;r;
    
    showContent2(url, "ComponentCommand", "componentsListTable", "prepare", "componentID");
}

function regretChangeComponentForm()
{
    showContent("ComponentCommand", "show", "componentsListTable", "prepare", "componentID");
}



//###########################
//##########Customer#########
//###########################


function newCustomerForm()
{
//    var customerAddress = document.getElementById("customerAddress").value;
//    var roofTypeID = document.getElementById("roofTypeID").value;
//    var cartportLength = document.getElementById("cartportLength").value;
//    var cartportWidth = document.getElementById("cartportWidth").value;
//    var cartportHeight = document.getElementById("cartportHeight").value;
//    var shedLength = document.getElementById("shedLength").value;
//    var shedWidth = document.getElementById("shedWidth").value;
//    var shedHeight = document.getElementById("shedHeight").value;

    var url = "FrontController?command=CustomerCommand&commandType=newfinished&customerAddress=" + customerAddress +
            "&roofTypeID=" + roofTypeID + "&cartportLength=" + cartportLength +
            "&cartportWidth=" + cartportWidth + "&cartportHeight=" + cartportHeight +
            "&shedLength=" + shedLength + "&shedWidth=" + shedWidth +
            "&shedHeight=" + shedHeight;
    showObject(url);
}

function changeCustomerForm()
{
//    var name = document.getElementById("name").value;
//    var rank = document.getElementById("rank").value;
//    var email = document.getElementById("email").value;
//    var phone_number = document.getElementById("phone_number").value;

    var url = "FrontController?command=CustomerCommand&commandType=changed&name=" + name +
            "&rank=" + rank + "&email=" + email +
            "&phone_number=" + phone_number;
    
    showContent2(url, "CustomerCommand", "customersListTable", "prepare", "customerID");
}

function regretChangeCustomerForm()
{
    showContent("CustomerCommand", "show", "customersListTable", "prepare", "customerID");
}