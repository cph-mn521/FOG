<h1>
    Ny ansat
</h1>
<div id="newEmployeeForm">
    <div class="container">
        Navn:<br>
        <input type="text" id="name" name="name" value=""><br>

        Stilling:<br>
        <input type="text" id="rank" list="ranks" name="rank">
        <datalist id="ranks">
            <option value="storeworker">
            <option value="admin">
            <option value="salesperson">
            <option value="superadmin">
        </datalist><br />

        Password<br>
        <input type="password" id="password" name="password" value=""><br>

        Email:<br>
        <input type="text" id="email" name="email" value=""><br>

        Telefon:<br>
        <input type="text" id="phoneNumber" name="phoneNumber" value=""><br>
        <div>
            <button id="newEmployeeForm" onclick="newEmployeeForm()" class="btn btn-warning">Fortsæt</button>
        </div>
        <div>
            <button id="regretEmployeeForm" onclick="regretEmployeeForm()" class="btn btn-info">Fortryd</button>
        </div>
    </div>
</div>
