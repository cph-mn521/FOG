<%@page import="com.entities.dto.Employee"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!-- Author: Niels -->


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div id="content">
    <div class="jumbotron text-center">
        <h1 id="headline">
            Opdater Brugerinformation
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
                    <td><button id="editProfileForm" onclick="editProfile()" class="btn btn-primary">Opdater</button></td>
                </tr>    
            </table>
        </div>
    </div>
</div>
<!--
<div id="diaglog1">
    something here <br>
    something else here
</div>-->
<!--<button id="topBtn" onclick="topFunction()" title="Gå til top">Top</button> -->
