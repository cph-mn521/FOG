/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.entities.dto.Employee;
import com.entities.dto.User;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.LoginException;
import com.google.gson.Gson;
import java.io.IOException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Martin
 */
public class Login extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException, DataException {
        String Username = request.getParameter("username");
        String Password = request.getParameter("password");
        HttpSession session = request.getSession();

        PresentationController PC = new PresentationController(DBURL.PRODUCTION);
        try {
            Employee emp = PC.LoginEmploye(Username, Password, request);
            try {
                response.getWriter().write(emp.toString());
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        } catch (DataException e) {
            try {
                response.getWriter().write("User Not Found!");
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return "woo";
    }
}
