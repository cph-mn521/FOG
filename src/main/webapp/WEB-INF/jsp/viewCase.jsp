<%-- 
    Document   : viewCase
    Created on : 11-05-2019, 21:06:56
    Author     : Martin
--%>

<%@page import="com.entities.dto.User"%>
<%@page import="com.entities.dto.Case"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%Case C =  (Case)request.getAttribute("case"); 
  User Owner = (User)request.getAttribute("user");  %>
<h3>Case nr <%=C.getCaseId()%></h3>
<h4>Placeret d. <%=C.getTimestamp() %></h4>
<p class="line"></p>
<ul class="list-unstyled components">
    <li><h4>Type: <%=C.getType() %></h4></li>
    <li><h4>Kunde: <%=Owner.getName() %></h4></li>
    <li><h4>email: <%=Owner.getEmail() %></h4></li>
    <li><h4>TLF  : <%=Owner.getPhone_number() %></h4></li>
    <li><h4>Case Beskrivelse:</h4><p><%=C.getMsg_owner() %></p></li>
</ul>
<h3>Case Noter</h3>
<div id=ACNotes>
    <ul>
        <li><p><%=C.getMsg_status() %> </p></li>
        <button id="takeCase" onclick="openCase(<%=C.getCaseId()  %>)">Tildel Case</button>
    </ul>

</div>