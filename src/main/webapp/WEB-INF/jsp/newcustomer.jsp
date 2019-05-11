<h1>
    Ny kunde
</h1>
<div id="newCustomerForm">
    <div class="container">
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="NewCustomer" />

            Navn:<br>
            <input type="text" name="name"><br>
            
            Email<br>
            <input type="text" name="email"><br>
            
            Kodeord<br>
            <input type="text" name="password"><br>
            
            Telefon:<br>
            <input type="text" name="phone_number"><br>
            
            <div>
                <button id="doneChangedCustomer" class="btn btn-warning">Fortsæt</button>
            </div>
        </form>
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="NewCustomer" />
            <button id="regretChangedCustomer" class="btn btn-info">Fortryd</button>
        </form>
    </div>
</div>
