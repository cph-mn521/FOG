package com.presentation.command;

import com.exceptions.DataException;
import com.exceptions.LoginException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Martin Bøgh overwritten by Niels
 */
public class Logout extends Command {

    @Override
    public String execute(ServletContext context, HttpServletRequest request, HttpServletResponse response) throws LoginException, DataException {
        HttpSession session = request.getSession();
        session.invalidate();
        try {
            response.getWriter().write("success");
        } catch (IOException ex) {
            Logger.getLogger(Logout.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "getfuckedtaber";
    }
}
