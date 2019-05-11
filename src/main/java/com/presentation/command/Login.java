/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.entities.dto.Employee;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.LoginException;
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
        String[] LogInfo = PC.LoginEmploye(Username, Password);
        System.out.println("breakline");
        if (LogInfo[2].equals("1")) {
            response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            try {
                response.getWriter().write("noLogins");
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            session.setAttribute("user", LogInfo[0]);
            session.setAttribute("Active_Cases", LogInfo[1]);
            session.setAttribute("user", "somethin");
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            try {
                response.getWriter().write(LogInfo[0]);
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return "woo";
    }
}
