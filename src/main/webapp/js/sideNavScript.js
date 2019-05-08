function toggleContent(tagID)
{
    $('#content #contentContainer .showing').addClass('hiding');
    $('#content #contentContainer .showing').removeClass('showing');
    $('#content #contentContainer ' + tagID).removeClass('hiding');
    $('#content #contentContainer ' + tagID).addClass('showing');
}
