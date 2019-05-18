<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:choose>
    <c:when test="${sessionScope.pdffilename != null}">
        <div class="jumbotron text-center">
            <h1 id="headline">
                <h1>
                    Ordre færdig
                </h1>
                <div class="container">
                    1. ${sessionScope.pdffilename}<br/>
                    <a href="pdf/<c:out value='${sessionScope.pdffilename}'/>" download>
                        <img src="img/pdf.jpg" alt="pdf" width="50">
                    </a>
                    <br>
                    ${sessionScope.pdffilepath}
                </div>
                <button onclick="saveSvg(document.getElementById('TopDown'), 'TopDownSvg')"  class="btn btn-info" >Download</button>
                <button onclick="saveSvg(document.getElementById('Front'), 'FrontSVG')"  class="btn btn-info" >Download</button>
                <button onclick="saveSvg(document.getElementById('FocusA'), 'FocusSVG')" class="btn btn-info" >Download</button>
                <button onclick="saveSvg(document.getElementById('Side'), 'SideSVG')"  class="btn btn-info" >Download</button>
        </div>
    </c:when>
    <c:otherwise>
        <div id='errorInfo'><h6>Der er ikke fundet en fil. Prøv at logge ind igen</h6></div>
    </c:otherwise>
</c:choose>