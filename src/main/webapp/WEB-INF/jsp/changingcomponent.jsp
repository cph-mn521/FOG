<c:if>
    <c:when test="${sessionScope.component != null}">
        <h1>
            Ændre materialer:
        </h1>
        <div id="componentTable">
            <div class="container">
                Materiale ID: <c:out value="${sessionScope.component.componentId}" />
                <form action="FrontController" method="POST">
                    <input type="hidden" name="command" value="ComponentCommand" />
                    <input type="hidden" name="commandType" value="changed" />

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
                        <button id="next" class="btn btn-warning">Fortsæt</button>
                    </div>
                </form>
                <div>
                    <form action="FrontController" method="POST">
                        <input type="hidden" name="command" value="ComponentCommand" />
                        <input type="hidden" name="commandType" value="changed" />
                        <button id="wishes" class="btn btn-info">Fortryd</button>
                    </form>
                </div>
            </div>
        </div>
</c:if>
