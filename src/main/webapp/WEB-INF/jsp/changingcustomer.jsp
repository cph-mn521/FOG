<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.customer != null && sessionScope.rank != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                Ændre kunde
            </h1>
        </div>
        <!--<div id="changeCustomer">-->
        <div class="container roundedCorner infobox">
            <table id="customerInfoTable" align="center">
                <tr>
                    <td colspan="2"><b>Kunde ID</b>  ${sessionScope.customer.customer_id}</td>
                </tr>
                <tr>
                    <td>Navn </td>
                    <td><input type="text" id="name" name="name" value="${sessionScope.customer.name}" size="15"></td>
                </tr>
                <tr>
                    <td>Email </td>
                    <td><input type="text" id="email" name="email" value="${sessionScope.customer.email}" size="15"></td>
                </tr>
                <tr>
                    <td>Telefon </td>
                    <td><input type="text" id="phoneNumber" name="phoneNumber" value="${sessionScope.customer.phone_number}" size="10"></td>
                </tr>
            </table>
            <table id="customerButtonTable" align="center">
                <tr>    
                    <c:if test="${sessionScope.rank == 'admin' || sessionScope.rank == 'superadmin' || sessionScope.rank == 'salesperson'}">
                        <td><button id="changeCustomerForm" onclick="changeCustomerForm()" class="btn btn-primary">Opdatér</button></td>
                        <td><button id="removeCustomerForm" onclick="removeCustomerForm(${sessionScope.customer.customer_id})" 
                                    class="btn btn-warning">Fjern</button></td>
                        </c:if>
                    <td><button id="regretCustomerForm" onclick="regretCustomerForm()" class="btn btn-danger">Fortryd</button></td>
                </tr>
            </table>
        </div>
        <button id="topBtn" onclick="topFunction()" title="Gå til top">Top</button>
    </c:when>
    <c:otherwise>
        <div id="errorInfo"><h6>Der er ikke fundet en liste af kunde. Prøv at logge ind igen</h6></div>
    </c:otherwise>
</c:choose>