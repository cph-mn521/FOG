<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.employee != null && sessionScope.rank != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                �ndre ansat
            </h1>
        </div>
        <div id="employeeTable">
            <div class="container roundedCorner infobox">
                <div>
                    <b>Ansat ID</b> #${sessionScope.employee.employee_id}
                    <table id="orderInfoTable">
                        <tr>
                            <td>Navn</td>
                        </tr>
                        <tr>
                            <td><input type="text" id="name" name="name" value="${sessionScope.employee.name}" size="15"><br></td> 
                        </tr>
                        <tr>
                            <td>Stilling</td>
                        </tr>
                        <tr>
                            <td><input type="text" id="rank" name="rank" value="${sessionScope.employee.rank}" size="15"></td> 
                        </tr>
                        <tr>
                            <td>Email</td>
                        </tr>
                        <tr>
                            <td><input type="text" id="email" name="email" value="${sessionScope.employee.email}" size="15"></td> 
                        </tr>
                        <tr>
                            <td>Telefon</td>
                        </tr>
                        <tr>
                            <td><input type="text" id="phoneNumber" name="phoneNumber" value="${sessionScope.employee.phone_number}" size="15"></td> 
                        </tr>
                        <table id="employeeButtonTable" align="center">
                            <tr>
                                <c:if test="${sessionScope.rank == 'superadmin'}">        
                                    <td><button id="changeEmployeeForm" onclick="changeEmployeeForm()" class="btn btn-primary">Opdat�r</button></td>
                                    <td><button id="removeEmployeeForm" onclick="removeEmployeeForm(${sessionScope.employee.employee_id})" class="btn btn-warning">Fjern</button></td>
                                </c:if>
                                <td><button id="regretEmployeeForm" onclick="regretEmployeeForm()" class="btn btn-danger">Fortryd</button></td>
                            </tr>
                        </table>
                </div>
            </div>
            <button id="topBtn" onclick="topFunction()" title="G� til top">Top</button>
        </c:when>
        <c:otherwise>
            <div id="errorInfo"><h6>Der er ikke fundet en liste af komponenter. Pr�v at logge ind igen</h6></div>
        </c:otherwise>
    </c:choose>
