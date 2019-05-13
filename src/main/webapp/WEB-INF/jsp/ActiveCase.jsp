<%-- 
    Document   : ActiveCase
    Created on : 13-05-2019, 12:46:26
    Author     : Martin
--%>

<%@page import="com.entities.dto.Case"%>
<%@page import="com.entities.dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<div id="LeftSide">
<%Case C =  (Case)request.getAttribute("case"); 
  User Owner = (User)request.getAttribute("user");  %>
<h3>Case nr <%=C.getCaseId()%></h3>
<h4>Placeret d. <%=C.getTimestamp() %></h4>
<p class="line"></p>
<ul class="list-unstyled components">
    <li><h4 id="OWNERNAME">oprettet af: <%=Owner.getName() %></h4></li>
    <li><h4 id="OWNEREMAIL">email      : <%=Owner.getEmail() %></h4></li>
    <li><h4 id="OWNERPHONE">Telefon    : <%=Owner.getPhone_number() %></h4></li>
    <li><h4 >Case Beskrivelse:</h4><p><%=C.getMsg_owner() %></p></li>
</ul>
<h3>Case Noter</h3>
<div id=ACNotes>
    <ul>
        <li><p id="OWNERMSG"><%=C.getMsg_status() %> </p></li>
    </ul>

</div>
</div>

    <div id="RightSide">
        <ul class="list-unstyled components">
            <li>
                <a href="#KundeInfo" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Kunde info</a>
                <ul class="collapse list-unstyled" id="KundeInfo">
                    <li>
                        <label for="Navn">Navn</label><br>
                        <input type="text" name="" id="CName" size="75">
                    </li>
                    <li>
                        <label for="Email">Email</label><br>
                        <input type="text" name="" id="CEmail" size="75">
                    </li>
                    <li>
                        <label for="Telefon"> Telefon </label><br>
                        <input type="Telefon" name="" id="CTelefon" size="75">
                    </li>
                    <li><button type="" style="float:right">Opdater</button><p></p></li>
                </ul>

            </li>

        </ul>
        <ul class="list-unstyled components">
            <li>
                <a href="#CaseInfo" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Case info</a>
                <ul class="collapse list-unstyled" id="CaseInfo">
                    <li>
                        <label for="description">Rediger Beskrivelse</label><br>
                        <textarea rows = "5" cols = "60" name = "description">Enter details here...</textarea>

                    </li>
                    <li>
                        <label for="description">Tilføj til Note</label><br>
                        <textarea rows = "5" cols = "60" name = "description">Enter details here...</textarea>
                    </li>
                    <li>
                        <a href="#EditNote" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Rediger Note</a>
                        <ul class="collapse list-unstyled" id="EditNote">
                            <li>
                                <label for="description">Note:</label><br>
                                <textarea rows = "5" cols = "60" name = "description">Enter details here...</textarea>
                            </li>
                        </ul>
                    </li>
                    <li><button type="" style="float:right">Opdater</button><p></p> </li>
                </ul>
            </li>
        </ul>
        <ul class="list-unstyled components">
            <li>
                <a href="#CaseEnd" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Sags Afslutning</a>
                <ul class="collapse list-unstyled" id="CaseEnd">
                    <li>
                        <a href="" title="">Frigiv Sag</a>

                    </li>
                    <li>
                        <a href="" title="">Luk Sag</a>
                    </li>

                    <li>
                        <a href="#SendTo" data-toggle="collapse" aria-expanded="false" class="dropdown-toggle">Sags Afslutning</a>
                        <ul class="collapse list-unstyled" id="Sen  dTo">
                            <li>
                                <a href="" title="">Admin</a>                            
                            </li>
                            <li>
                                <a href="" title="">Super Admin</a>

                            </li>
                        </ul>
                    </li>
                    <li><button type="" style="float:right">Opdater</button> <p></p></li>
                </ul>
            </li>
        </ul>




    </div>