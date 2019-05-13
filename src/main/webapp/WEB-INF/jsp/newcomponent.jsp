<h1>
    Ny komponent
</h1>
<div id="newComponentForm">
    <div class="container">
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="NewComponent" />

            Beskrivelse:<br>
            <input type="text" name="description"><br />
            
            Hjælpetekst:<br>
            <input type="text" name="helpText"><br />
            
            Længde:<br>
            <input type="text" name="length"><br />
            
            Bredde:<br>
            <input type="text" name="width"><br />
            
            Højde:<br>
            <input type="text" name="height"><br />
            
            Stykpris:<br>
            <input type="number" name="price"><br />
            
            <div>
                <button id="doneChangedComponent" class="btn btn-warning">Fortsæt</button>
            </div>
        </form>
        <form action="FrontController" method="POST">
            <input type="hidden" name="command" value="NewComponent" />
            <button id="regretChangedComponent" class="btn btn-info">Fortryd</button>
        </form>
    </div>
</div>
