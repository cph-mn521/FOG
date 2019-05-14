<%-- 
    Document   : svg
    Created on : 10-May-2019, 23:00:09
    Author     : Niels
--%>
<%@page import="com.entities.dto.Roof"%>
<%@page import="com.entities.dto.Carport"%>
<%@page import="java.lang.Math"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>



<%

          
Carport cp = (Carport) session.getAttribute("carport");
Roof rf = (Roof) session.getAttribute("roof");
    

%>



<div id="content">
    <!-- Measurements, variables and Strings -->
    <%         
    //Measurements    
    double width = cp.getWidth()/10;
    double length = cp.getLength()/10;
    double height = cp.getHeight()/10;
    //int roofHeight = 90;
    
    
    
    //Column Measurements
    double columnWidth = 25;
    double columnHeight = (int) (height*1.25);
    
    //Earth greying variables
    int earthAngle = 45;
    double columnDepth = columnHeight/4;
    int greyDist = 5;
    
    //Shed measurements
    boolean shed = false;
    double shedHeight = height;
    double shedWidth = 150;
    double doorWidth = 50;
    double doorHeight = 100;   
    boolean shedLeft = false;
    
    
    double overhang = 20;
    //Angles
    double roofAngleA = rf.getSlant();
    double roofLengthB = width/2;
    double roofHeight = roofLengthB*Math.tan(Math.toRadians(roofAngleA));
    
    double roofLengthA = roofHeight;
    double roofLengthC = Math.sqrt(Math.pow(roofLengthB, 2)+Math.pow(roofHeight,2));
    double roofAngleB = Math.toDegrees(Math.asin(roofLengthB/roofLengthC));
    double roofAngleC = 90;
    double roofOffset =  overhang * Math.cos(Math.toRadians(roofAngleA));
    
    //Focus dimensions
    double circleWidth = 500;
    double circleHeight = 500;
    
   
    
    
    //Textformatting
    int textHeight = 13; //height of characters
    int textDepth = 5; // depth of characters (under the line)   
    int textOffset = textHeight+textDepth; //total textable area.
    int fontWidth = 10; //width of characters
    
    int lineWidth = 1;
    int squareLineWidth = 2;
    
    //Data to entered on drawings
    double xt, yt;
    char deg = 176;   
    
    String roofTopText = String.format("%.2f %s",length+roofOffset*2,"cm.");
    String roofSideText = String.format("%.2f %s",(length+roofOffset*2),"cm.");
    String roofWidthText = String.format("%.2f %s",(width+roofOffset*2),"cm.");
    String roofLengthText = String.format("%.2f %s",(length+roofOffset*2),"cm.");
    String roofHalfText = String.format("%.2f %s",(roofLengthB+overhang),"cm.");
    String roofAngleAText= String.format("%.2f%s",roofAngleA,deg);
    String roofAngleBText = String.format("%.2f%s",roofAngleB,deg);
    String roofAngleCText = String.format("%.2f%s",roofAngleC,deg);
    String roofSideCText = String.format("%.2f %s",roofLengthC,"cm.");
    String roofHeightText = String.format("%.2f %s",roofHeight,"cm.");
    String roofBottomText = String.format("%.2f %s",(width-columnWidth*2),"cm.");
    String roofBottomLengthText = String.format("%.2f %s",(length-columnWidth*2),"cm.");
    String roofOverhangText = String.format("%.2f %s",roofOffset,"cm.");
    
    String columnSideText = String.format("%.2f %s",columnHeight,"cm.");
    String columnDepthText = String.format("%.2f %s",columnDepth,"cm.");
    String columnOverText = String.format("%.2f %s", height,"cm.");
    String columnWidthText = String.format("%.2f %s",columnWidth,"cm.");
    
    String carportTotalHeight = String.format("%.2f %s",(height+roofHeight),"cm.");
    
    String earthSurfaceText = "Jordoverfladen";
    
    String focusLabelA = "Fig. A";
    
    
    
    %>
    
        <!-- Generate carport top down view -->   
        <div id="svg">
            <h1>Top Down</h1>
            <svg width = "<%=width+(textOffset)*2%>" height ="<%=length+textOffset*2%>" id="TopDown" >        
                <svg width ="<%=width%>" height = "<%=length%>" x="<%=textOffset%>" y="<%=textOffset%>" >
                    
                    <!--Draws the outline of the roof -->
                    <rect width="100%" height="100%" 
                          style="fill:rgb(255,255,255);stroke-width:<%=squareLineWidth%>;stroke:rgb(0,0,0)" />
                    
                    <!-- Draws the top line of the roof -->
                    <% if(roofAngleA>0){%>
                    <line x1="50%" y1="0" x2="50%" y2="100%" style="stroke:rgb(0,0,0);stroke-width:<%=lineWidth%>;"/>
                    
                    <!-- Adds text to the top line of the roof -->
                    <text x="<%=(width/2)+textDepth%>" y="<%=(length-(roofTopText.length()*fontWidth))/2%>" 
                           transform="rotate(90,<%=((width)/2)+textDepth%>,<%=(length-roofTopText.length()*fontWidth)/2%>)" ><%=roofTopText%></text>
                    <%}%>
                                       
                    <!-- Adds text to the sidelength of the roof -->
                    <text y="<%=textHeight+squareLineWidth%>" x="<%=(width*0.75)-(roofHalfText.length()*fontWidth)/2%>">
                    <%=roofHalfText%></text>
                    
                    </svg>
                    <!-- Adds text to the bottom/front of the roof -->
                    <text x="<%=((width+(textOffset*2)-roofWidthText.length()*fontWidth)/2)%>" y ="<%=length+textHeight+textOffset%>">
                    <%=roofWidthText%></text>
                    
                    <!-- Adds text to the side of the roof -->
                   <text x="<%=textOffset+textDepth+width%>" y="<%=(textOffset*2+length-roofSideText.length()*fontWidth)/2%>" 
                          transform="rotate(90, <%=textOffset+textDepth+width%>,<%=(textOffset*2+length-roofSideText.length()*fontWidth)/2%>)"><%=roofSideText%></text> 
                   
            </svg>
        </div>
        
        <!-- Generate carport front view -->
        <div id="svg">
            <h1>Front</h1>
            <svg width="<%=width + textOffset*2%>" height="<%=columnHeight+roofHeight+textOffset*3%>" id="Front">
                <svg x="<%=textOffset%>" y="<%=textOffset%>" width="<%=width%>" height="<%=textOffset+columnHeight+roofHeight%>" >
                    <!-- Draws the roof -->
                    <polygon points="<%=(width)/2%> 0, 0,<%=roofHeight%>, <%=width%>,<%=roofHeight%>"/>                   
                    
                    <!-- Draws the earth dashes -->
                    <%
                        int x = 0;
                        double xOffset = columnDepth/Math.tan(Math.toRadians(earthAngle));
                    while((x-xOffset)<width){
                    if(x%greyDist==0){    
                    %>                       
                        <line x1="<%=x%>" y1="<%=roofHeight+columnHeight-columnDepth%>" x2="<%=x-xOffset%>" y2="100%" 
                              style="stroke:rgb(200,200,200);stroke-dasharray:3"  />                        
                        <%}
                            x++;
                    }
                    %>
                                        
                    <!-- Draws the columns of the carport -->
                   <rect x="<%=roofOffset%>" y="<%=roofHeight%>" width ="<%=columnWidth%>" height="<%=columnHeight%>"/>   
                  
                    <rect x="<%=width-columnWidth-roofOffset%>" y="<%=roofHeight%>" width ="<%=columnWidth%>" height="<%=columnHeight%>"/>   

                    <!-- Draws the earth level -->
                    <line x1="0%" y1="<%=roofHeight+columnHeight-columnDepth%>" x2="100%" y2="<%=roofHeight+columnHeight-columnDepth%>"
                          style="stroke-dasharray:4"/>
                    
                    <!-- Adds text to the earth surface -->
                    <text x="<%=(width-earthSurfaceText.length()*fontWidth)/2%>" y="<%=roofHeight+columnHeight-columnDepth-textDepth%>"> <%=earthSurfaceText%></text>
                                        
                    <!-- Adds text to columns -->
                    <text x="<%=roofOffset+columnWidth+textDepth%>" y="<%=roofHeight+(columnHeight-columnSideText.length()*fontWidth)/2%>"
                          transform="rotate(90,<%=roofOffset+columnWidth+textDepth%>,<%=roofHeight+(columnHeight-columnSideText.length()*fontWidth)/2%>)"> <%=columnSideText%></text> 
                    
                    <!-- Variables for text -->
                    <%
                        double xn = width-roofOffset+textDepth;
                        yt = roofHeight-textOffset+(columnHeight-columnDepth-columnOverText.length()*fontWidth)/2;
                        double yb = roofHeight-textOffset+height+(columnDepth-columnDepthText.length()*fontWidth)/2;
                        %>
                       
                    <text x="<%=xn%>" y="<%=yt%>" transform="rotate(90, <%=xn%>, <%=yt%>)"> <%=columnOverText%></text>
                    
                    <text x="<%=xn%>" y="<%=yb%>" transform="rotate(90, <%=xn%>, <%=yb%>)"><%=columnDepthText%></text>
                          
                    <%
                        xt = 0.5*roofLengthC;
                        yt = roofHeight/2-textDepth-squareLineWidth;
                    %>
                    
                    <text x="<%=xt%>" y="<%=yt%>" text-anchor="middle" transform="rotate(<%=-roofAngleA%>,<%=xt%>,<%=yt%>)" > <%=roofSideCText%></text>
                    
                    <!-- Adds text between columns -->
                    <text x="<%=(width-roofBottomText.length()*fontWidth)/2%>" y="<%=roofHeight+textHeight%>"><%=roofBottomText%></text>
                                       
                    <!-- Adds text to the roof width -->
                    <text x="<%=(width-roofWidthText.length()*fontWidth)/2%>" y="<%=roofHeight-textDepth-squareLineWidth%>"><%=roofWidthText%></text>
                    
                    <!-- Adds text to angle A of the roof -->
                    <%
                        xt = width;
                        yt = roofHeight-textDepth;
                    %>
                    
                    <text x="<%=xt%>" y="<%=yt%>" text-anchor="end" 
                          transform="rotate(<%=roofAngleA%>,<%=xt%>,<%=yt%>)"
                          ><%=roofAngleAText%></text>
                    
                    <!-- Draws the shed -->
                    <%if(shed){ %>
                       
                    <%}%>
                </svg>
                                
                <!-- Draws focus circle -->
                <circle r="<%=columnWidth/2+2*roofOffset%>" cx="<%=textOffset + width - roofOffset - columnWidth/2%>" cy="<%=roofHeight+textOffset%>"/>
                
                <!-- Adds text to focus circle -->
                <text x="<%=textOffset+width-roofOffset-(columnWidth+focusLabelA.length()*fontWidth)/2%>" y="<%=roofHeight+textOffset-textDepth-(columnWidth/2+2*roofOffset)%>"><%=focusLabelA%></text>
                
                <!-- Adds text to Angle B of the roof -->
                <text x="<%=(width+textOffset+roofOffset-roofAngleBText.length()*fontWidth)/2%>" y="<%=textHeight%>"><%=roofAngleBText%></text>
            </svg>
        </div>
            
        <!-- Draws the roof corner focus -->
        <div id="svg">
            <h1><%=focusLabelA%></h1>
            <svg width="<%=2*textOffset+circleWidth%>" height="<%=2*textOffset+circleHeight%>" id="focusA">          
                <svg x="<%=textOffset%>" y="<%=textOffset%>" height="<%=circleHeight+squareLineWidth%>" width="<%=circleWidth+squareLineWidth%>">
                   
                    <% 
                        //circle variables
                    double oldR = columnWidth/2+2*roofOffset;
                    double newR = circleWidth/2;
                    double norm = newR/oldR;
                    
                     //column variables
                    double columnWidthNorm = columnWidth*norm;
                    double columnHeightNorm = newR + squareLineWidth*2;
                    
                     
                     //roof variables
                     double roofOffsetNorm = roofOffset*norm;
                     double pointAx = 2*newR-roofOffsetNorm;
                     double pointAy = newR;
                     double pointBx = -squareLineWidth*2;
                     double pointBy = newR-(squareLineWidth*2+pointAx)*Math.tan(Math.toRadians(roofAngleA));
                     double pointCx = -squareLineWidth*2;
                     double pointCy = newR;
                    
                     double columnX = roofOffsetNorm*2;
                     double columnY = newR;
                     

                    %>    
                    
                
                    <!-- Draws the snippet of the roof -->
                    <polygon points="<%=pointAx%> <%=pointAy%>, <%=pointBx%> <%=pointBy%>, <%=pointCx%> <%=pointCy%>"/>
                    
                    <!-- Draws the snippet of the column -->
                    <rect x="<%=columnX%>" y="<%=columnY%>" height="<%=columnHeightNorm%>" width="<%=columnWidthNorm%>"/>
                    
                    <!-- Draws the circle -->
                    <circle r="<%=newR%>" cx="50%" cy="50%"/>
                    
                    <%
                    double bX1 = columnX+columnWidthNorm;
                    double bY1 = newR;
                    double bX3 = bX1 + roofOffsetNorm-(roofOffsetNorm*Math.cos(Math.toRadians(roofAngleA)));
                    double bY3 = newR-(roofOffsetNorm*Math.sin(Math.toRadians(roofAngleA)));

                    %>
                    <!-- Draws the angle curve -->
                    <path d="M <%=bX1%> <%=bY1%> A <%=roofOffsetNorm%> <%=roofOffsetNorm%> 0 0 1 <%=bX3%> <%=bY3%>"/>
                    
                    <!-- Adds text to angle A -->
                    <% double rtx = bX1+roofOffsetNorm-(roofOffsetNorm*Math.cos(Math.toRadians(0.5*roofAngleA)));
                       double rty = squareLineWidth*2+newR-(roofOffsetNorm*Math.sin(Math.toRadians(0.5*roofAngleA))); %>
                    <text x="<%=rtx-((roofAngleAText).length()*fontWidth)%>"
                          y="<%=rty%>" transform="rotate(<%=0.5*roofAngleA%>,<%=rtx%>,<%=rty%>)">
                    <%=roofAngleAText%> </text>
                    
                    <!-- Adds text to the column -->
                    <text x="<%=columnX+(columnWidthNorm-columnWidthText.length()*fontWidth)/2%>" y="<%=newR+columnHeightNorm/2%>"><%=columnWidthText%></text>
                    
                    <!-- Adds text to the roof overshoot -->
                    <text x="<%=columnX+columnWidthNorm+squareLineWidth%>" y="<%=newR+textHeight%>" text-anchor="start" ><%=roofOverhangText%></text>
                       
                </svg>
            </svg>
                
        </div>
        
        <!-- Draws the carport from the side -->
        <div id="svg">
            <h1>Side</h1>
            <svg width ="<%=length+textOffset*2%>" height="<%=roofHeight+columnHeight+textOffset*3%>" id="side">
                <svg x="<%=textOffset%>" y="<%=textOffset%>" height="<%=roofHeight+columnHeight+textOffset%>" width="<%=length%>">
                    
                    <!-- Draws the earth dashes -->
                    <%
                        x = 0;
                    while((x-xOffset)<length){
                    if(x%greyDist==0){    
                    %>                       
                        <line x1="<%=x%>" y1="<%=roofHeight+columnHeight-columnDepth%>" x2="<%=x-xOffset%>" y2="100%" 
                              style="stroke:rgb(200,200,200);stroke-dasharray:3"  />                        
                        <%}
                            x++;
                    }
                    %>
                
                    <!-- Draws the earth level -->
                    <line x1="0%" y1="<%=roofHeight+columnHeight-columnDepth%>" x2="100%" y2="<%=roofHeight+columnHeight-columnDepth%>"
                          style="stroke-dasharray:4"/>
                    
                     <!-- Adds text to the earth surface -->
                    <text x="<%=(length-earthSurfaceText.length()*fontWidth)/2%>" y="<%=roofHeight+columnHeight-columnDepth-textDepth%>"> <%=earthSurfaceText%></text>
 
                    <!-- Draws the roof of the carport -->
                    <rect x="0" y="0" width="<%=length%>" height="<%=roofHeight%>"/>
                    
                    <!-- Draws the columns of the carport -->
                    <rect x="<%=roofOffset%>" y="<%=roofHeight%>" height="<%=columnHeight%>" width="<%=columnWidth%>"/>
                    
                    <rect x="<%=length-roofOffset-columnWidth%>" y="<%=roofHeight%>" height="<%=columnHeight%>" width="<%=columnWidth%>"/>
                    
                    <!-- Adds text to the roof height -->
                    <text x="<%=textDepth%>" 
                          <%if((roofHeightText.length()*fontWidth) < roofHeight){%>
                          y="<%=(roofHeight-roofHeightText.length()*fontWidth)/2%>"                          
                          transform="rotate(90,<%=textDepth%>,<%=(roofHeight-roofHeightText.length()*fontWidth)/2%>)" 
                          <%} else{ %> y="<%=(roofHeight+textDepth)/2%>" <%}%> ><%=roofHeightText%></text>
                
                    <!-- Adds text between columns -->
                    <text x="<%=(length-roofBottomLengthText.length()*fontWidth)/2%>" y="<%=roofHeight+textHeight%>"><%=roofBottomLengthText%></text>
                        
                </svg>
                
                <!-- Adds text to the total Height -->
                    <text x="<%=length+textOffset%>" y="<%=textOffset+(roofHeight+height-carportTotalHeight.length()*fontWidth)/2%>" 
                          transform="rotate(90,<%=length+textOffset%>,<%=textOffset+(roofHeight+height-carportTotalHeight.length()*fontWidth)/2%>)"><%=carportTotalHeight%></text>
                    
                
                <!-- Adds text to the top of the roof -->
                <text x="<%=textOffset+(length-roofLengthText.length()*fontWidth)/2%>" y="<%=textHeight%>"><%=roofLengthText%></text>
            </svg>
        </div>                  
            
            
<a href="javascript:void(0)" onclick=function(){alert('lul');} >Download1</a>



<button id="dlbutton">download</button>

<script> document.getElementById("dlbutton").addEventListener("click", function(){alert("lul");});</script>

            
</div>
            

