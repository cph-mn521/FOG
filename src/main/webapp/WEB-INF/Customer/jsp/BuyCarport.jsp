<%-- 
    Document   : BuyCarport
    Created on : 15-05-2019, 13:12:06
    Author     : Martin
--%>

<%@page import="com.entities.dto.Roof"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<h1>Velkommen til køb af din carport!</h1>
<h3>Indtast din carport og opret en odrer hos danmarks bedste byggemarkede</h3>
<div class="Input">
    <label for="Adresse"> Adresse. </label><br>
    <input id="Adr" type="text" name="Adresse" class="carport" size="65"><br>
    <label for="length"> Længde. </label><br>
    <input id="length" type="tel" name="length" class="carport" size="65" title="Carportens længde skal være mellem 2400 og 8800mm"><br>
    <label for="width"> Brede. </label><br>
    <input id="width" type="tel" name="width" class="carport" size="65" title="Carporten brede skal være melle 2400 og 7500mm"><br>
    <label for="heigth"> Højde. </label><br>
    <input id="heigth" type="tel" name="heigth" class="carport" size="65" title="Carportens højde skal være melle 2100 og 5100mm" ><br>
    <label for="dropdown"> Tag type. </label><br>
    <select id="targetSelect">
        <%  List<Roof> roof = (List<Roof>) request.getAttribute("roofs");
            for (Roof R : roof) {
        %>                    
        <option value="<%=R.getRoofTypeId()%>" title="taget har en hældning på:<%=R.getSlant()%> grader" ><%=R.getType()%></option>
        <% }%>
    </select>
</div>

<div class="Skur">
    <input id="shedbox" type="checkbox" name="shed" value="Shed" > Skur<br>
    <div id="shedinfo" style="display:none">
        <ul class="list-unstyled components" >
            <li>    
                <label for="ShedL">Længde. </label><br>
                <input id="ShedL" type="text" name="Adresse" class="carport" title="Skurets længde skal være mere end 1200mm, og må ikke overskride carportens længde."><br>
            </li>
            <li>
                <label for="ShedW">Brede. </label><br>
                <input id="ShedW" type="text" name="Adresse" class="carport" title="Skurets brede må skal være længere end 1200mm, og må ikke overskride carportens brede."><br>
            </li>
        </ul>
    </div>
</div>
<div class="orderComment">
    <label for="Adresse">Kommentar til Ordren. </label><br>
    <textarea id="commentOwner" rows = "5" cols = "68" name = "description" placeholder="Beskrivelse." id="changeDescr" title="kort beskrivelse til vores medarbajdere, Skriv gerne spørgsmål og en kort beskrivelse af dit projekt"></textarea>
</div>

<div class="EXPLAIONOBOX ui-widget"  id="msgBox">    
    <button id="buybutton" class="btn btn-warning" href="#login-box">Bestil Carport</button>
    <a href="#login-box" class="login-window">
</div>

<div id="dialog1" title="Basic dialog" style="text-align: center;">
    <label for="Adresse"> Email. </label><br>
    <input id="1l" type="text" name="Navn" class="carport" size="53" ><br>
    <label id="change" for="length"> Password. </label><br>    
    <input id="2l" type="tel" name="length" class="carport" size="53"><br>    
    <br><button id="submitbutton" onclick="login()">Submit</button>
    <br><br><a id="changemode" href="#" onclick="showRegisterBox()">Ny bruger.</a>
</div>
    
<div id="dialog2" title="Basic dialog" style="text-align: center;">
    <label for="Adresse"> Email. </label><br>
    <input id="1" type="text" name="Navn" class="carport" size="53" ><br>
    <label id="change" for="length"> Navn. </label><br>
    <input id="2" type="tel" name="length" class="carport" size="53"><br>    
    <label for="width" > Telefon </label><br>
    <input id="3" type="tel" name="width" class="carport" size="53"><br>
    <label for="heigth"> Kodeord. </label><br>
    <input id="4" type="password" name="heigth" class="carport" size="53" title="Indtast password her." ><br>
    <label for="heigth" > Gentag Kodeord. </label><br>
    <input id="5" type="password" name="heigth" class="carport" size="53" title="Gentag password her" ><br>
    <br><button id="submitbutton" onclick="register()">Submit</button>
    <br><br><a id="changemode" href="#" onclick="showLoginBox()">Logind.</a>
</div>

<%--
    <div id="dialog" title="Basic dialog">
<label for="Adresse"> Navn. </label><br>
<input id="Adresse" type="text" name="Navn" class="carport" size="53" ><br>
<label for="length"> Email. </label><br>
<input id="2" type="tel" name="length" class="carport" size="53"><br>
<label for="width"> Telefon </label><br>
<input id="3" type="tel" name="width" class="carport" size="53"><br>
<label for="heigth"> Kodeord. </label><br>
<input id="4" type="password" name="heigth" class="carport" size="53" title="Indtast password her."><br>
<label for="heigth"> Gentag Kodeord. </label><br>
<input id="5" type="password" name="heigth" class="carport" size="53" title="Gentag password her"><br>
<br><button style="left: 250px;">Submit</button>
--%>