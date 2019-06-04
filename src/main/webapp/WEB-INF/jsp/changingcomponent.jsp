<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.component != null && sessionScope.rank != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                Ændr materialer
            </h1>
        </div>
        <div id="componentTable">
            <div class="container roundedCorner infobox">
                <b>Materiale ID</b> #${sessionScope.component.componentId}
                <table id="componentInfoTable">
                    <tr>
                        <td>Beskrivelse  </td>
                    </tr>
                    <tr>
                        <td colspan="4"><input type="text" id="description" name="description" value="${sessionScope.component.description}" size="30"></td> 
                    </tr>
                    <tr>
                        <td>Hjælpetekst  </td>
                    </tr>
                    <tr>
                        <td colspan="4"><input type="text" id="helpText" name="helpText" value="${sessionScope.component.helpText}" size="30"></td> 
                    </tr>
                    <tr class="boldText">
                        <td>Længde</td>
                        <td>Bredde  </td>
                        <td>Højde  </td>
                        <td>Stykpris  </td>
                    </tr>
                    <tr>
                        <td><input type="text" id="length" name="length" value="${sessionScope.component.length}" size="1"></td> 
                        <td><input type="text" id="width" name="width" value="${sessionScope.component.width}" size="1"></td> 
                        <td><input type="text" id="height" name="height" value="${sessionScope.component.height}" size="1"></td> 
                        <td><input type="text" id="price" name="price" value="${sessionScope.component.price}" size="1"></td> 
                    </tr>
                </table>
                <table id="componentButtonTable" align="center">
                    <tr>
                        <c:if test="${sessionScope.rank == 'admin' || sessionScope.rank == 'superadmin'}">
                            <td><button id="changeComponentForm" onclick="changeComponentForm()" class="btn btn-primary">Opdatér</button></td>
                            <td><button id="removeComponentForm" onclick="removeComponentForm(${sessionScope.component.componentId})"
                                        class="btn btn-warning">Fjern</button></td>
                            </c:if>
                        <td><button id="regretChangeComponent" onclick="regretComponentForm()" class="btn btn-danger">Fortryd</button></td>
                    </tr>
                </table>
            </div>
        </div>
        <button id="topBtn" onclick="topFunction()" title="Gå til top">Top</button>
    </c:when>
    <c:otherwise>
        <div id="errorInfo"><h6>Der er ikke fundet en liste af kunder</h6></div>
    </c:otherwise>
</c:choose>