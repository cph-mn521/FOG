<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.bomMap != null}">
        <h1>
            Stykliste:
        </h1>
        <div id="bomTable">
            <div class="container">
                <p> OrderID:  <span class="orderIDText"><c:out value="${sessionScope.orderID}" /></span> </p>
                <table id="componentListTable" class="table table-hover table-condensed table-striped text-center">
                    <tr class="table">
                        <th>Komponent ID</th>
                        <th>Beskrivelse</th>
                        <th>Hj�lpetekst</th>
                        <th>Bredde</th>
                        <th>H�jde</th>
                        <th>Stykpris</th>
                        <th>Antal</th>  
                    </tr>
                    <c:forEach items="${sessionScope.bomMap}" var="components"> 
                        <tr class="table-bordered">
                            <td>${components.key.componentId}</td>
                            <td>${components.key.description}</td>
                            <td>${components.key.helpText}</td>
                            <td>${components.key.width}</td>
                            <td>${components.key.height}</td>
                            <td>${components.key.price}</td>
                            <td>${components.value}</td>
                    </c:forEach>
                </table>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            Der er ikke fundet en ordre
        </div>
    </c:otherwise>
</c:choose>