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
public class NewFormComponent extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        try {
            request.getRequestDispatcher("WEB-INF/jsp/newcomponent.jsp").include(request, response);

        } catch (ServletException ex) {
            throw new DataException("Servlet problem. " + ex.getMessage());
        } catch (IOException ex) {
            throw new DataException("Fejl i ansat håndtering." + ex.getMessage());
        }

        return "success";
    }
}
