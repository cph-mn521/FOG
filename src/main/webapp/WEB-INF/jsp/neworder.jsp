<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.roofs != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                Ny ordre
            </h1>
        </div>
        <button onclick="topFunction()" id="topBtn" title="Gå til top">Top</button>
        <div id="newOrderForm">
            <div class="container roundedCorner infobox">
                Kundeadresse<br/>
                <input type="text" id="customerAddress" name="customerAddress"><br/>

                Tagtype<br/>
                <input type="text" list="roofType" id="roofTypeID" name="roofTypeID">
                <datalist id="roofType">
                    <c:forEach items="${sessionScope.roofs}" var="roof"> 
                        <option value="${roof.roofTypeId}: ${roof.type}, ${roof.color}">
                        </c:forEach>
                </datalist><br/>


                <!--Tagtype ID<br/>-->
                <!--<input type="text" id="roofTypeID" name="roofTypeID" value=""><br/>-->

                Carport længde (2400-7800 mm)<br/>
                <input type="text" id="cartportLength" name="cartportLength" value="7800"><br/>

                Carport bredde (2400-7500 mm)<br/>
                <input type="text" id="cartportWidth" name="cartportWidth" value="7500"><br/>

                Carport højde<br/>
                <input type="text" id="cartportHeight" name="cartportHeight" value="4000"><br/>

                Skur længde<br/>
                <input type="text" id="shedLength" name="shedLength" value="0"><br/>

                Skur bredde<br/>
                <input type="text" id="shedWidth" name="shedWidth" value="0"><br/>

                Skur højde<br/>
                <input type="text" id="shedHeight" name="shedHeight" value="0"><br/>
                <table id="orderButtonTable" align="center">
                    <tr> 
                        <td><button id="newOrderForm" onclick="newOrderForm()" class="btn btn-primary">Skab ny</button></td>
                        <td><button id="regretOrderForm" onclick="regretOrderForm()" class="btn btn-danger">Fortryd</button></td>
                    </tr>
                </table>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div id="errorInfo"><h6>Der er ikke fundet en liste af tag typer. Prøv at logge ind igen</h6></div>
    </c:otherwise>
</c:choose>
