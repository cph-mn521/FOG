<%@page import="com.entities.dto.Employee"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Author: Niels -->


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="content">
    <div class="jumbotron text-center">
        <h1 id="headline">
            Opdater brugerinformation
        </h1>
    </div>
    <div id="editProfileForm">
        <div class="container roundedCorner infobox">
            <%
               Employee att = (Employee) session.getAttribute("user");               
            %>
            
            Navn:<br>
            <input type="text" id="name" name="name" value="<%=att.getName()%>"><br>

            Password<br>
            <input type="password" id="password" name="password" value="<%=att.getPassword()%>" ><br>

            Email:<br>
            <input type="email" id="email" name="email" value="<%=att.getEmail()%>"><br>

            Telefon:<br>
            <input type="text" id="phoneNumber" name="phoneNumber" value="<%=att.getPhone_number()%>"><br>

            <table id="editProfileTble" align="center">
                <tr> 
                    <td><button id="editProfileForm" href="#" onclick="editProfile()" class="btn btn-primary">Opdater</button></td>
                </tr>    
            </table>
        </div>
    </div>
</div>
            <!--
<div id="dialog1" title="Basic dialog" style="text-align: center;">
    <label for="Adresse"> Email. </label><br>
    <input id="" type="text" name="Navn" class="password"><br>   
    <br><button id="submitbutton" onclick="login()">Login</button>
    <br><br><a id="changemode" href="#" onclick="checkLogin()">Verificer Ændringer</a>
</div>
            
            
            <!--<button id="topBtn" onclick="topFunction()" title="Gå til top">Top</button> -->
