<c:if>
    <c:when test="${sessionScope.component != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                Ændre materialer
            </h1>
        </div>
        <div id="componentTable">
            <div class="container">
                Materiale ID: ${sessionScope.component.componentId}<br/>

                Beskrivelse:<br/>
                <input type="text" id="description" name="description" value="${sessionScope.component.description}"><br/>
                Hjælpetekst:<br/>
                <input type="text" id="helpText" name="helpText" value="${sessionScope.component.helpText}"><br/>
                Bredde<br/>
                <input type="text" id="width" name="width" value="${sessionScope.component.width}"><br/>
                Højde<br/>
                <input type="text" id="height" name="height" value="${sessionScope.component.height}"><br/>
                Længde<br/>
                <input type="text" id="length" name="length" value="${sessionScope.component.length}"><br/>
                Stykpris<br/>
                <input type="text" id="price" name="price" value="${sessionScope.component.price}"><br/>
                <div class="btn-group" role="group" aria-label="button group changing component">
                    <div>
                        <button id="changeComponentForm" onclick="changeComponentForm()" class="btn btn-primary">Fortsæt</button>
                    </div>
                    <div>
                        <button id="regretChangeComponent" onclick="regretComponentForm()" class="btn btn-danger">Fortryd</button>
                    </div>
                    <div>
                        <button id="removeComponentForm" onclick="removeComponentForm(${sessionScope.component.componentId})"
                                class="btn btn-warning">Fjern</button>
                    </div>
                </div>
            </div>
        </div>
</c:if>
