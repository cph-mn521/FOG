<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.pdffilename != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                <h1>
                    Ordre færdig
                </h1>
                <div class="container">
                    1. <c:out value='${sessionScope.pdffilename}'/><br/>
                    2. ${sessionScope.pdffilename}<br/>
                    <a href="<c:out value='${sessionScope.pdffilename}'/>" download>
                        <img src="img/pdf.jpg" alt="pdf" width="104">
                    </a>
                        <a href="file:////home/martin/FOGStyklistePDF/FOGCarportstykliste15_2019-05-18.pdf">Link 1</a>
                </div>
        </div>
    </c:when>
    <c:otherwise>
        <div id='errorInfo'><h6>Der er ikke fundet en fil. Prøv at logge ind igen</h6></div>
    </c:otherwise>
</c:choose>