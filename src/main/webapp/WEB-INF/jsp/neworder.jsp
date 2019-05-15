<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>
    Ny ordre
</h1>

<div id="newOrderForm">
    <div class="container">
        <!--<form action="FrontController" method="POST">-->
            <!--<input type="hidden" name="command" value="OrderCommand" />-->
            <!--<input type="hidden" name="commandType" value="newfinished" />-->
            
            Kundeadresse<br />
            <input type="text" id="customerAddress" name="customerAddress">

            Tagtype<br />
            <input type="text" list="roofType" name="roofTypeID">
            <datalist id="roofType">
                <c:forEach items="${sessionScope.roofs}" var="roof"> 
                    <option value="${roof.roofTypeId}: ${roof.type}, ${roof.color}">
                    </c:forEach>
            </datalist><br />


            Tagtype ID<br />
            <input type="text" id="roofTypeID" name="roofTypeID" value=""><br />

            Carport længde<br />
            <input type="text" id="cartportLength" name="cartportLength" value=""><br />

            Carport bredde<br />
            <input type="text" id="cartportWidth" name="cartportWidth" value=""><br />

            Carport højde<br />
            <input type="text" id="cartportHeight" name="cartportHeight" value=""><br />

            Skur længde<br />
            <input type="text" id="shedLength" name="shedLength" value=""><br />

            Skur bredde<br />
            <input type="text" id="shedWidth" name="shedWidth" value=""><br />

            Skur højde<br />
            <input type="text" id="shedHeight" name="shedHeight" value=""><br />

            <div>
                <button id="doneNewOrder" onclick="newOrderForm()" class="btn btn-warning">Fortsæt</button>
            </div>
        <!--</form>-->
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="OrderCommand" />
            <input type="hidden" name="commandType" value="newfinished" />
            <button id="regretNewOrder" class="btn btn-info">Fortryd</button>
        </form>
    </div>
</div>
