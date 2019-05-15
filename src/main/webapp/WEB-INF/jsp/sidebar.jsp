<%-- 
    Document   : sidebar
    Created on : 10-05-2019, 12:12:55
    Author     : Martin
--%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:choose>
    <c:when test="${sessionScope.rank == 'admin'}">
        <%@ include file = "../fragments/sideMenues/admin.jspf" %>
    </c:when>
    <c:when test="${sessionScope.rank == 'salesperson'}">
        <%@ include file = "../fragments/sideMenues/salesperson.jspf" %>
    </c:when>
    <c:when test="${sessionScope.rank == 'superadmin'}">
        <%@ include file = "../fragments/sideMenues/superadmin.jspf" %>
    </c:when>
    <c:when test="${sessionScope.rank == 'storeworker'}">
        <%@ include file = "../fragments/sideMenues/storeworker.jspf" %>
    </c:when>
    <c:otherwise>
        <%@ include file = "../fragments/sideMenues/customer.jspf" %>
    </c:otherwise>
</c:choose>