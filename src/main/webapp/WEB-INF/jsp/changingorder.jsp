<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.customer != null 
                    && sessionScope.carport != null
                    && sessionScope.roof != null && sessionScope.bomMap != null
                    && sessionScope.order != null && sessionScope.rank != null}">
            <div class="jumbotron text-center">
                <h1 id="headline">
                    �ndre ordre
                </h1>
            </div>
            <!--<div id="changeOrderForm">-->
            <div class="container roundedCorner infobox">
                <table id="orderInfoTable">
                    <tr>
                        <td>Ordre ID</td>
                        <td>${sessionScope.order.order_id}</td> 
                    </tr>
                    <tr>
                        <td>Kunde Adresse </td>
                        <td>${sessionScope.order.customer_address}</td> 
                    </tr>
                    <tr>
                        <td>Tag type </td>
                        <td>${roof.roofTypeId}: ${roof.type}, ${roof.color}</td> 
                    </tr>
                    <tr>
                        <td>Cartport l�ngde (mm) </td>
                        <td>${sessionScope.carport.length}</td> 
                    </tr>
                    <tr>
                        <td>Cartport bredde (mm) </td>
                        <td>${sessionScope.carport.width}</td> 
                    </tr>
                    <tr>
                        <td>Cartport h�jde (mm) </td>
                        <td>${sessionScope.carport.height}</td> 
                    </tr>
                    <tr>
                        <td>Ordre modtaget (�-M-D)</td>
                        <td>${sessionScope.order.order_receive_date}</td> 
                    </tr>
                    <tr>
                        <td>Ordre afsendt (�-M-D)</td>
                        <td>${sessionScope.order.order_send_date}</td> 
                    </tr>
                    <tr>
                        <td>Ordre status </td>
                        <td>${sessionScope.order.order_status}</td> 
                    </tr>
                    <tr>
                        <td>Kunde adresse </td>
                        <td>${sessionScope.order.customer_address}</td> 
                    </tr>
                    <c:if test="${sessionScope.rank == 'admin' || sessionScope.rank == 'superadmin' || sessionScope.rank == 'salesperson'}">
                        <tr>
                            <td>Total pris (kr) </td>
                            <td><input type="text" id="totalPrice" name="totalPrice" value="${sessionScope.order.total_price}" size="4"></td>
                        </tr>
                    </c:if>
                </table>
            </div>

            <table id="orderButtonTable" align="center">
                <tr>
                    <c:if test="${sessionScope.rank == 'admin' || sessionScope.rank == 'superadmin' || sessionScope.rank == 'salesperson'}">
                        <td><button id="doneChangeOrder" onclick="changeOrderForm()" class="btn btn-primary">Forts�t</button></td>
                        <td><button id="removeOrderForm" onclick="removeOrderForm(${sessionScope.order.order_id})" class="btn btn-warning">Fjern</button></td>
                    </c:if>
                    <td><button id="regretOrderForm" onclick="regretOrderForm()" class="btn btn-danger">Fortryd</button></td>
                    <td><button id="showDrawing" onclick="showDrawing()" class="btn btn-success">Tegning</button></td>
                    <td><button id="downloadPDF" onclick="downloadPDF()" class="btn btn-info">Stykliste</button></td>
                    <c:if test="${sessionScope.rank == 'admin' || sessionScope.rank == 'superadmin' || sessionScope.rank == 'storeworker'}">
                        <td><button id="orderSent" onclick="orderSent(${sessionScope.order.order_id})" class="btn btn-primary">Sendt</button></td>
                    </c:if>
                </tr>
            </table>
    </div>
    <div id="componentsTable">
        <div class="container roundedCorner">
            <h1>
                Stykliste:
            </h1>
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
        Der er ikke fundet en liste af kunder
    </div>
</c:otherwise>
</c:choose>
