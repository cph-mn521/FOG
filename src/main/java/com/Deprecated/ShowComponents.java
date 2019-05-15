package com.presentation.command;

import com.entities.dto.Component;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.LoginException;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class ShowComponents extends Command {

    @Override
    public String execute(ServletContext context, HttpServletRequest request, HttpServletResponse response) throws LoginException, DataException {
        response.setContentType("text/plain;charset=UTF-8");  // Set content type of the response so that jQuery knows what it can expect.
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();

        try {
            List<Component> components = pc.getAllComponents();
            session.setAttribute("components", components);
            request.getRequestDispatcher("WEB-INF/jsp/showallcomponents.jsp").include(request, response);
        } catch (IOException ex) {
            return "ohnoes";
        } catch (ServletException ex) {
            throw new DataException("Servlet problem.");
        }
        return "succes!";
    }
}
