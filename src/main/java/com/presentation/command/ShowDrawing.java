package com.presentation.command;

import com.exceptions.DataException;
import com.exceptions.PresentationException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author martin bøgh
 */
public class ShowDrawing extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, PresentationException
    {
//        PresentationController fc = new PresentationController(DBURL.PRODUCTION);
//        HttpSession session = request.getSession();
        try {
//            int compID= Integer.parseInt((String) request.getParameter("componentID"));
//            if (compID > 0)
//            {
//                session.setAttribute("component", fc.getComponent(compID));
//            }

            request.getRequestDispatcher("WEB-INF/jsp/showdrawing.jsp").include(request, response);

        } catch (NumberFormatException | IOException ex) {
            throw new PresentationException("kunne ikke se tegning" + ex.getMessage());
        } catch (ServletException ex) {
            throw new PresentationException("Problemer i servlet." + ex.getMessage());
        }
        return "w";
    }
}
