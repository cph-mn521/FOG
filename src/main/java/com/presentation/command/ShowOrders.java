package com.presentation.command;

import com.entities.dto.Order;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.LoginException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author niller, martin bøgh
 */
public class ShowOrders extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException, DataException {
        response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
        response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        
        try {
            List<Order> listy = pc.getAllOrders();
            session.setAttribute("orders", listy);
            request.getRequestDispatcher("WEB-INF/fragments/showorderhistory.jspf").include(request, response);
        } catch (IOException ex) {
            return "ohnoes";
        } catch (ServletException ex) {
            Logger.getLogger(JSTEST.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "succes!";
    }
}
