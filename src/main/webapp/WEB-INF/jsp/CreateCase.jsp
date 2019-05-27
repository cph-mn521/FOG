<%-- 
    Document   : CreateCase
    Created on : 22-05-2019, 14:09:26
    Author     : Martin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<div id="Customercontent">
    <label for="Adresse">Beskriv problemet: </label><br>
    <textarea id="commentOwner" rows = "5" cols = "68" name = "description" placeholder="Beskrivelse." id="changeDescr"></textarea>
    <br><button class="btn btn-danger" onclick="createEmpCase()"> Indsend</button>
</div>