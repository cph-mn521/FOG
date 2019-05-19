<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.employee != null && sessionScope.rank != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                Ændre ansat
            </h1>
        </div>
        <button onclick="topFunction()" id="topBtn" title="Gå til top">Top</button>
        <div id="employeeTable">
            <div class="container roundedCorner infobox">
                <div>
                    <table id="orderInfoTable">
                        <tr>
                            <td colspan="2"><b>Ansat ID</b>  ${sessionScope.employee.employee_id}</td>
                        </tr>
                        <tr>
                            <td>Navn: </td>
                            <td><input type="text" id="name" name="name" value="${sessionScope.employee.name}" size="15"><br></td> 
                        </tr>
                        <tr>
                            <td>Stilling: </td>
                            <td><input type="text" id="rank" name="rank" value="${sessionScope.employee.rank}" size="15"></td> 
                        </tr>
                        <tr>
                            <td>Email </td>
                            <td><input type="text" id="email" name="email" value="${sessionScope.employee.email}" size="15"></td> 
                        </tr>
                        <tr>
                            <td>Telefon: </td>
                            <td><input type="text" id="phoneNumber" name="phoneNumber" value="${sessionScope.employee.phone_number}" size="15"></td> 
                        </tr>
                        <table id="employeeButtonTable" align="center">
                            <tr>
                                <c:if test="${sessionScope.rank == 'superadmin'}">        
                                    <td><button id="changeEmployeeForm" onclick="changeEmployeeForm()" class="btn btn-primary">Opdatér</button></td>
                                    <td><button id="removeEmployeeForm" onclick="removeEmployeeForm(${sessionScope.employee.employee_id})" class="btn btn-warning">Fjern</button></td>
                                </c:if>
                                <td><button id="regretEmployeeForm" onclick="regretEmployeeForm()" class="btn btn-danger">Fortryd</button></td>
                            </tr>
                        </table>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div id="errorInfo"><h6>Der er ikke fundet en liste af komponenter. Prøv at logge ind igen</h6></div>
        </c:otherwise>
    </c:choose>
