

<%@page import="com.entities.dto.Roof"%>
<%@page import="java.util.List"%>
<html>
    <head profile="http://www.w3.org/2005/10/profile">
        <link rel="icon" 
              type="image/jpg" 
              href="img/fog.jpg">
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <!--CSS-->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

        <link rel="stylesheet" href="/resources/demos/style.css">
        <link rel="stylesheet" href="css/main.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="js/CustomerNavigation.js"></script>
        <!--Javascript-->
        <!-- Font Awesome JS -->
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
        <!--page scripts-->        <!-- JSTL -->

        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
        <style>
            label {
                display: inline-block;
                width: 5em;
            }
        </style>

        <title>FOG-Carporte</title>
    </head>

    <body>


        <div class="wrapper">

            <div class="topnav">
                <div class="topnav-header">
                    <a href="index.jsp"><h3>Fog</h3></a>            
                </div>
                <a id = "Buy" href="#" onclick="getCJSP(this)">K�b Carport</a>       

                <c:choose>
                    <c:when test="${sessionScope.customer != null }">
                        <a id = "register" href="#" onclick="getCJSP(this)" style="display:none">Registerer dig sig som kunde</a>
                        <a id = "profile" href="#"  onclick="getCJSP(this)">Min Profil</a>                
                        <a id = "MyCase" href="#"  onclick="getCJSP(this)">Mine Odre.</a>
                    </c:when>
                    <c:otherwise>
                        <a id = "register" href="#" onclick="getCJSP(this)">Registerer dig sig som kunde</a>
                        <a id = "profile" href="#" style="display:none" onclick="getCJSP(this)">Min Profil</a>                
                        <a id = "MyCase" href="#" style="display:none" onclick="getCJSP(this)">Mine Odre.</a>
                    </c:otherwise>
                </c:choose>
                <a id = "about" href="https://da.wikipedia.org/wiki/Johannes_Fog-Petersen" >Om Fog</a>
                <div class="login-container">               
                    <c:choose>
                        <c:when test="${sessionScope.customer != null }">
                            <form action="javascript:loginFunction()"> 
                                <input type="text" placeholder="Username" name="username" id ="usn" style="display:none;">
                                <input type="text" placeholder="Password" name="psw" id="psw" style="display:none;">
                                <button id="logbutton" onclick="logout()" class="btn btn-info" >log out</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action="javascript:loginFunction()"> 
                                <input type="text" placeholder="Username" name="username" id ="usn">
                                <input type="text" placeholder="Password" name="psw" id="psw">
                                <button id="logbutton" onclick="btnpushin()" class="btn btn-info" >Login</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </div>
            </div>



            <!-- Sidebar -->

            <!-- Page Content -->
            <div id="Customercontent" >
                <img src="img/index.jpg" width="100%" height="80%">
                <p class="line"></p>
                <h4>Welkommen til Fog Carport service</h4>
                <p class="line"></p>
                <h4>UDVALGET I FOG TR�LAST OG BYGGECENTER</h4>
                Hos Fog Tr�last & Byggecenter har vi et sortiment som sp�nder bredt indenfor byggematerialer, bolig, have & fritid og v�rkt�j. Heriblandt kan n�vnes varer som de flotte sibiriske terrassebr�dder, �konomiske varmegenvindings ventilatorer, batteridrevne h�kkeklippere og bore-/skruemaskiner. Danskerne er mestre i at g�re det selv, er det ogs� g�ldende for dig? S� udforsk vores komplette varelager, vi har lige hvad du g�r og mangler. G�r-det-selv er ikke farligt, v�r villig til at pr�ve det af, der er penge at spare og en stor tilfredsstillelse af at v�re herre i eget hus venter. S� hvis du er et havemenneske, g�r-det-selv-mand eller bare har brug for noget v�rkt�j til at fixe de praktiske g�rem�l, s� g� p� opdagelse i vores mange sp�ndende varer p� webshoppen. 
                <p class="line"></p>
                <h4>G�R-DET-SELV PROJEKTER</h4>
                Det viser sig at f�rre danskere selv vedligeholder eller foretager forbedringer i hjemmet. Dette er m�ske fordi, at det i dag ikke handler i s� h�j grad om besparelser l�ngere for danskerne, men derimod mere om forn�jelse. Forn�jelsen er nemlig stor hos danskerne, n�r det kommer til g�r-det-selv projekter. Der foretages ogs� flere og flere bolighandler i Danmark. Er du en af boligk�berne? Og skal du i gang med et renoveringsprojekt? S� er Fog Tr�last & Byggecenter stedet, hvor du finder de bedste materialer og det mest hensigtsm�ssige v�rkt�j til jobbet. Skal du ligesom omtrent halvdelen af boligk�berne bruge maling din nye bolig, s� finder du hos os et stort udvalg i maling og udstyr til form�let. Det kan ogs� v�re, at du ligesom knap 20% af danskerne skal have lagt nyt gulv eller loft, s� hj�lper vi gerne i Fog Tr�last & Byggecenter til med at finde den rette l�sning til dig. 
                <p class="line"></p>
                <h4>ALT TIL BOLIGEN</h4>
                Der kan v�re mange grunde til at renovere eller tilf�rer nye elementer til sin bolig. Af forskellige �rsager kan n�vnes at elementer i boligen er nedslidt, hjemmets rammer er ikke tilsvarende med tiden eller m�ske handler det for dig mere om fornyelse som h�nger sammen med familiens aktuelle behov? Hvad end din �rsag er, s� kan du finde de rette l�sninger i vores Byggecenter og Tr�last. Skal der ske noget ude p� badev�relset, s� kan du bl.a. finde armaturer og ventilationsl�sninger. Tr�nger dine d�re til at friskes op? S� udforsk vores d�rgreb, hvor vi bl.a. har flotte varianter fra Arne Jacobsen. G�r du ligesom os meget op i optimal udend�rsbelysning s� find de rette sensorlamper i Fog Tr�last og Byggecentre til din indk�rsel. Synes du et p�nt hjem og lyse rum er to ord for det samme, s� f� inspiration til at udleve dine boligdr�mme i Fog Tr�last og Byggecenter.
                <br>
                <br>
            </div>

        </div>
        <!-- jQuery CDN - Slim version (=without AJAX) -->

        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <!-- Popper.JS -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
        <!-- Bootstrap JS -->
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
        <script src="js/CustomerNavigation.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </body>
</html>