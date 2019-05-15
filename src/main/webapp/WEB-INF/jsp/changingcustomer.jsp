<c:if test="${sessionScope.customer != null}">

    <h1>
        Ændre kunde:
    </h1>
    <div id="changeCustomer">
        <div class="container">
            Kunde ID: <c:out value="${sessionScope.customer.customer_id}" />
            Navn<br>
            <input type="text" id="name" name="name" value="${sessionScope.customer.name}"><br>
            Email<br>
            <input type="text" id="email" name="email" value="${sessionScope.customer.email}"><br>
            Telefon<br>
            <input type="text" id="phoneNumber" name="phoneNumber" value="${sessionScope.customer.phone_number}"><br>
            <div>
                <button id="changeChangeCustomer" onclick="changeCustomerForm()" class="btn btn-warning">Fortsæt</button>
            </div>
            <div>
                <button id="regretChangeCustomer" onclick="regretCustomerForm()" class="btn btn-info">Fortryd</button>
            </div>
        </div>
    </div>
</c:if>