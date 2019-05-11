<c:choose>
    <c:when test="${sessionScope.employee != null}">

        <h1>
            Ændre ansat
        </h1>
        <div id="bomTable">
            <div class="container">
                Ansattes ID: <c:out value="${sessionScope.employee.employee_id}" />
                <form action="FrontController" method="POST">
                    <input type="hidden" name="command" value="ChangedEmployee" />

                    Navn:<br>
                    <input type="text" name="name" value="${sessionScope.employee.name}"><br>
                    Stilling:<br>
                    <input type="text" name="rank" value="${sessionScope.employee.rank}"><br>
                    Email:<br>
                    <input type="text" name="email" value="${sessionScope.employee.email}"><br>
                    Telefon:<br>
                    <input type="text" name="phone_number" value="${sessionScope.employee.phone_number}"><br>
                    <div>
                        <button id="doneChangedEmployee" class="btn btn-warning">Fortsæt</button>
                    </div>
                    <div>
                    </div>
                </form>
                <form action="FrontController" method="POST">
                    <input type="hidden" name="command" value="ChangedEmployee" />
                    <button id="regretChangedEmployee" class="btn btn-info">Fortryd</button>
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
