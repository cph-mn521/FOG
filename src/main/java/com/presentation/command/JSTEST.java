package com.presentation.command;

import com.exceptions.LoginException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JSTEST extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {
        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        try {
            //response.getWriter().write("woo");
                    
            request.getRequestDispatcher("WEB-INF/fragments/content_1.jspf").include(request, response);
        } catch (IOException ex) {
            return "ohnoes";
        } catch (ServletException ex) {
            Logger.getLogger(JSTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "succes!";
    }

}
