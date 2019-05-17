<c:if test="${sessionScope.customer != null}">
    <div class="jumbotron text-center">
        <h1 id="headline">
            Ændre kunde
        </h1>
    </div>
    <div id="changeCustomer">
        <div class="container infobox">
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
                    <td><button id="changeCustomerForm" onclick="changeCustomerForm()" class="btn btn-primary">Fortsæt</button></td>
                    <td><button id="regretCustomerForm" onclick="regretCustomerForm()" class="btn btn-danger">Fortryd</button></td>
                    <td><button id="removeCustomerForm" onclick="removeCustomerForm(${sessionScope.customer.customer_id})" 
                                class="btn btn-warning">Fjern</button></td>
                </tr>
            </table>
        </div>
</c:if>