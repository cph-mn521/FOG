<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>
    Ændre ordre
</h1>
<div id="newOrderForm">
    <div class="container">
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="NewOrder" />

            Customer<br /><br />

            Kunde Adresse<br />
            <input type="text" name="customerAddress" value="${sessionScope.order.customer.customer_address}"><br />

            Tag type<br />
            <input type="text" list="roofType" name="roofTypeID">
            <datalist id="roofType">
                <c:forEach items="${sessionScope.roofs}" var="roof"> 
                    <option value="${roof.roofTypeId}: ${roof.type}, ${roof.color}">
                    </c:forEach>
            </datalist><br />

            roofTypeID<br />
            <input type="text" name="roofTypeID" value="${sessionScope.order}"><br />

            cartportLength<br />
            <input type="text" name="cartportLength" value="${sessionScope.order}"><br />

            cartportWidth<br />
            <input type="text" name="cartportWidth" value="${sessionScope.order}"><br />

            cartportHeight<br />
            <input type="text" name="cartportHeight" value="${sessionScope.order}"><br />

            shedLength<br />
            <input type="text" name="shedLength" value="${sessionScope.order}"><br />

            shedWidth<br />
            <input type="text" name="shedWidth" value="${sessionScope.order}"><br />

            shedHeight<br />
            <input type="text" name="shedHeight" value="${sessionScope.order}"><br />

            <div>
                <button id="doneNewOrder" class="btn btn-warning">Fortsæt</button>
            </div>
        </form>
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="NewOrder" />
            <button id="regretNewOrder" class="btn btn-info">Fortryd</button>
        </form>
    </div>
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
