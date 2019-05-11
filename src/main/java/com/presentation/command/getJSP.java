/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.enumerations.DBURL;
import com.exceptions.DataException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Martin
 */
public class getJSP extends Command{
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response){
        
        String page =         "WEB-INF/jsp/"+request.getParameter("page")+".jsp";
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        try {
            PresentationController PC = new PresentationController(DBURL.PRODUCTION);
            //response.getWriter().write("{ \"name\":\"John\", \"age\":30, \"car\":null }");
            switch (request.getParameter("page")){
                /*case "availCases":
                    try{
                    //request.getParameter("AvailCases", PC.getAvailCases());
                    request.getRequestDispatcher("WEB-INF/jsp/availCases.jsp").include(request, response);
                    }
                    catch(DataException e){
                        request.getRequestDispatcher("WEB-INF/jsp/404.jsp").include(request, response);
                    }
                    break;*/
                default:
                    request.getRequestDispatcher("WEB-INF/jsp/"+request.getParameter("page")+".jsp").include(request, response);
                
            }
            
            request.getRequestDispatcher("WEB-INF/jsp/"+request.getParameter("page")+".jsp").include(request, response);
        } catch (ServletException | IOException | DataException ex ) {
            return "ohnoes";}
        return "0";
        
    }
}
