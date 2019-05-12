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

function buttonEvent(buttonID, urlString)
{
    $('#' + listTableID + ':has(td)').click(function (e)
    {
        var clickedRow = $(e.target).closest('tr');
        showObject(urlString);

        return;

    }); // end mouseover
}

