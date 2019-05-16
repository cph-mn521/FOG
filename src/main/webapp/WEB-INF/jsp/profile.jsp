<%-- 
    Document   : profile
    Created on : 15-May-2019, 13:06:50
    Author     : nille
--%>

<%@page import="com.entities.dto.Roof"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1>Velkommen til køb af din carport!</h1>
<h3>Wooo!</h3>
<div class="Input">
    <label for="Adresse"> Adresse. </label><br>
    <input id="Adr" type="text" name="Adresse" class="carport" size="65"><br>
    <label for="length"> Længde. </label><br>
    <input id="length" type="tel" name="length" class="carport" size="65"><br>
    <label for="width"> Brede. </label><br>
    <input id="width" type="tel" name="width" class="carport" size="65"><br>
    <label for="heigth"> Højde. </label><br>
    <input id="heigth" type="tel" name="heigth" class="carport" size="65"><br>
  
    <label for="dropdown"> Tag type. </label><br>
    <select id="targetSelect">
        <%  List<Roof> roof = (List<Roof>) request.getAttribute("roofs");
            for (Roof R : roof) {
        %>                    
        <option value="<%=R.getRoofTypeId() %>" ><%=R.getType()%></option>
        <% }%>
    </select>
</div>

<div class="Skur">
    <input id="shedbox" type="checkbox" name="shed" value="Shed" > Skur<br>
    <div id="shedinfo" style="display:none">
    <ul class="list-unstyled components" >
        <li>    
            <label for="ShedL">Længde. </label><br>
            <input id="ShedL" type="text" name="Adresse" class="carport"><br>
        </li>
        <li>
            <label for="ShedW">Brede. </label><br>
            <input id="ShedW" type="text" name="Adresse" class="carport"><br>
        </li>
    </ul>
    </div>
</div>
<div class="orderComment">
    <label for="Adresse">Kommentar til Odren. </label><br>
    <textarea rows = "5" cols = "68" name = "description" placeholder="Beskrivelse." id="changeDescr"></textarea>
</div>
    <div class="EXPLAIONOBOX" id="msgBox" style="display:block">
        <p/>
</div>  
<button class="buybutton btn btn-warning">BUY THIS THING!</button>