/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.exceptions.DataException;
import com.exceptions.LoginException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Martin
 */
public class sidebar extends Command {
    
    @Override 
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            request.getRequestDispatcher("WEB-INF/jsp/sidebar.jsp").include(request, response);
        } catch (ServletException ex) {
            Logger.getLogger(sidebar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(sidebar.class.getName()).log(Level.SEVERE, null, ex);
        }
       return "0";
    }
}
