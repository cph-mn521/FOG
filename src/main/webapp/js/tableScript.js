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

//          value is the value of the fist cell in the row you've clicked on
        var value = clickedRow.find('td:eq(0)').text();
//        var url = 'FrontController?command=ShowBOM&orderID=' + value;
        var url = urlString + value;

//        change shown page 
        showOrder(url);

//        send parameter value to relevant command

//        window.location = url;
        $("#orderListTable:has(td)").mouseover(function (e)
        {
            $(this).css('cursor', 'crosshair');
        }); // end mouseover
        return;

    }); // end mouseover

}

