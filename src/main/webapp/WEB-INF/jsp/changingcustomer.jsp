<c:if test="${sessionScope.customer != null}">
    <div class="jumbotron text-center">
        <h1 id="headline">
            Ændre kunde
        </h1>
    </div>
    <div id="changeCustomer">
        <div class="container">
            Kunde ID: ${sessionScope.customer.customer_id}<br/>
            Navn<br/>
            <input type="text" id="name" name="name" value="${sessionScope.customer.name}"><br/>
            Email<br/>
            <input type="text" id="email" name="email" value="${sessionScope.customer.email}"><br/>
            Telefon<br/>
            <input type="text" id="phoneNumber" name="phoneNumber" value="${sessionScope.customer.phone_number}"><br>
            <div class="btn-group" role="group" aria-label="button group changing customer">
                <div>
                    <button id="changeCustomerForm" onclick="changeCustomerForm()" class="btn btn-primary">Fortsæt</button>
                </div>
                <div>
                    <button id="regretCustomerForm" onclick="regretCustomerForm()" class="btn btn-danger">Fortryd</button>
                </div>
                <div>
                    <button id="removeCustomerForm" onclick="removeCustomerForm(${sessionScope.customer.customer_id})" class="btn btn-warning">Fjern</button>
                </div>
            </div>
        </div>
    </div>
</c:if>