<h1>
    Nyt Materiale
</h1>
<div id="newComponentForm">
    <div class="container">
        Beskrivelse:<br>
        <input type="text" id="description" name="description"><br />

        Hj�lpetekst:<br>
        <input type="text" id="helpText" name="helpText"><br />

        L�ngde:<br>
        <input type="text" id="length" name="length"><br />

        Bredde:<br>
        <input type="text" id="width" name="width"><br />

        H�jde:<br>
        <input type="text" id="height" name="height"><br />

        Stykpris:<br>
        <input type="number" id="price" name="price"><br />
        <div class="btn-group" role="group" aria-label="button group changing order">
            <div>
                <button id="newComponentForm" onclick="newComponentForm()" class="btn btn-primary">Forts�t</button>
            </div>
            <div>
                <button id="regretComponentForm" onclick="regretComponentForm()" class="btn btn-danger">Fortryd</button>
            </div>
        </div>
    </div>
</div>
