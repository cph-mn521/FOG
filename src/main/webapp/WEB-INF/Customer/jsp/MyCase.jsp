<%-- 
    Document   : MyCase
    Created on : 15-05-2019, 13:15:11
    Author     : Martin
--%>

<%@page import="com.entities.dto.Order"%>
<%@page import="java.util.List"%>
<%@page import="com.entities.dto.Case"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    List<Case> Cases = (List<Case>) request.getAttribute("Cases");
    List<Order> Orders = (List<Order>) request.getAttribute("Orders");
    int i = 1;

%>
 <table class="table table-hover table-condensed table-striped text-center">
<tr>
    <th>Odrer nummer</th>
    <th>Modtaget</th>
    <th>Status</th>
    <th>pris</th>
</tr>
<%    for (Order o : Orders) {
%>  
<tr onclick="toggleSubtable('<%=o.getOrder_id() %>')">               
    <td><%=o.getOrder_id()%> </td>
    <td ><%=o.getOrder_receive_date() %></td>
    <td<%=o.getOrder_status()%> </td>
    <td><%=o.getTotal_price() %></td>

 <tr style="display:none;" id="<%=o.getOrder_id() %>">
    <td>Odrer nummer</td>
    <td>Modtaget</td>
    <td>Status</td>
    <td>pris</td>
<tr style="display:none;" id="<%=o.getOrder_id() %>"><td>ree</td></tr>
 </tr>  

<% i++;
    }
%>
</table>

<div id="links">
    
</div>