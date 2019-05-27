

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
                <a id = "Buy" href="#" onclick="getCJSP(this)">Køb Carport</a>       

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
                <h4>UDVALGET I FOG TRÆLAST OG BYGGECENTER</h4>
                Hos Fog Trælast & Byggecenter har vi et sortiment som spænder bredt indenfor byggematerialer, bolig, have & fritid og værktøj. Heriblandt kan nævnes varer som de flotte sibiriske terrassebrædder, økonomiske varmegenvindings ventilatorer, batteridrevne hækkeklippere og bore-/skruemaskiner. Danskerne er mestre i at gøre det selv, er det også gældende for dig? Så udforsk vores komplette varelager, vi har lige hvad du går og mangler. Gør-det-selv er ikke farligt, vær villig til at prøve det af, der er penge at spare og en stor tilfredsstillelse af at være herre i eget hus venter. Så hvis du er et havemenneske, gør-det-selv-mand eller bare har brug for noget værktøj til at fixe de praktiske gøremål, så gå på opdagelse i vores mange spændende varer på webshoppen. 
                <p class="line"></p>
                <h4>GØR-DET-SELV PROJEKTER</h4>
                Det viser sig at færre danskere selv vedligeholder eller foretager forbedringer i hjemmet. Dette er måske fordi, at det i dag ikke handler i så høj grad om besparelser længere for danskerne, men derimod mere om fornøjelse. Fornøjelsen er nemlig stor hos danskerne, når det kommer til gør-det-selv projekter. Der foretages også flere og flere bolighandler i Danmark. Er du en af boligkøberne? Og skal du i gang med et renoveringsprojekt? Så er Fog Trælast & Byggecenter stedet, hvor du finder de bedste materialer og det mest hensigtsmæssige værktøj til jobbet. Skal du ligesom omtrent halvdelen af boligkøberne bruge maling din nye bolig, så finder du hos os et stort udvalg i maling og udstyr til formålet. Det kan også være, at du ligesom knap 20% af danskerne skal have lagt nyt gulv eller loft, så hjælper vi gerne i Fog Trælast & Byggecenter til med at finde den rette løsning til dig. 
                <p class="line"></p>
                <h4>ALT TIL BOLIGEN</h4>
                Der kan være mange grunde til at renovere eller tilfører nye elementer til sin bolig. Af forskellige årsager kan nævnes at elementer i boligen er nedslidt, hjemmets rammer er ikke tilsvarende med tiden eller måske handler det for dig mere om fornyelse som hænger sammen med familiens aktuelle behov? Hvad end din årsag er, så kan du finde de rette løsninger i vores Byggecenter og Trælast. Skal der ske noget ude på badeværelset, så kan du bl.a. finde armaturer og ventilationsløsninger. Trænger dine døre til at friskes op? Så udforsk vores dørgreb, hvor vi bl.a. har flotte varianter fra Arne Jacobsen. Går du ligesom os meget op i optimal udendørsbelysning så find de rette sensorlamper i Fog Trælast og Byggecentre til din indkørsel. Synes du et pænt hjem og lyse rum er to ord for det samme, så få inspiration til at udleve dine boligdrømme i Fog Trælast og Byggecenter.
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