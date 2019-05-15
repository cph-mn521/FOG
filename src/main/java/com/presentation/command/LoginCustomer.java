package com.presentation.command;

import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.LoginException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The purpose of Login is to...
 *
 * @author martin bøgh
 */
public class LoginCustomer extends Command
{

    @Override
    public String execute(ServletContext context, HttpServletRequest request, HttpServletResponse response) throws LoginException, DataException
    {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);

        HttpSession session = request.getSession();
//        session.setAttribute("user", user);

        try
        {
//preparing objects for session
            session.setAttribute("orderID", null);
            session.setAttribute("bomMap", null);
            session.setAttribute("user", username);
            session.setAttribute("orders", pc.getAllOrders());
        } catch (DataException ex)
        {
            throw new DataException("Kunne ikke finde komponenter nødvendige for at klargøre siden (id:lc1)");
        }

//        return user.role + "page";
        return "index";
    }
}
