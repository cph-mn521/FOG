
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="topnav">
    <div class="topnav-header">
        <h3>Fog</h3>            
    </div>
    <a class="active" href="#home">Nyheder</a>
    <a href="#about">Profil</a>
    <a href="#contact">Noget andet</a>

    <script src="js/loginScript.js"></script>

    <c:choose>
        <c:when test = "${sessionScope.user != null}">
            <div class="login-container">
                Velkommen <c:out value="${sessionScope.user}" />
            </div>
        </c:when>
        <c:otherwise>
            <div class="login-container">
                <!--<form action="javascript:loginFunction()">-->
                <form action="FrontController" method="POST" id="loginform">
                    <input type="hidden" name="command" value="LoginCustomer" />
                    <input type="text" placeholder="Username" name="username">
                    <input type="text" placeholder="Password" name="password">
                    <button type="submit">Login</button>
                </form>
            </div>
        </c:otherwise>
    </c:choose>

</div>
