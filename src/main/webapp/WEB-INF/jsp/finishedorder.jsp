<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<h1>
    Ordre færdig
</h1>
<c:if test="${sessionScope.pdffilename != null}">
    <div class="container">
        <a href="<c:out value='pdffilename' /> download>
            <img src="img/pdf.jpg" alt="pdf" width="104">
        </a>
    </div>
</c:if>