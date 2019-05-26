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

<div id="tabs">
    <ul>
        <%for (Case C : Cases) {%>
        <li><a href="#tabs-<%=i%>"><%=C.getOrderId()%></a></li>
            <%i++;
                } %>
    </ul>
                <%-- <%if(!Cases.get(i-1).getType().equals("salesperson")){ %><% } %> --%>
    <%  i = 1;
    for (Order O : Orders) {%>
    <div id="tabs-<%=i%>">
        <%=O.getOrder_receive_date()%>
        <%=O.getTotal_price()%>
        
        <a>Se Tegninger.</a>
        <a>Se PDF.</a>
        
        <%=O.getOrder_status()%>
    </div>
    <%i++;
    }%>
</div>
