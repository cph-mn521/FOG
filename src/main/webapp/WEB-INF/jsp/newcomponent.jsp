<h1>
    Nyt Materiale
</h1>
<div id="newComponentForm">
    <div class="container">
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="ComponentCommand" />
            <input type="hidden" name="commandType" value="newfinished" />

            Beskrivelse:<br>
            <input type="text" name="description"><br />
            
            Hj�lpetekst:<br>
            <input type="text" name="helpText"><br />
            
            L�ngde:<br>
            <input type="text" name="length"><br />
            
            Bredde:<br>
            <input type="text" name="width"><br />
            
            H�jde:<br>
            <input type="text" name="height"><br />
            
            Stykpris:<br>
            <input type="number" name="price"><br />
            
            <div>
                <button id="doneNewComponent" class="btn btn-warning">Forts�t</button>
            </div>
        </form>
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="ComponentCommand" />
            <input type="hidden" name="commandType" value="newfinished" />
            <button id="regretNewComponent" class="btn btn-info">Fortryd</button>
        </form>
    </div>
</div>
