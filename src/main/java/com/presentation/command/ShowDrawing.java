package com.presentation.command;

import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author martin bøgh
 */
public class ShowDrawing extends Command {

    @Override
    public String execute(ServletContext context, HttpServletRequest request, HttpServletResponse response) throws DataException, FormException
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
            throw new DataException("kunne ikke se tegning");
        } catch (ServletException ex) {
            Logger.getLogger(ShowOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "w";
    }
}
