package com.presentation.command;

import com.entities.dto.Case;
import com.entities.dto.Customer;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.LoginException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The purpose of Login is to...
 *
 * @author martin bøgh
 */
public class LoginCustomer extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException, DataException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);

        HttpSession session = request.getSession();
//        session.setAttribute("user", user);

        try {
//preparing objects for session
            Customer C = pc.getCustomer(username, password);
            session.setAttribute("customer",C);
           // List<Case> cases;            
            response.getWriter().write(C.toString());

        } catch (IOException | DataException ex) {
            Logger.getLogger(LoginCustomer.class.getName()).log(Level.SEVERE, null, ex);
            throw new DataException("Kunne ikke finde komponenter nødvendige for at klargøre siden (id:lc1)");            
        }
        return "succes";
    }
}
