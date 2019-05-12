package com.presentation.command;

import com.entities.dto.Component;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class ChangedComponents extends Command {

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
                if (!description.isEmpty()) {
                    comp.setDescription(description);
                }

                if (!helpText.isEmpty()) {
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

            session.setAttribute("components", pc.getAllComponents());
            if (comp.getComponentId() > 0) {
                session.setAttribute("component", pc.getComponent(comp.getComponentId()));

            }
              try {
                request.getRequestDispatcher("WEB-INF/jsp/showallcomponents.jsp").include(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(ChangedComponents.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ChangedComponents.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException ex) {
            throw new FormException("Der skete en fejl ved hentning af materiale");
        }

        return "success";
    }
}
