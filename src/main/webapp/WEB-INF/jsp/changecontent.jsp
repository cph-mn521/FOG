<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${sessionScope.BOM != null}">
        <h1>
            Stykliste:
        </h1>
        <div id="bomTable">
            <div class="container">
                changeContent
            </div>
        </div>
    </c:when>
    <c:otherwise>
        <div>
            Der er ikke fundet en changeContent
        </div>
    </c:otherwise>
</c:choose>