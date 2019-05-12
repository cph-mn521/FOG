<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>
    Ny ordre
</h1>
<div id="newOrderForm">
    <div class="container">
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="NewOrder" />

            Customer<br /><br />

            Kunde Adresse<br />
            <input type="text" name="customerAddress" value=""><br />

            Tag type<br />
            <input type="text" list="roofType" name="roofTypeID">
            <datalist id="roofType">
                <c:forEach items="${sessionScope.roofs}" var="roof"> 
                    <option value="${roof.roofTypeId}: ${roof.type}, ${roof.color}">
                    </c:forEach>
            </datalist><br />


            roofTypeID<br />
            <input type="text" name="roofTypeID" value=""><br />

            cartportLength<br />
            <input type="text" name="cartportLength" value=""><br />

            cartportWidth<br />
            <input type="text" name="cartportWidth" value=""><br />

            cartportHeight<br />
            <input type="text" name="cartportHeight" value=""><br />

            shedLength<br />
            <input type="text" name="shedLength" value=""><br />

            shedWidth<br />
            <input type="text" name="shedWidth" value=""><br />

            shedHeight<br />
            <input type="text" name="shedHeight" value=""><br />

            <div>
                <button id="doneNewOrder" class="btn btn-warning">Fortsæt</button>
            </div>
        </form>
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="NewOrder" />
            <button id="regretNewOrder" class="btn btn-info">Fortryd</button>
        </form>
    </div>
</div>
