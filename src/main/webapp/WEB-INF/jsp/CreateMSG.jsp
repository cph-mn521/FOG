<%-- 
    Document   : CreateMSG
    Created on : 22-05-2019, 14:15:09
    Author     : Martin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<div id="Customercontent">
    <label for="Adresse">Tittel: </label><br>
    <textarea id="Titel" rows = "1" cols = "68" name = "description" placeholder="Beskrivelse." id="changeDescr"></textarea>
    <br><label for="Adresse">Modatager </label><br>
        <select id="targetSelect">
        <option value="storeworker" >Lager</option>
        <option value="salesperson" >Salg</option>
        <option value="admin" >Admin</option>
        <option value="superadmin" >Super-Admin</option>
        <option value="all">Alle</option>
    </select><br>
    <label for="msg">Besked: </label><br>
    <textarea id="msg" rows = "10" cols = "68" name = "description" placeholder="Beskrivelse." id="changeDescr"></textarea>
    <br><button class="btn btn-danger" onclick="createMsg()"> Send</button>
</div>