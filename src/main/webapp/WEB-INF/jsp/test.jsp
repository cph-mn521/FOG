<%-- 
    Document   : login
    Created on : 05-04-2019, 10:31:09
    Author     : Martin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%@ include file = "../fragments/header.jspf" %>

        <title>Test page</title>
    </head>

    <body>
        <c:out value ="Her skal der logges ind :)"/>

        <div>
            <p>Du er logget ind</p> 
            <%--<c:set var="message"  value='${sessionScope["numList"]}'/>--%>
            <c:set var="message"  value='${sessionScope.numList}'/>
            <c:if test="${message!= null}">
                <c:out value="${message}"/>            
            </c:if> 
        </div>

    </body>
</html>
