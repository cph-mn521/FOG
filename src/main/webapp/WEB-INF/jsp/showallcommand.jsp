<%-- 
    Document   : showAllCommand
    Created on : May 12, 2019, 1:32:25 PM
    Author     : martin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${sessionScope.showallcommand == 'components'}">
    <script>showComponents();</script>
</c:if>