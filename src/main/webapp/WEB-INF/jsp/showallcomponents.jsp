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
                    <a id="newComponentShowAllComponentPage" onclick="newComponent()" href="#"> <img src="img/new.png" alt="nyt"> Ny komponent</a>
                    </c:if>
                <input type="search" id="searchInput" style="background: url(img/searchicon.png) no-repeat left center;" onkeyup="tableSearch('componentsListTable')" placeholder="S�g p� materiale beskrivelse.." title="S�g p� beskrivelse"/>
                <table id="componentsListTable" class="table table-hover table-condensed table-striped text-center">
                    <tr class="table">
                        <th>Komponent ID</th>
                        <th>Beskrivelse</th>
                        <th>Hj�lpetekst</th>
                        <th>L�ngde</th>
                        <th>Bredde</th>
                        <th>H�jde</th>
                        <th>Stykpris</th>
                    </tr>
                    <c:forEach items="${sessionScope.components}" var="Component"> 
                        <tr class="table-bordered">
                            <td>${Component.componentId}</td>
                            <td>${Component.description}</td>
                            <td>${Component.helpText}</td>
                            <td>${Component.length}</td>
                            <td>${Component.width}</td>
                            <td>${Component.height}</td>
                            <td>${Component.price}</td>
                        </c:forEach>
                </table>
            </div>
        </div>
        <button id="topBtn" onclick="topFunction()" title="G� til top">Top</button>
    </c:when>
    <c:otherwise>
        <div id="errorInfo"><h6>Der er ikke fundet en liste af komponenter. Pr�v at logge ind igen</h6></div>
    </c:otherwise>
</c:choose>
