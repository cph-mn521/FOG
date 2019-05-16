

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
        <link rel="stylesheet" href="css/main.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.0/jquery.min.js"></script>
        <script src="js/CustomerNavigation.js"></script>
        <!--Javascript-->
        <!-- Font Awesome JS -->
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/solid.js" integrity="sha384-tzzSw1/Vo+0N5UhStP3bvwWPq+uvzCMfrN1fEFe+xBmv1C/AtVX5K0uZtmcHitFZ" crossorigin="anonymous"></script>
        <script defer src="https://use.fontawesome.com/releases/v5.0.13/js/fontawesome.js" integrity="sha384-6OIrr52G08NpOFSZdxxz1xdNSndlD4vdcf/q2myIUVO0VsqaGHJsB0RaBE01VTOY" crossorigin="anonymous"></script>
        <!--page scripts-->        <!-- JSTL -->
        <%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>


        <title>FOG-Carporte</title>
    </head>

    <body>


        <div class="wrapper">

            <div class="topnav">
                <div class="topnav-header">
                    <h3>Fog</h3>            
                </div>
                <a id = "Buy" href="#home" onclick="getCJSP(this)">Køb Karport</a>                
                <a id = "profile" href="#" style="display:none" onclick="getCJSP(this)">Min Profil</a>
                <a id = "register" href="#" onclick="getCJSP(this)">Registerer dig sig som kunde</a>
                <a id = "about" href="https://da.wikipedia.org/wiki/Johannes_Fog-Petersen" >Om Fog</a>
                <div class="login-container">
                    <form action="javascript:loginFunction()">
                        <input type="text" placeholder="Username" name="username">
                        <input type="text" placeholder="Password" name="psw">
                        <button type="submit">Login</button>
                    </form>
                </div>
            </div>



            <!-- Sidebar -->

            <!-- Page Content -->
            <div id="Customercontent">

                <h4>Welkommen til Fog Carport service</h4>
                

            </div>

            
            <!-- jQuery CDN - Slim version (=without AJAX) -->


            <script src="https://code.jquery.com/jquery-3.4.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
            <!-- Popper.JS -->
            <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js" integrity="sha384-cs/chFZiN24E4KMATLdqdvsezGxaGsi4hLGOzlXwp5UZB1LY//20VyM2taTB4QvJ" crossorigin="anonymous"></script>
            <!-- Bootstrap JS -->
            <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js" integrity="sha384-uefMccjFJAIv6A+rW+L4AHf99KvxDjWSu1z9VI8SKNVmz4sk7buKt/6v9KI65qnm" crossorigin="anonymous"></script>
            <script src="js/CustomerNavigation.js"></script>
    </body>
</html>