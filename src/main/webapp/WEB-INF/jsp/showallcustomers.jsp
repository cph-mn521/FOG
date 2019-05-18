<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.customers != null && sessionScope.rank != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                Kunder
            </h1>
        </div>
        <div id="customerTable">
            <div class="roundedCorner">
                <c:if test="${sessionScope.rank == 'admin' || sessionScope.rank == 'superadmin' || sessionScope.rank == 'salesperson'}">
                    <a id="newCustomerShowAllCustomersPage" onclick="newCustomer()" href="#"> <img src="img/new.png" alt="nyt"> Ny Kunde</a>
                    </c:if>
                <input type="text" id="searchInput" style="background: url(img/searchicon.png) no-repeat left center;" onkeyup="tableSearch('customersListTable')" placeholder="Søg på navn.." title="Søg på navn">
                <table id="customersListTable" class="table table-hover table-condensed table-striped text-center">
                    <tr class="table">
                        <th>Kunde ID</th>
                        <th>Navn</th>
                        <th>Email</th>
                        <th>Telefon</th>
                    </tr>
                    <c:forEach items="${sessionScope.customers}" var="customer"> 
                        <tr class="table-bordered">
                            <td>${customer.customer_id}</td>
                            <td>${customer.name}</td>
                            <td>${customer.email}</td>
                            <td>${customer.phone_number}</td>
                        </c:forEach>
                </table>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div id="errorInfo"><h6>Der er ikke fundet en liste af kunder. Prøv at logge ind igen</h6></div>
    </c:otherwise>
</c:choose>