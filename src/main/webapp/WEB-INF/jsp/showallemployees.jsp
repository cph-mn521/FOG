<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.employees != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                Ansatte
            </h1>
        </div>
        <div id="employeeTable">
            <div class="container roundedCorner">
                <a id="newEmployeeShowAllEmployeesPage" onclick="newEmployee()" href="#"> <img src="img/new.png" alt="nyt"> Ny ansat</a>
                <input type="text" id="searchInput" onkeyup="tableSearch('employeesListTable')" placeholder="S�g p� navn.." title="S�g navn">
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
        </div>
    </c:when>
    <c:otherwise>
        <div>
            Der er ikke fundet en liste af kunder
        </div>
    </c:otherwise>
</c:choose>