package com.presentation.command;

import com.exceptions.DataException;
import com.exceptions.FormException;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author martin bøgh
 */
public class NewFormCustomer extends Command {

    @Override
    public String execute(ServletContext context, HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        try {
            request.getRequestDispatcher("WEB-INF/jsp/newcustomer.jsp").include(request, response);

        } catch (ServletException ex) {
            throw new DataException("Servlet problem. " + ex.getMessage());
        } catch (IOException ex) {
            throw new DataException("Fejl i kunde håndtering." + ex.getMessage());
        }

        return "success";
    }
}
