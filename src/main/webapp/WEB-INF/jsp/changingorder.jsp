<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.customer != null && sessionScope.carport != null}">
    <h1>
        Ændre ordre
    </h1>
    <div id="changeOrderForm">
        <div class="container">
            Kunde Adresse<br />
            <input type="text" name="customerAddress" value="${sessionScope.order.customer_address}"><br />

            Tag type<br />
            <input type="text" list="roofType" name="roofTypeID">
            <datalist id="roofType">
                <c:forEach items="${sessionScope.roofs}" var="tag"> 
                    <option value="${tag.roofTypeId}: ${tag.type}, ${tag.color}">
                    </c:forEach>
            </datalist><br />

            roofTypeID<br />
            <input type="text" name="roofTypeID" value="${sessionScope.carport.roofTypeId}"><br />

            cartportLength<br />
            <input type="text" name="cartportLength" value="${sessionScope.carport.length}"><br />

            cartportWidth<br />
            <input type="text" name="cartportWidth" value="${sessionScope.carport.width}"><br />

            cartportHeight<br />
            <input type="text" name="cartportHeight" value="${sessionScope.carport.height}"><br />

            shedLength<br />
            <input type="text" name="shedLength" value="${sessionScope.carport.shedLength}"><br />

            shedWidth<br />
            <input type="text" name="shedWidth" value="${sessionScope.carport.shedWidth}"><br />

            shedHeight<br />
            <input type="text" name="shedHeight" value="${sessionScope.carport.shedHeight}"><br />
            <div>
                <button id="doneChangeOrder" onclick="changeOrderForm()" class="btn btn-warning">Fortsæt</button>
            </div>
            <div>
                <button id="regretChangeOrder" onclick="regretChangeOrder()" class="btn btn-info">Fortryd</button>
            </div>
            <div>
                <button id="showDrawing" onclick="showDrawing()" class="btn btn-info">Se tegning</button>
            </div>
            <div>
                <button id="downloadPDF" onclick="downloadPDF()" class="btn btn-info">Download stykliste (virker ikke endnu)</button>
            </div>
        </div>
    </c:if>
    <c:if test="${sessionScope.bomMap != null}">
        <h1>
            Stykliste:
        </h1>
        <div id="componentsTable">
            <div class="container">
                <p> OrderID:  <span class="orderIDText"><c:out value="${sessionScope.orderID}" /></span> </p>
                <table id="componentListTable" class="table table-hover table-condensed table-striped text-center">
                    <tr class="table">
                        <th>Komponent ID</th>
                        <th>Beskrivelse</th>
                        <th>Hjælpetekst</th>
                        <th>Bredde</th>
                        <th>Højde</th>
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
    </c:if>    
</div>
