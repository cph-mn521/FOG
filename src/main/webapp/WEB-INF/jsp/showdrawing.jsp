<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<button onclick="topFunction()" id="topBtn" title="Gå til top">Top</button>
<%
    if (session.getAttribute("carport") != null) {
%>
<jsp:include page="svg.jsp"/>
<% } else {
%>
<h1> Please select a carport first </h1>

<%}
%>