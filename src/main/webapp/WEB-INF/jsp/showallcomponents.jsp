<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.components != null && sessionScope.rank != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                Materialer
            </h1>
        </div>
        <div id="componentTable">
            <div class="roundedCorner">
                <c:if test="${sessionScope.rank == 'admin' || sessionScope.rank == 'superadmin'}">
                    <a id="newComponentShowAllComponentPage" onclick="newComponent()" href="#"> <img src="img/new.png" alt="nyt"> Nyt materiale</a>
                    </c:if>
                <input type="search" id="searchInput" style="background: url(img/searchicon.png) no-repeat left center;" onkeyup="tableSearch('componentsListTable')" placeholder="Søg på materiale beskrivelse.." title="Søg på beskrivelse"/>
                <table id="componentsListTable" class="table table-hover table-condensed table-striped text-center">
                    <tr class="table">
                        <th>Komponent ID</th>
                        <th>Beskrivelse</th>
                        <th>Hjælpetekst</th>
                        <th>Bredde</th>
                        <th>Højde</th>
                        <th>Stykpris</th>
                    </tr>
                    <c:forEach items="${sessionScope.components}" var="Component"> 
                        <tr class="table-bordered">
                            <td>${Component.componentId}</td>
                            <td>${Component.description}</td>
                            <td>${Component.helpText}</td>
                            <td>${Component.width}</td>
                            <td>${Component.height}</td>
                            <td>${Component.price}</td>
                        </c:forEach>
                </table>
            </div>
        </div>

    </c:when>
    <c:otherwise>
        <div>
            Der er ikke fundet en liste af komponenter
        </div>
    </c:otherwise>
</c:choose>