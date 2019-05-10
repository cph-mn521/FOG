/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

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
        response.setContentType("text/http");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        try {
            //response.getWriter().write("{ \"name\":\"John\", \"age\":30, \"car\":null }");
            
            request.getRequestDispatcher("WEB-INF/jsp/"+request.getAttribute("page")+".jsp").include(request, response);
        } catch (ServletException | IOException ex ) {
            return "ohnoes";}
        return "0";
        
    }
}
