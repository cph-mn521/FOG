<div class="jumbotron text-center">
    <h1 id="headline">
        Opdater Brugerinformation
    </h1>
</div>
<div id="editProfileForm">
    <div class="container roundedCorner infobox">
        Navn:<br>
        <input type="text" id="name" name="name" value=""><br>
        
        Password<br>
        <input type="password" id="password" name="password" value=""><br>

        Email:<br>
        <input type="text" id="email" name="email" value=""><br>

        Telefon:<br>
        <input type="text" id="phoneNumber" name="phoneNumber" value=""><br>

        <table id="editProfileTble" align="center">
            <tr> 
                <td><button id="newEmployeeForm" onclick="editProfile()" class="btn btn-primary">Opdater</button></td>
            </tr>    
        </table>
    </div>
</div>
<script>
    $(document).ready(setValues());
</script>

<!--<button id="topBtn" onclick="topFunction()" title="Gå til top">Top</button> -->
