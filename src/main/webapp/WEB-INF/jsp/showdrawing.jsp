<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
if(session.getAttribute("carport") != null){
%>
    <jsp:include page="svg.jsp"/>
<% } else{  
%>
<h1> Please select a carport first </h1>

<%}
%>