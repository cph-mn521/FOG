<%-- 
    Document   : MyCase
    Created on : 15-05-2019, 13:15:11
    Author     : Martin
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.entities.dto.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.entities.dto.Case"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Case> Cases = (List<Case>) request.getAttribute("Cases");
    List<Order> Orders = (List<Order>) request.getAttribute("Orders");
    int i = 0;

%>
<table class="table table-hover  table-bordered table-striped text-center">
    <tr>
        <th>Odrer nummer</th>
        <th>Modtaget</th>
        <th>Status</th>
        <th>Pris</th>
    </tr>
    <%    for (Order o : Orders) {
    %>  
    <tr onclick="toggleSubtable('<%=o.getOrder_id()%>')">               
        <td><%=o.getOrder_id()%> </td>
        <td ><%=o.getOrder_receive_date()%></td>
        <td><%=(Cases.get(i).getType().equals("salesperson"))?"behandles af en sælger":o.getOrder_status()%> </td>
        <td><%=o.getTotal_price()%></td>
    <%if(!Cases.get(i).getType().equals("salesperson")){%>
    <tr style="display:none;" id="<%=o.getOrder_id()%>">
        <td onclick="showSVG('<%=o.getOrder_id()%>')" colspan="2"> Se tegninger</td>
    <a href="pdf/<%="FOGCarportstykliste_"+o.getOrder_id()+o.getOrder_receive_date().toString() %>" download>
        <td colspan="2" ><b>Hent pdf</b> </td>
    </a>
    <% } %>
</tr>  

<% i++;
    }
%>
</table>

<div id="links">

</div>