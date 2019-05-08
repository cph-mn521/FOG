<%-- 
    Document   : index
    Created on : 24. april, 2019
    Modified on: 
    Author     : martin bøgh, Martin Wulff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file = "WEB-INF/Fragments/header.jspf" %>
        <link rel="stylesheet" href="css/main.css">
    </head>

    <body>
        <div class="wrapper">
            <%@ include file = "WEB-INF/Fragments/topnav.jspf" %>
            <%@ include file = "WEB-INF/Fragments/sidenav.jspf" %>
            <%@ include file = "WEB-INF/Fragments/content.jspf" %>            
        </div>
        <%@ include file = "WEB-INF/Fragments/footer.jspf" %>
    </body>
</html>
