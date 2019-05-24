<%-- 
    Document   : CreateMSG
    Created on : 22-05-2019, 14:15:09
    Author     : Martin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<div id="Customercontent">
    <label for="Adresse">Tittel: </label><br>
    <textarea id="commentOwner" rows = "1" cols = "68" name = "description" placeholder="Beskrivelse." id="changeDescr"></textarea>
    <br><label for="Adresse">Modatager </label><br>
        <select id="targetSelect">
        <option value="storeworker" >Lager</option>
        <option value="salesperson" >Salg</option>
        <option value="admin" >Admin</option>
        <option value="superadmin" >Den helt store kanon</option>
        <option>Alle</option>
    </select><br>
    <label for="Adresse">Besked: </label><br>

    <textarea id="commentOwner" rows = "10" cols = "68" name = "description" placeholder="Beskrivelse." id="changeDescr"></textarea>
    <br><button class="btn btn-danger"> Send</button>
</div>