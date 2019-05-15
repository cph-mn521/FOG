<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.orders != null}">
    <h1>
        Ordrer:
    </h1>
    
    <div id="orderTable">
        <div class="container">
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