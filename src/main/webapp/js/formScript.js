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

function changeEmployeeForm()
{
    var name = document.getElementById("name").value;
    var rank = document.getElementById("rank").value;
    var email = document.getElementById("email").value;
    var phone_number = document.getElementById("phone_number").value;

    var url = "FrontController?command=EmployeeCommand&commandType=changed&name=" + name +
            "&rank=" + rank + "&email=" + email +
            "&phone_number=" + phone_number;
    
    showContent2(url, "EmployeeCommand", "employeesListTable", "prepare", "employeeID");
}

function regretChangeEmployeeForm()
{
    showContent("EmployeeCommand", "show", "employeesListTable", "prepare", "employeeID");
}


