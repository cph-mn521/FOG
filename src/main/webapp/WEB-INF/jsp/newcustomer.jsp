<h1>
    Ny kunde
</h1>
<div id="newCustomerForm">
    <div class="container">
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="CustomerCommand" />
            <input type="hidden" name="commandType" value="newfinished" />

            Navn:<br>
            <input type="text" name="name"><br>
            
            Email<br>
            <input type="email" name="email"><br>
            
            Kodeord<br>
            <input type="password" name="password"><br>
            
            Telefon:<br>
            <input type="text" name="phone_number"><br>
            
            <div>
                <button id="doneChangedCustomer" class="btn btn-warning">Fortsæt</button>
            </div>
        </form>
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="CustomerCommand" />
            <input type="hidden" name="commandType" value="newfinished" />
            <button id="regretChangedCustomer" class="btn btn-info">Fortryd</button>
        </form>
    </div>
</div>
