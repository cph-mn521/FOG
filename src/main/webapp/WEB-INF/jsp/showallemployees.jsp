<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.employees != null && sessionScope.rank != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                Ansatte
            </h1>
        </div>
        <div id="employeeTable">
            <div class="roundedCorner">
                <c:if test="${sessionScope.rank == 'superadmin'}">
                    <a id="newEmployeeShowAllEmployeesPage" onclick="newEmployee()" href="#"> <img src="img/new.png" alt="nyt"> Ny ansat</a>
                    </c:if>
                <input type="text" id="searchInput" style="background: url(img/searchicon.png) no-repeat left center;" onkeyup="tableSearch('employeesListTable')" placeholder="Søg på navn.." title="Søg navn">
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
                            <td>${employee.name}</td>
                            <td>${employee.rank}</td>
                            <td>${employee.email}</td>
                            <td>${employee.phone_number}</td>
                        </c:forEach>
                </table>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            Der er ikke fundet en liste af ansatte
        </div>
    </c:otherwise>
</c:choose>