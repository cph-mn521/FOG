<%-- 
    Document   : svg
    Created on : 10-May-2019, 23:00:09
    Author     : nille
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% //GIV MIG ET SESSIONSOBJEKT MED EN CARPORT 
    
%>


<div id="content">
    
    <%         
    //Measurements
    int width = 450;
    int length = 250;
    int height = 350;
    int roofHeight = 60;
    
    
    //Column Measurements
    int columnWidth = 25;
    int columnHeight = (int) (height*1.25);
    
    //Earth greying variables
    int earthAngle = 45;
    int columnDepth = columnHeight/4;
    int greyDist = 5;
    
    //Shed measurements
    boolean shed = false;
    int shedHeight = height;
    int shedWidth = 150;
    int doorWidth = 50;
    int doorHeight = 100;   
    boolean shedLeft = false;
    
    
    int overhang = 20;
    //Angles
    double roofLengthA = roofHeight;
    double roofLengthB = width/2;
    double roofLengthC = java.lang.Math.sqrt(java.lang.Math.pow(roofLengthB, 2)+java.lang.Math.pow(roofHeight,2));
    double roofAngleA = java.lang.Math.toDegrees(java.lang.Math.asin(roofLengthA/roofLengthC));
    double roofAngleB = java.lang.Math.toDegrees(java.lang.Math.asin(roofLengthB/roofLengthC));
    double roofAngleC = 90;
    double roofOffset =  overhang * java.lang.Math.cos(java.lang.Math.toRadians(roofAngleA));
    
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
    String roofTopText = "Tagryg";
    String roofSideText = "sidelængde";
    String roofWidthText = "Tagbredde";
    String roofHalfText = "LilleTagMål";
    String roofAngleAText= "TagVinkelA";
    String roofAngleBText = "TagVinkelB";
    String roofAngleCText = "TagVinkelC";
    String roofSideCText = "TagSideC";
    String roofHeightText = "TagHøjde";
    String roofBottomText = "Tagbund";
    String roofOverhangText = "overshoot";
    
    String columnSideText = "TotalSøjleside";
    String columnBottomText = "Søjlebund";
    String columnDepthText = "SøjleUnderJord";
    String columnOverText = "SøjleOverJord";
    String columnBreddeText ="Søjlebredde";
    
    String earthSurfaceText = "Jordorverfladen";
    
    String focusLabelA = "Figur A";
    
    char deg = 176;
    
    // RGB colors of components
    String roofFillColour = "(255,255,255)";
    
    %>
    
        <!-- Generate carport top down view -->
        <div id="svg">
            <h1>Top Down</h1>
            <svg width = "<%=width+(textOffset)*2%>" height ="<%=length+textOffset*2%>" >        
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
            <svg width="<%=width + textOffset*2%>" height="<%=columnHeight+roofHeight+textOffset*3%>">
                <svg x="<%=textOffset%>" y="<%=textOffset%>" width="<%=width%>" height="<%=textOffset+columnHeight+roofHeight%>" >
                    <!-- Draws the roof -->
                    <polygon points="<%=(width)/2%> 0, 0,<%=roofHeight%>, <%=width%>,<%=roofHeight%>" 
                             style="fill:rgb(255,255,255);stroke-width:<%=squareLineWidth%>;stroke:rgb(0,0,0)" />                   
                    
                    <!-- Draws the earth dashes -->
                    <%
                        int x = 0;
                        double xOffset = columnDepth/java.lang.Math.tan(java.lang.Math.toRadians(earthAngle));
                    while((x-xOffset)<width){
                    if(x%greyDist==0){    
                    %>                       
                        <line x1="<%=x%>" y1="<%=roofHeight+columnHeight-columnDepth%>" x2="<%=x-xOffset%>" y2="100%" 
                              style="stroke:rgb(200,200,200);stroke-width:<%=lineWidth%>;stroke-dasharray:3"  />                        
                        <%}
                            x++;
                    }
                    %>
                                        
                    <!-- Draws the columns of the carport -->
                   <rect x="<%=roofOffset%>" y="<%=roofHeight%>" width ="<%=columnWidth%>" height="<%=columnHeight%>" 
                          style="fill:white;stroke-width:<%=squareLineWidth%>;stroke:rgb(0,0,0)" />   
                  
                    <rect x="<%=width-columnWidth-roofOffset%>" y="<%=roofHeight%>" width ="<%=columnWidth%>" height="<%=columnHeight%>"
                          style="fill:white;stroke-width:<%=squareLineWidth%>;stroke:rgb(0,0,0)" />   

                    <!-- Draws the earth level -->
                    <line x1="0%" y1="<%=roofHeight+columnHeight-columnDepth%>" x2="100%" y2="<%=roofHeight+columnHeight-columnDepth%>"
                          style="stroke:rgb(0,0,0);stroke-width:<%=lineWidth%>;stroke-dasharray:4"/>
                    
                    <!-- Adds text to the earth surface -->
                    <text x="<%=(width-earthSurfaceText.length()*fontWidth)/2%>" y="<%=roofHeight+columnHeight-columnDepth-textDepth%>"> <%=earthSurfaceText%></text>
                                        
                    <!-- Adds text to columns -->
                    <text x="<%=roofOffset+columnWidth+textDepth%>" y="<%=roofHeight+(columnHeight-columnSideText.length()*fontWidth)/2%>"
                          transform="rotate(90,<%=roofOffset+columnWidth+textDepth%>,<%=roofHeight+(columnHeight-columnSideText.length()*fontWidth)/2%>)"> <%=columnSideText%></text> 
                    
                    <%
                        double xn = width-roofOffset+textDepth;
                        double yt = roofHeight-textOffset+(columnHeight-columnDepth-columnOverText.length()*fontWidth)/2;
                        double yb = roofHeight-textOffset+height+(columnDepth-columnDepthText.length()*fontWidth)/2;
                        %>
                       
                    <text x="<%=xn%>" y="<%=yt%>" transform="rotate(90, <%=xn%>, <%=yt%>)"> <%=columnOverText%></text>
                    
                    <text x="<%=xn%>" y="<%=yb%>" transform="rotate(90, <%=xn%>, <%=yb%>)"><%=columnDepthText%></text>
                          
                    <text y="<%=(roofHeight/2)+textHeight-textDepth%>"
                          x="<%=((roofLengthC/2)*java.lang.Math.cos(roofAngleA))-(roofSideCText.length()*fontWidth)/2%>"
                          transform="rotate(<%=-roofAngleA%>,
                          <%=((roofLengthC/2)*java.lang.Math.cos(roofAngleA))-(roofSideCText.length()*fontWidth)/2%>,
                          <%=(roofHeight/2)%>)"
                          ><%=roofSideCText%></text>
                    
                    <!-- Adds text between columns -->
                    <text x="<%=(width-roofBottomText.length()*fontWidth)/2%>" y="<%=roofHeight+textHeight%>"><%=roofBottomText%></text>
                                       
                    <!-- Adds text to the roof width -->
                    <text x="<%=(width-roofBottomText.length()*fontWidth)/2%>" y="<%=roofHeight-textDepth-squareLineWidth%>"><%=roofWidthText%></text>
                    
                    <!-- Adds text to angle A of the roof -->
                    <text x="<%=width-roofAngleCText.length()*fontWidth%>" y="<%=roofHeight-textDepth%>"
                          transform="rotate(<%=roofAngleA%>,<%=width%>,<%=roofHeight%>)"
                          ><%=roofAngleCText%></text>
                    
                    <!-- Draws the shed -->
                    <%if(shed){ %>
                       
                    <%}%>
                </svg>
                                
                <!-- Draws focus circle -->
                <circle r="<%=columnWidth/2+2*roofOffset%>" cx="<%=textOffset + width - roofOffset - columnWidth/2%>" cy="<%=roofHeight+textOffset%>"
                        style="stroke-width:<%=squareLineWidth%>;stroke:rgb(0,0,0);fill-opacity:0.0;stroke-opacity:0.5;" />
                
                <!-- Adds text to focus circle -->
                <text x="<%=textOffset+width-roofOffset-(columnWidth+focusLabelA.length()*fontWidth)/2%>" y="<%=roofHeight+textOffset-textDepth-(columnWidth/2+2*roofOffset)%>"><%=focusLabelA%></text>
                
                <!-- Adds text to Angle C of the roof -->
                <text x="<%=(width+textOffset+roofOffset-roofAngleBText.length()*fontWidth)/2%>" y="<%=textHeight%>"><%=roofAngleBText%></text>
            </svg>
        </div>
            
        <div id="svg">
            <h1><%=focusLabelA%></h1>
            <svg width="<%=2*textOffset+500%>" height="<%=2*textOffset+500%>">          
                <svg x="<%=textOffset%>" y="<%=textOffset%>" height="<%=500+squareLineWidth%>" width="<%=500+squareLineWidth%>">
                   
                    <% 
                     double oldR = columnWidth/2+2*roofOffset;
                     double newR = 250;
                     double norm = newR/oldR;
                     
                     double columnWidthNorm = columnWidth*norm;
                     double roofOffsetNorm = roofOffset*norm;
                     double pointAx = 2*newR-roofOffsetNorm;
                     double pointAy = newR;
                     double pointBx = -10;
                     double pointBy = newR-(2*newR*java.lang.Math.tan(java.lang.Math.toRadians(roofAngleA)));
                     double pointCx = -10;
                     double pointCy = newR;

                    %>    
                    
                
                    <!-- Draws the snippet of the roof -->
                    <polygon points="<%=pointAx%> <%=pointAy%>, <%=pointBx%> <%=pointBy%>, <%=pointCx%> <%=pointCy%>"
                             style="fill:white;stroke-width:<%=squareLineWidth%>;stroke:rgb(0,0,0);"/>
                    
                    <!-- Draws the snippet of the column -->
                    <rect x=""/>
                    
                    
                      <!-- Draws the circle -->
                    <circle r="250" cx="50%" cy="50%" style="stroke-width:<%=squareLineWidth%>;stroke:rgb(0,0,0);fill:white;fill-opacity:0.0;"/>
                     
                     
                </svg>
            </svg>
            
                
        </div>
        
    
</div>