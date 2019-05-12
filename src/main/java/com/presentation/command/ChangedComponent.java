package com.presentation.command;

import com.entities.dto.Component;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class ChangedComponent extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
            String description = (String) request.getParameter("description");
            String helpText = (String) request.getParameter("helpText");
            int width = Integer.parseInt((String) request.getParameter("width"));
            int height = Integer.parseInt((String) request.getParameter("height"));
            float price = Float.parseFloat((String) request.getParameter("price"));
            Component oldComp = (Component) session.getAttribute("component");
            Component comp = oldComp;

            if (comp != null) {

//            Change component
                if (description!= null && !description.isEmpty()) {
                    comp.setDescription(description);
                }

                if (helpText!= null && !helpText.isEmpty()) {
                    comp.setHelpText(helpText);
                }

                if (width != 0) {
                    comp.setWidth(width);
                }

                if (height != 0) {
                    comp.setHeight(height);
                }

                if (price != 0) {
                    comp.setPrice(price);
                }

//                if (deleted != null && deleted.equals("true"))
//                {
//                    session.setAttribute("component", null);
//                    pc.deleteComponent(pc.getComponent(comp.getComponentId()));
//                } else
//                {
                pc.updateComponent(oldComp, comp);
//                }
            }

//            session.setAttribute("showAllCommand", "components");

            try {
                request.getRequestDispatcher("WEB-INF/jsp/showallcomponents.jsp").include(request, response);
            } catch (ServletException ex) {
                throw new DataException("Servlet problem. " + ex.getMessage());
            } catch (IOException ex) {
                throw new DataException("kunne ikke læse komponents data. " + ex.getMessage());
            }
        } catch (NumberFormatException ex) {
            System.out.println("NumberFormatException: " + ex.getMessage());
            return "index";
        }

        return "index";
    }
}
