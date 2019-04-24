<%-- 
    Document   : index
    Created on : 24. april, 2019
    Modified on: 
    Author     : martin bøgh
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@ include file = "WEB-INF/fragments/header.jspf" %>
    </head>

    <body>
        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <!--info for user-->
                <div id="firstTextContainer">
                    <div id="firstText">
                        <p style="font-size: 1.2em">Login </p>
                    </div>
                </div>
            </c:when>
            <c:otherwise>
                <jsp:forward page="WEB-INF/jsp/seewishpage.jsp" />
            </c:otherwise>
        </c:choose>
    </body>
</html>
