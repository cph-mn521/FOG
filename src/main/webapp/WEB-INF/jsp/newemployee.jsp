<h1>
    Ny ansat
</h1>
<div id="newEmployeeForm">
    <div class="container">
        Navn:<br>
        <input type="text" id="name" name="name" value=""><br>

        Stilling:<br>
        <input type="text" id="ranks" list="ranks" name="rank">
        <datalist id="ranks">
            <option value="storeworker">
            <option value="admin">
            <option value="salesperson">
            <option value="superadmin">
        </datalist><br />

        Email:<br>
        <input type="text" id="name" name="email" value=""><br>

        Telefon:<br>
        <input type="text" id="phoneNumber" name="phoneNumber" value=""><br>
        <div>
            <button id="doneNewEmployee" onclick="newEmployeeForm()" class="btn btn-warning">Fortsæt</button>
        </div>
        <div>
            <button id="regretNewEmployee" onclick="regretNewEmployee()" class="btn btn-info">Fortryd</button>
        </div>
    </div>
</div>
