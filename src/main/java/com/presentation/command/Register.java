package com.presentation.command;

import com.exceptions.LoginException;
import com.entities.dto.User;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Register extends Command
{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException
    {
        String email = request.getParameter("email");
        String password1 = request.getParameter("password1");
        String password2 = request.getParameter("password2");
        if (password1.equals(password2))
        {
            // commented out beacause of imminent meeting
//            User user = FrontController.createCustomer(email, password1);
            HttpSession session = request.getSession();
//            session.setAttribute("user", user);
//            session.setAttribute("role", user.role);
            
            //Ændres til noget relevant
            return "page";
        } else
        {
            throw new LoginException("the two passwords did not match.");
        }
    }
}
