<div class="jumbotron text-center">
    <h1 id="headline">
        Ny kunde
    </h1>
</div>
<div id="newCustomerForm">
    <div class="container roundedCorner infobox">
        Navn:<br>
        <input type="text" id="name" name="name"><br>

        Email<br>
        <input type="email" id="email" name="email"><br>

        Kodeord<br>
        <input type="password" id="password" name="password"><br>

        Telefon:<br>
        <input type="text" id="phoneNumber" align="center" name="phoneNumber"><br>

        <table id="customerButtonTable" align="center">
            <tr>    
                <td><button id="newCustomerForm" onclick="newCustomerForm()" class="btn btn-primary">Skab ny</button></td>
                <td><button id="regretCustomerForm" onclick="regretCustomerForm()" class="btn btn-danger">Fortryd</button></td>
            </tr>
        </table>
    </div>
</div>
<button id="topBtn" onclick="topFunction()" title="Gå til top">Top</button>