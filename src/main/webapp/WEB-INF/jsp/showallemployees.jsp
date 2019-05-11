<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.employees != null}">

        <!--AJAX call-->
        <div id="changingComponents"></div>

        <h1>
            Komponentliste
        </h1>
        <div id="bomTable">
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
                            <td>${employee.rank}</td>
                            <td>${employee.employee_id}</td>
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
            Der er ikke fundet en liste af komponenter
        </div>
    </c:otherwise>
</c:choose>