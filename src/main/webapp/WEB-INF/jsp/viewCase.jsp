<%-- 
    Document   : viewCase
    Created on : 11-05-2019, 21:06:56
    Author     : Martin
--%>

<%@page import="com.entities.dto.User"%>
<%@page import="com.entities.dto.Case"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%Case C = (Case) request.getAttribute("case");
    User Owner = (User) request.getAttribute("user");%>
<h1 class="dif">Case nr <%=C.getCaseId()%></h1>
<div>
    <h4>Placeret d. <%=C.getTimestamp()%></h4>
    <p class="line"></p>
    <ul class="list-unstyled components">
        <li><h4>oprettet af: <%=Owner.getName()%></h4></li>
        <li><h4>email      : <%=Owner.getEmail()%></h4></li>
        <li><h4>Telefon    : <%=Owner.getPhone_number()%></h4></li>
        <p class="line"></p>
    </ul>

</div>
<h2>Case Beskrivelse</h2>
<div>
    <div id="ACNotes">
        <ul>
            <li><p><%=C.getMsg_owner()%></p></li>
        </ul>
    </div>  
</div>
<p class="line"></p>
<h2>Case Noter</h2>
<div>
    <div id="ACNotes">
        <ul>
            <li><p><%=C.getMsg_status()%> </p></li>
        </ul>
        <button id="takeCase" class="btn btn-danger" onclick="buttonPush()">Tildel Case</button>
    </div>  
</div>  