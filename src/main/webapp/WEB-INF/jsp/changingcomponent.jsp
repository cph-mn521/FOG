<c:if>
    <c:when test="${sessionScope.component != null}">
        <h1>
            Ændre materialer:
        </h1>
        <div id="componentTable">
            <div class="container">
                Materiale ID: <c:out value="${sessionScope.component.componentId}" />

                Beskrivelse:<br>
                <input type="text" id="description" name="description" value="${sessionScope.component.description}"><br>
                Hjælpetekst:<br>
                <input type="text" id="helpText" name="helpText" value="${sessionScope.component.helpText}"><br>
                Bredde<br>
                <input type="text" id="width" name="width" value="${sessionScope.component.width}"><br>
                Højde<br>
                <input type="text" id="height" name="height" value="${sessionScope.component.height}"><br>
                Længde<br>
                <input type="text" id="length" name="length" value="${sessionScope.component.length}"><br>
                Stykpris<br>
                <input type="text" id="price" name="price" value="${sessionScope.component.price}"><br>
                <div>
                    <button id="changeComponentForm" onclick="changeComponentForm()" class="btn btn-warning">Fortsæt</button>
                </div>
                <div>
                    <button id="regretChangeComponent" onclick="regretComponentForm()" class="btn btn-info">Fortryd</button>
                </div>
            </div>
        </div>
</c:if>
