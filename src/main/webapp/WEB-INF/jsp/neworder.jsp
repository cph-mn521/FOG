<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>
    Ny ordre
</h1>

<div id="newOrderForm">
    <div class="container">

        Kundeadresse<br />
        <input type="text" id="customerAddress" name="customerAddress">

        Tagtype<br />
        <input type="text" list="roofType" id="roofTypeID" name="roofTypeID">
        <datalist id="roofType">
            <c:forEach items="${sessionScope.roofs}" var="roof"> 
                <option value="${roof.roofTypeId}: ${roof.type}, ${roof.color}">
                </c:forEach>
        </datalist><br />


        <!--Tagtype ID<br />-->
        <!--<input type="text" id="roofTypeID" name="roofTypeID" value=""><br />-->

        Carport længde (2400-7800 mm)<br />
        <input type="text" id="cartportLength" name="cartportLength" value="7800"><br />

        Carport bredde (2400-7500 mm)<br />
        <input type="text" id="cartportWidth" name="cartportWidth" value="7500"><br />

        Carport højde<br />
        <input type="text" id="cartportHeight" name="cartportHeight" value="4000"><br />

        Skur længde<br />
        <input type="text" id="shedLength" name="shedLength" value="0"><br />

        Skur bredde<br />
        <input type="text" id="shedWidth" name="shedWidth" value="0"><br />

        Skur højde<br />
        <input type="text" id="shedHeight" name="shedHeight" value="0"><br />
        <div class="btn-group" role="group" aria-label="button group new order">
            <div>
                <button id="newOrderForm" onclick="newOrderForm()" class="btn btn-primary">Fortsæt</button>
            </div>
            <div>
                <button id="regretOrderForm" onclick="regretOrderForm()" class="btn btn-danger">Fortryd</button>
            </div>
        </div>
    </div>
</div>
