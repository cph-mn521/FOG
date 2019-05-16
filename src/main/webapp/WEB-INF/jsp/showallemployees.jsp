<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.employees != null}">
        <h1>
            Ansatte
        </h1>
        <div id="employeeTable">
            <div class="container">
                <table id="employeesListTable" class="table table-hover table-condensed table-striped text-center">
                    <tr class="table">
                        <th>Kunde ID</th>
                        <th>Navn</th>
                        <th>Stilling</th>
                        <th>Email</th>
                        <th>Telefon</th>
                    </tr>
                    <c:forEach items="${sessionScope.employees}" var="employee"> 
                        <tr class="table-bordered">
                            <td>${employee.employee_id}</td>
                            <td>${employee.rank}</td>
                            <td>${employee.name}</td>
                            <td>${employee.email}</td>
                            <td>${employee.phone_number}</td>
                        </c:forEach>
                </table>
            </div>
            <a id="newEmployeeShowAllEmployeesPage" onclick="newEmployee()" href="#">Ny ansat</a>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            Der er ikke fundet en liste af kunder
        </div>
    </c:otherwise>
</c:choose>