<c:choose>
    <c:when test="${sessionScope.component != null}">
        <h1>
            Ændre i materiale:
        </h1>
        <div id="bomTable">
            <div class="container">
                Komponent ID: <c:out value="${sessionScope.component.componentId}" />
                <form action="FrontController" method="POST">
                    <input type="hidden" name="command" value="ChangedComponents" />

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
                    <div>
                        <form action="FrontController" method="POST">
                            <input type="hidden" name="command" value="ChangedComponents" />
                            <button id="wishes" class="btn btn-info">Fortryd</button>
                        </form>
                    </div>
                </form>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            Der er ikke fundet en liste af komponenter
        </div>
    </c:otherwise>
</c:choose>
