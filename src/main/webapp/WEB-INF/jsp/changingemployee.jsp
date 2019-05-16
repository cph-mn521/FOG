<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.employee != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                Ændre ansat
            </h1>
        </div>
        <div id="employeeTable">
            <div class="container" id="outerButtons>
                 Ansattes ID: <c:out value="${sessionScope.employee.employee_id}" />

                 Navn:<br>
                 <input type="text" id="name" name="name" value="${sessionScope.employee.name}"><br>
                Stilling:<br>
                <input type="text" id="rank" name="rank" value="${sessionScope.employee.rank}"><br>
                Email:<br>
                <input type="text" id="email" name="email" value="${sessionScope.employee.email}"><br>
                Telefon:<br>
                <input type="text" id="phoneNumber" name="phoneNumber" value="${sessionScope.employee.phone_number}"><br>
                <div class="btn-group" role="group" aria-label="button group changing employee">
                    <div>
                        <button id="changeEmployeeForm" onclick="changeEmployeeForm()" class="btn btn-primary">Fortsæt</button>
                    </div>
                    <div>
                        <button id="regretEmployeeForm" onclick="regretEmployeeForm()" class="btn btn-danger">Fortryd</button>
                    </div>
                    <div>
                        <button id="removeEmployeeForm" onclick="removeEmployeeForm(${sessionScope.employee.employee_id})" class="btn btn-warning">Fjern</button>
                    </div>
                </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            Der er ikke fundet en liste af komponenter
        </div>
    </c:otherwise>
</c:choose>
