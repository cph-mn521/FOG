<h1>
    Ny ansat
</h1>
<div id="newEmployeeForm">
    <div class="container">
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="NewEmployee" />

            Navn:<br>
            <input type="text" name="name" value=""><br>
            
            Stilling:<br>
            <input type="text" list="ranks" name="rank">
            <datalist id="ranks">
                <option value="storeworker">
                <option value="admin">
                <option value="salesperson">
                <option value="superadmin">
            </datalist><br />
            
            Email:<br>
            <input type="text" name="email" value=""><br>
            
            Kodeord<br>
            <input type="text" name="password" value=""><br>
            
            Telefon:<br>
            <input type="text" name="phone_number" value=""><br>
            
            <div>
                <button id="doneChangedEmployee" class="btn btn-warning">Fortsæt</button>
            </div>
        </form>
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="NewEmployee" />
            <button id="regretChangedEmployee" class="btn btn-info">Fortryd</button>
        </form>
    </div>
</div>
