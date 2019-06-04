<div class="jumbotron text-center">
    <h1 id="headline">
        Ny komponent
    </h1>
</div>
<div id="newComponentForm">
    <div class="container roundedCorner infobox">
        Beskrivelse:<br>
        <input type="text" id="description" name="description"><br />

        Hjælpetekst:<br>
        <input type="text" id="helpText" name="helpText"><br />

        Længde:<br>
        <input type="text" id="length" name="length"><br />

        Bredde:<br>
        <input type="text" id="width" name="width"><br />

        Højde:<br>
        <input type="text" id="height" name="height"><br />

        Stykpris:<br>
        <input type="number" id="price" name="price"><br />
        <table id="componentButtonTable" align="center">
            <td><button id="newComponentForm" onclick="newComponentForm()" class="btn btn-primary">Opret ny</button></td>
            <td><button id="regretComponentForm" onclick="regretComponentForm()" class="btn btn-danger">Fortryd</button></td>
        </table>
    </div>
</div>
<button id="topBtn" onclick="topFunction()" title="Gå til top">Top</button>
