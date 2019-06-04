<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${sessionScope.carport != null}">
        <jsp:include page="svg.jsp"/>
        <button onclick="topFunction()" id="topBtn" title="Gå til top">Top</button>
    </c:when>
    <c:otherwise>
        <h1> Vælg venligst en carport først </h1>
    </c:otherwise>
</c:choose>