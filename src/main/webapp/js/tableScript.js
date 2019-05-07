function tableEvent(tagID, urlString)
{
//      create mouseover table effect
    $('#'+tagID+':has(td)').mouseover(function (e)
    {
        $(this).css('cursor', 'crosshair');
    }); // end mouseover


//        create table-click links depending on user role
    $('#'+tagID+':has(td)').click(function (e)
    {
//          clickedRow is the row you've clicked on
        var clickedRow = $(e.target).closest('tr');

//          value is the value of the fist cell in the row you've clicked on
        var value = clickedRow.find('td:eq(0)').text();
//        var url = 'FrontController?command=ChangeComponents&componentID=' + value;
        var url = urlString + value;
        window.location = url;
        return;

    }); // end mouseover

}

