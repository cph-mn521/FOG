<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.employee != null}">
        <h1>
            Ændre ansat
        </h1>
        <div id="employeeTable">
            <div class="container">
                Ansattes ID: <c:out value="${sessionScope.employee.employee_id}" />
                <!--<form action="FrontController" method="POST">-->
                    <!--<input type="hidden" name="command" value="EmployeeCommand" />-->
                    <!--<input type="hidden" name="commandType" value="changed" />-->

                    Navn:<br>
                    <input type="text" id="name" name="name" value="${sessionScope.employee.name}"><br>
                    Stilling:<br>
                    <input type="text" id="rank" name="rank" value="${sessionScope.employee.rank}"><br>
                    Email:<br>
                    <input type="text" id="email" name="email" value="${sessionScope.employee.email}"><br>
                    Telefon:<br>
                    <input type="text" id="phone_number" name="phone_number" value="${sessionScope.employee.phone_number}"><br>
                    <div>
                        <button id="doneChangedEmployee" onclick="changeEmployeeForm()" class="btn btn-warning">Fortsæt</button>
                    </div>
                    <div>
                    </div>
                <!--</form>-->
                <!--<form action="FrontController" method="POST">-->
                    <!--<input type="hidden" name="command" value="EmployeeCommand" />-->
                    <!--<input type="hidden" name="commandType" value="changed" />-->
                    <button id="regretChangedEmployee" onclick="regretChangeEmployeeForm()" class="btn btn-info">Fortryd</button>
                <!--</form>-->
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            Der er ikke fundet en liste af komponenter
        </div>
    </c:otherwise>
</c:choose>
