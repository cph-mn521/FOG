package com.presentation.command;

import com.exceptions.LoginException;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
//        User user = FrontController.login(email, password);
        HttpSession session = request.getSession();
//        session.setAttribute("user", user);
//        session.setAttribute("role", "customer");
        session.setAttribute("orderID", null);
        session.setAttribute("bomMap", null);
        
//        return user.role + "page";
        return "index";
    }
}
