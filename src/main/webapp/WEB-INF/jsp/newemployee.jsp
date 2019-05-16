<div class="jumbotron text-center">
    <h1 id="headline">
        Ny ansat
    </h1>
</div>
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
        <div class="btn-group" role="group" aria-label="button group changing order">
            <div>
                <button id="newEmployeeForm" onclick="newEmployeeForm()" class="btn btn-primary">Fortsæt</button>
            </div>
            <div>
                <button id="regretEmployeeForm" onclick="regretEmployeeForm()" class="btn btn-danger">Fortryd</button>
            </div>
        </div>
    </div>
</div>
