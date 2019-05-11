<%-- 
    Document   : availCases
    Created on : 10-05-2019, 16:01:37
    Author     : Martin
--%>

<%@page import="com.entities.dto.Case"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id=ACList>
    <table class="table table-hover table-condensed table-striped text-center">
        <%
            List<Case> cases = (List<Case>) request.getAttribute("freeCases");
            for (Case C : cases) {%>
        <tr>
            <td > <a href="#" onclick="getCase(event)" id="<%= C.getCaseId()%>"> Case #<%= C.getCaseId()%> 
                    <br> Odrer #<%= C.getOrderId()%>
                    <br><%= C.getTimestamp().toString() %></a></th>
        </tr>
        <%}%>
    </table>

</div>
<div id="selectedAC">
    <h3>Case nr 4121</h3>
    <h4>Placeret d. 4/2</h4>
    <p class="line"></p>
    <ul class="list-unstyled components">
        <li><h4>Type: Carport</h4></li>
        <li><h4>Kunde: Rigeligt</h4></li>
        <li><h4>Case Beskrivelse:</h4><p>Jeg har lige fået en ny bil. og det er herre fedt, derfor vil jeg også gerne ha en carport til</p></li>
    </ul>
    <h3>Case Noter</h3>
    <div id=ACNotes>
        <ul>
            <li><p>Kunden er meget uvidende omkring bygning af karporte. Anbefaler at vi kontakter Håndværkere. Lagt fri pga ferie.</p><p>26/7</p></li>
            <li><p>Der skete noget vildt n stuff</p></li>
            <button id="takeCase">Tildel Case</button>
        </ul>
        
    </div>
</div>


