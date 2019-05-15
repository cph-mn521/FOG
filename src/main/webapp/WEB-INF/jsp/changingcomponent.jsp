<c:if>
    <c:when test="${sessionScope.component != null}">
        <h1>
            Ændre materialer:
        </h1>
        <div id="componentTable">
            <div class="container">
                Materiale ID: <c:out value="${sessionScope.component.componentId}" />

                Beskrivelse:<br>
                <input type="text" name="description" value="${sessionScope.component.description}"><br>
                Hjælpetekst:<br>
                <input type="text" name="helpText" value="${sessionScope.component.helpText}"><br>
                Bredde<br>
                <input type="text" name="width" value="${sessionScope.component.width}"><br>
                Højde<br>
                <input type="text" name="height" value="${sessionScope.component.height}"><br>
                Stykpris<br>
                <input type="text" name="price" value="${sessionScope.component.price}"><br>
                <div>
                    <button id="doneChangeComponent" onclick="changeComponentForm()" class="btn btn-warning">Fortsæt</button>
                </div>
                <div>
                    <button id="regretChangeComponent" onclick="regretChangeComponent()" class="btn btn-info">Fortryd</button>
                </div>
            </div>
        </div>
</c:if>
