<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.orders != null && sessionScope.rank != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                Ordrer
            </h1>
        </div>
        <div id="orderTable">
            <div class="roundedCorner">
                <c:if test="${sessionScope.rank == 'admin' || sessionScope.rank == 'superadmin' || sessionScope.rank == 'salesperson'}">
                    <a id="newOrderShowAllOrdersPage" onclick="newOrder()" href="#"> <img src="img/new.png" alt="nyt"> Ny Ordre</a>
                    </c:if>
                <input type="text" id="searchInput" style="background: url(img/searchicon.png) no-repeat left center;" onkeyup="tableSearch('ordersListTable')" placeholder="Søg på ordre modtaget dato.." title="Søg på modtaget dato">
                <table id="ordersListTable" class="table table-hover table-condensed table-striped text-center">
                    <tr class="table">
                        <th>Ordre ID</th>
                        <th>Ordre modtaget</th>
                        <th>Ordre afsendt</th>
                        <th>Ordrestatus</th>
                        <th>Totalpris</th>
                        <th>Kunde ID</th>
                        <th>Kundeadresse</th>
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
        <button id="topBtn" onclick="topFunction()" title="Gå til top">Top</button>
    </c:when>
    <c:otherwise>
        <div id="errorInfo"><h6>Der er ikke fundet en liste af ordrer. Prøv at logge ind igen</h6></div>
    </c:otherwise>
</c:choose>