<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${sessionScope.carport != null}">
        <jsp:include page="svg.jsp"/>
        <button onclick="topFunction()" id="topBtn" title="G� til top">Top</button>
    </c:when>
    <c:otherwise>
        <h1> V�lg venligst en carport f�rst </h1>
    </c:otherwise>
</c:choose>