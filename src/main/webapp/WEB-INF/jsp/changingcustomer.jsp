<c:if test="${sessionScope.customer != null}">

    <h1>
        Ændre kunde:
    </h1>
    <div id="changeCustomer">
        <div class="container">
            Kunde ID: <c:out value="${sessionScope.customer.customer_id}" />
            <form action="FrontController" method="POST">
                <input type="hidden" name="command" value="CustomerCommand" />
                    <input type="hidden" name="commandType" value="changed" />

                Navn<br>
                <input type="text" name="name" value="${sessionScope.customer.name}"><br>
                Email<br>
                <input type="text" name="email" value="${sessionScope.customer.email}"><br>
                Kodeord<br>
                <input type="text" name="password" value="${sessionScope.customer.password}"><br>
                Telefon<br>
                <input type="text" name="phone_number" value="${sessionScope.customer.phone_number}"><br>
                <div>
                    <button id="next" class="btn btn-warning">Fortsæt</button>
                </div>
                <div>
                    <form action="FrontController" method="POST">
                        <input type="hidden" name="command" value="CustomerCommand" />
                    <input type="hidden" name="commandType" value="changed" />
                        <button id="wishes" class="btn btn-info">Fortryd</button>
                    </form>
                </div>
            </form>
        </div>
    </div>
</c:if>