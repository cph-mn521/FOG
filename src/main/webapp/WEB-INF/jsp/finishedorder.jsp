<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.pdffilename != null}">
        <div class="jumbotron text-center">
                <h1>
                    Ordre færdig
                </h1>
                Betalingen er genført.
                <div class="container">
                    <a href="pdf/<c:out value='${sessionScope.pdffilename}'/>" download>
                        <img src="img/pdf.jpg" alt="pdf" width="50">
                    </a>
                    <br>
                </div>
                <table align="center">
                    <tr>
                        <td><button id="showDrawing" onclick="showDrawing()" class="btn btn-success">Tegning</button></td>
                    </tr>
                </table>
        </div>
    </c:when>
    <c:otherwise>
        <div id='errorInfo'><h6>Der er ikke fundet en fil. Prøv at logge ind igen</h6></div>
    </c:otherwise>
</c:choose>