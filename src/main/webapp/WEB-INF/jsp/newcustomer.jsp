<h1>
    Ny kunde
</h1>
<div id="newCustomerForm">
    <div class="container">
        Navn:<br>
        <input type="text" id="name" name="name"><br>

        Email<br>
        <input type="email" id="email" name="email"><br>

        Kodeord<br>
        <input type="password" id="password" name="password"><br>

        Telefon:<br>
        <input type="text" id="phoneNumber" name="phoneNumber"><br>
        <div class="btn-group" role="group" aria-label="button group changing order">
            <div>
                <button id="newCustomerForm" onclick="newCustomerForm()" class="btn btn-primary">Fortsæt</button>
            </div>
            <div>
                <button id="regretCustomerForm" onclick="regretCustomerForm()" class="btn btn-danger">Fortryd</button>
            </div>
            <div>
                <button id="removeCustomerForm" onclick="removeCustomerForm()" class="btn btn-warning">Fortryd</button>
            </div>
        </div>
    </div>
</div>
