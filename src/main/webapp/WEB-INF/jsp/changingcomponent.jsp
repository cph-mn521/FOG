<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.component != null && sessionScope.rank != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                Ændre materialer
            </h1>
        </div>
        <div id="componentTable">
            <div class="container infobox">
                <table id="componentInfoTable">
                    <tr>
                        <td>Materiale ID  </td>
                        <td>${sessionScope.component.componentId}</td>
                    </tr>
                    <tr>
                        <td>Beskrivelse  </td>
                        <td><input type="text" id="description" name="description" value="${sessionScope.component.description}" size="30"></td> 
                    </tr>
                    <tr>
                        <td>Hjælpetekst  </td>
                        <td><input type="text" id="helpText" name="helpText" value="${sessionScope.component.helpText}" size="30"></td> 
                    </tr>
                    <tr>
                        <td>Bredde  </td>
                        <td><input type="text" id="width" name="width" value="${sessionScope.component.width}" size="1"></td> 
                    </tr>
                    <tr>
                        <td>Højde  </td>
                        <td><input type="text" id="height" name="height" value="${sessionScope.component.height}" size="1"></td> 
                    </tr>
                    <tr>
                        <td>Længde  </td>
                        <td><input type="text" id="length" name="length" value="${sessionScope.component.length}" size="1"></td> 
                    </tr>
                    <tr>
                        <td>Stykpris  </td>
                        <td><input type="text" id="price" name="price" value="${sessionScope.component.price}" size="1"></td> 
                    </tr>
                    <div class="centerize">
                        <table id="componentButtonTable" align="center">
                            <tr>
                                <c:if test="${sessionScope.rank == 'admin' || sessionScope.rank == 'superadmin'}">
                                    <td><button id="changeComponentForm" onclick="changeComponentForm()" class="btn btn-primary">Fortsæt</button></td>
                                    <td><button id="removeComponentForm" onclick="removeComponentForm(${sessionScope.component.componentId})"
                                                class="btn btn-warning">Fjern</button></td>
                                    </c:if>
                                <td><button id="regretChangeComponent" onclick="regretComponentForm()" class="btn btn-danger">Fortryd</button></td>
                            </tr>
                        </table>
                    </div>
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            Der er ikke fundet en liste af kunder
        </div>
    </c:otherwise>
</c:choose>