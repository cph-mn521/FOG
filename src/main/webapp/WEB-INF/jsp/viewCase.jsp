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
<h1>Case nr <%=C.getCaseId()%></h1>
<div class="roundedCorner infobox">
    <h4>Placeret d. <%=C.getTimestamp()%></h4>
    <p class="line"></p>
    <ul class="list-unstyled components">
        <li><h4>oprettet af: <%=Owner.getName()%></h4></li>
        <li><h4>email      : <%=Owner.getEmail()%></h4></li>
        <li><h4>Telefon    : <%=Owner.getPhone_number()%></h4></li>
        <li><h4>Case Beskrivelse:</h4><p><%=C.getMsg_owner()%></p></li>
    </ul>
</div>
<h1>Case Noter</h1>
<div class="roundedCorner infobox">
    <div id="ACNotes">
        <ul>
            <li><p><%=C.getMsg_status()%> </p></li>
        </ul>
        <table align="center">
            <tr>
                <td><button id="takeCase" class="btn btn-primary" onclick="buttonPush()">Tildel Case</button></td>
            </tr>
        </table>
    </div>  
</div>  