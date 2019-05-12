function tableEvent(tagID, urlString, newID)
{
//      create mouseover table effect
    $(tagID + ':has(td)').mouseover(function (e)
    {
        $(this).css('cursor', 'crosshair');
    }); // end mouseover


//        create table-click links depending on user role
    $(tagID + ':has(td)').click(function (e)
    {
//          clickedRow is the row you've clicked on
        var clickedRow = $(e.target).closest('tr');

//          value is the value of the first cell in the row you've clicked on
        var value = clickedRow.find('td:eq(0)').text();
        
//        var url = 'FrontController?command=ShowBOM&orderID=' + value;
        var url = urlString + value;

//        change shown page 
        showOrder(url);

//        window.location = url;
        return;

    }); // end mouseover

}

function tableEventComp(tagID, urlString, newID)
{
    $(tagID + ':has(td)').mouseover(function (e)
    {
        $(this).css('cursor', 'crosshair');
    }); // end mouseover
    $(tagID + ':has(td)').click(function (e)
    {
        var clickedRow = $(e.target).closest('tr');
        var value = clickedRow.find('td:eq(0)').text();
        var url = urlString + value;
        showComponent(url);
        return;
    }); // end mouseover
}

function tableEventOrder(tagID, urlString, newID)
{
    $(tagID + ':has(td)').mouseover(function (e)
    {
        $(this).css('cursor', 'crosshair');
    }); // end mouseover
    $(tagID + ':has(td)').click(function (e)
    {
        var clickedRow = $(e.target).closest('tr');
        var value = clickedRow.find('td:eq(0)').text();
        var url = urlString + value;
        showOrder(url);
        return;
    }); // end mouseover
}

function tableEventEmployee(tagID, urlString, newID)
{
    $(tagID + ':has(td)').mouseover(function (e)
    {
        $(this).css('cursor', 'crosshair');
    }); // end mouseover
    $(tagID + ':has(td)').click(function (e)
    {
        var clickedRow = $(e.target).closest('tr');
        var value = clickedRow.find('td:eq(0)').text();
        var url = urlString + value;
        showEmployee(url);
        return;
    }); // end mouseover
}

function tableEventCustomer(tagID, urlString, newID)
{
    $(tagID + ':has(td)').mouseover(function (e)
    {
        $(this).css('cursor', 'crosshair');
    }); // end mouseover
    $(tagID + ':has(td)').click(function (e)
    {
        var clickedRow = $(e.target).closest('tr');
        var value = clickedRow.find('td:eq(0)').text();
        var url = urlString + value;
        showCustomer(url);
        return;
    }); // end mouseover
}