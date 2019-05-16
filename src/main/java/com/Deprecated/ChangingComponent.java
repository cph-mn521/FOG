package com.presentation.command;

import com.entities.dto.Component;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class ChangingComponent extends Command {

    @Override
    public String execute(ServletContext context, HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
            int compID = Integer.parseInt((String) request.getParameter("componentID"));
            if (compID > 0) {
                Component comp = pc.getComponent(compID);
                session.setAttribute("component", comp);
            }
            request.getRequestDispatcher("WEB-INF/jsp/changingcomponent.jsp").include(request, response);
        } catch (NumberFormatException | IOException ex) {
            throw new DataException("kunne ikke få komponent ID." + ex.getMessage());
        } catch (ServletException ex) {
            throw new DataException("Servlet problem. " + ex.getMessage());
        }
        return "w";
    }
}
