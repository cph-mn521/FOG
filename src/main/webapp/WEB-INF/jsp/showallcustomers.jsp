<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.customers != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                Kunder
            </h1>
        </div>
        <div id="customerTable">
            <div class="container roundedCorner">
                <a id="newCustomerShowAllCustomersPage" onclick="newCustomer()" href="#"> <img src="img/new.png" alt="nyt"> Ny Kunde</a>
                <input type="text" id="searchInput" onkeyup="tableSearch('customersListTable')" placeholder="Søg på navn.." title="Søg på navn">
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
        <div>
            Der er ikke fundet en liste af kunder
        </div>
    </c:otherwise>
</c:choose>