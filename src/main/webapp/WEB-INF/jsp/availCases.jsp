<%-- 
    Document   : availCases
    Created on : 10-05-2019, 16:01:37
    Author     : Martin
--%>

<%@page import="com.entities.dto.Message"%>
<%@page import="com.entities.dto.Case"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id=ACList>
    <table class="table table-hover table-condensed table-striped text-center">
        <%
            List<Case> cases = (List<Case>) request.getAttribute("freeCases");
            for (Case C : cases) {%>
        <tr>
            <td > <a href="#" onclick="getCase(this)" id="<%= C.getCaseId()%>"> Case #<%= C.getCaseId()%> 
                    <br> Odrer #<%= C.getOrderId()%>
                    <br><%= C.getTimestamp().toString() %></a></th>
        </tr>
        <%}%>
    </table>

</div>
<div id="selectedAC">

</div>
<div id="MsgList">
    <table class="table table-hover table-condensed table-striped text-left">
        <%
            List<Message> msg = (List<Message>) request.getAttribute("msg");
            for (Message M : msg) {%>
        <tr>
            <td > <a href="#" onclick="getMsg(this)" id="<%= M.getID() %>"><%= M.getTimestamp() %> 
                    <br><%= M.getTitle()%> 
        </tr>
        <%}%>
    </table>

</div>

