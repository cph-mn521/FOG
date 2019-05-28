<%-- 
    Document   : viewMessage
    Created on : 11-05-2019, 21:07:09
    Author     : Martin
--%>

<%@page import="com.entities.dto.Message"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%Message M = (Message)request.getAttribute("msg"); %>

<h3><%=M.getTitle() %></h3>
<h4><%=M.getTimestamp() %></h4>
<p class="line"></p>
<ul class="list-unstyled components">
    <li><h4><%=M.getMsg() %></h4></li>
</ul>

