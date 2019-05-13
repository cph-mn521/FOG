package com.presentation.command;

import com.exceptions.DataException;
import com.exceptions.LoginException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Martin Bøgh
 */
public class Logout extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException, DataException {
        HttpSession session = request.getSession();

        session.setAttribute("customers", null);
        session.setAttribute("customer", null);
        session.setAttribute("employees", null);
        session.setAttribute("employee", null);
        session.setAttribute("orders", null);
        session.setAttribute("order", null);
        session.setAttribute("components", null);
        session.setAttribute("component", null);
        session.setAttribute("roofs", null);
        session.setAttribute("roof", null);
        session.setAttribute("carports", null);
        session.setAttribute("carport", null);
        session.setAttribute("user", null);
        session.setAttribute("rank", null);
        return "index";
    }
}
