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
        <%@ include file = "WEB-INF/fragments/body.jspf" %>
        <%@ include file = "WEB-INF/fragments/sidebar.jsp" %>
        <c:choose>
            <c:when test="${sessionScope.user == null}">
                <!--info for user-->
                <div id="firstTextContainer">
                    <div id="firstText">
                        <p style="font-size: 1.2em">Login </p>
                    </div>
                </div>
                <div>
                    <form action="FrontController" method="POST">
                        <input type="hidden" name="command" value="Login" />
                        <button class="btn btn-success">Login</button>
                    </form>
                </div>
            </c:when>
            <c:otherwise>
                <jsp:forward page="WEB-INF/jsp/test.jsp" />
            </c:otherwise>
        </c:choose>
        <%@ include file = "WEB-INF/fragments/footer.jspf" %>
    </body>
</html>
