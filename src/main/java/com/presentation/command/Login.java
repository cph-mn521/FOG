package com.presentation.command;

import com.enteties.dto.User;
import com.exceptions.LoginException;
import com.presentation.frontcontrol.FrontController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The purpose of Login is to...
 *
 * @author kasper
 */
public class Login extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = FrontController.login(email, password);
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        session.setAttribute("role", user.role);
        return user.role + "page";
    }
}
