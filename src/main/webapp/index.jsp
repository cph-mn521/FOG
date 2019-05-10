<%-- 
    Document   : index
    Created on : 24. april, 2019
    Modified on: 
    Author     : martin bøgh, Martin Wulff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head profile="http://www.w3.org/2005/10/profile">
        <link rel="icon" 
              type="image/jpg" 
              href="img/fog.jpg">
        <%@ include file = "WEB-INF/fragments/header.jspf" %>
    </head>

    <body>
        <div class="wrapper">
            <%@ include file = "WEB-INF/fragments/topnav.jspf" %>
            <%@ include file = "WEB-INF/fragments/sidenav_1.jspf" %>

            <% if(request.getSession().getAttribute("rank") != null){
                pageContext.include("WEB-INF/fragments/sideMenus" + (String) request.getAttribute("rank") + ".jspf"); 
            }
            else{%>
            <!--<nav id ="sidebar"></nav>-->
            <% } %>
            
        <%@ include file = "WEB-INF/fragments/content.jspf" %>            
        </div>
    <%@ include file = "WEB-INF/fragments/footer.jspf" %>
    </body>

</html>
