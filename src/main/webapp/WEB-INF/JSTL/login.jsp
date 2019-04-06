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
        <%@ include file = "/WEB-INF/Fragments/header.jspf" %>
        
        <title>JSP Page</title>
    </head>
    <body>
    <c:out value ="Her skal der logges ind :)"/>
    
    <div>
        <form action="control?origin=login" method="post">
            Enter username : <input type="text" name="email" placeholder="Email" required> <BR>
            Enter password : <input type="password" name="password" placeholder="Password" required> <BR>
            <input type="submit" value="Login"/>
        </form>
        <c:set var="message"  value='${sessionScope["numList"]}'/>
        <c:if test="${message!= null}">
            <c:out value="${message}"/>            
        </c:if> 
    </div>
    
    </body>
</html>
