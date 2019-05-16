<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.orders != null}">
    <div class="jumbotron text-center">
        <h1 id="headline">
            Ordrer
        </h1>
    </div>

    <div id="orderTable">
            <div class="container roundedCorner">
            <a id="newOrderShowAllOrdersPage" onclick="newOrder()" href="#"> <img src="img/new.png" alt="nyt"> Ny Ordre</a>
            <input type="text" id="searchInput" onkeyup="tableSearch()" placeholder="S�g p� ordre modtaget dato.." title="S�g p� modtaget dato">
            <table id="ordersListTable" class="table table-hover table-condensed table-striped text-center">
                <tr class="table">
                    <th>Ordre ID</th>
                    <th>Ordre modtaget</th>
                    <th>Ordre afsendt</th>
                    <th>Ordre status</th>
                    <th>Total pris</th>
                    <th>Kunde ID</th>
                    <th>Kunde Adresse</th>
                </tr>
                <c:forEach items="${sessionScope.orders}" var="order"> 
                    <tr class="table-bordered">
                        <td>${order.order_id}</td>
                        <td>${order.order_receive_date}</td>
                        <td>${order.order_send_date}</td>
                        <td>${order.order_status}</td>
                        <td>${order.total_price}</td>
                        <td>${order.customer_id}</td>
                        <td>${order.customer_address}</td>
                    </c:forEach>
            </table>
        </div>
    </div>
</c:if>