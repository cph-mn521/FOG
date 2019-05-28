package com.presentation.command;

import com.entities.dto.Component;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.PresentationException;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class ComponentCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, PresentationException {
        response.setContentType("text/plain;charset=UTF-8");  // Set content type of the response so that jQuery knows what it can expect.

        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        String commandType = (String) request.getParameter("commandType");
        String page = null;

        switch (commandType) {
            case "show":
                page = "showallcomponents";
                showComponents(pc, session, request);
                break;

            case "prepare":
                page = "changingcomponent";
                prepareComponent(pc, session, request);
                break;

            case "changed":
                page = "showallcomponents";
                changedComponent(pc, session, request);
                break;

            case "newform":
                page = "newcomponent";
                prepareFormComponent(pc, session, request);
                break;

            case "newfinished":
                page = "showallcomponents";
                newComponent(pc, session, request);
                break;

            case "remove":
                page = "showallcomponents";
                removeComponent(pc, session, request);
                break;

            default:
                page = "index";

        }

        try {
            if (page.equals("index")) {
                return "index";
            } else {
                request.getRequestDispatcher("WEB-INF/jsp/" + page + ".jsp").include(request, response);
            }
        } catch (IOException ex) {
            throw new PresentationException("Problemer med at hente data.");
        } catch (ServletException ex) {
            throw new PresentationException("Servlet problem.");
        }
        return "succes!";
    }

    /**
     * 
     * @param pc
     * @param session
     * @param request
     * @throws DataException 
     */
    public void showComponents(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws DataException {
        List<Component> components = pc.getAllComponents();
        session.setAttribute("components", components);
    }

    /**
     * 
     * @param pc
     * @param session
     * @param request
     * @throws DataException
     * @throws PresentationException 
     */
    public void prepareComponent(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws DataException, PresentationException {
        try {
            int compID = Integer.parseInt((String) request.getParameter("componentID"));
            if (compID > 0) {
                Component comp = pc.getComponent(compID);
                session.setAttribute("component", comp);
            }
        } catch (NumberFormatException ex) {
            throw new PresentationException("kunne ikke læse materiales ID nummer.");
        }
    }

    /**
     * 
     * @param pc
     * @param session
     * @param request
     * @throws DataException 
     */
    public void changedComponent(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws DataException, PresentationException {
        try {
            String description = (String) request.getParameter("description");
            String helpText = (String) request.getParameter("helpText");
            int width = Integer.parseInt((String) request.getParameter("width"));
            int height = Integer.parseInt((String) request.getParameter("height"));
            int length = Integer.parseInt((String) request.getParameter("length"));
            float price = Float.parseFloat((String) request.getParameter("price"));
            Component oComp = (Component) session.getAttribute("component");
            Component newComponent = new Component(oComp.getComponentId(), oComp.getDescription(),
                    oComp.getHelpText(), oComp.getHeight(), oComp.getWidth(), oComp.getLength(), oComp.getPrice());

            if (newComponent != null) {

//            Changing component
                if (description != null && !description.isEmpty()) {
                    newComponent.setDescription(description);
                }

                if (helpText != null && !helpText.isEmpty()) {
                    newComponent.setHelpText(helpText);
                }

                if (length != 0) {
                    newComponent.setLength(length);
                }

                if (width != 0) {
                    newComponent.setWidth(width);
                }

                if (height != 0) {
                    newComponent.setHeight(height);
                }

                if (price != 0) {
                    newComponent.setPrice(price);
                }
                pc.updateComponent(oComp, newComponent);
            }

            session.setAttribute("components", pc.getAllComponents());

        } catch (NumberFormatException ex) {
            throw new PresentationException("Der skal stå noget i alle felter. ");
        }
    }

    /**
     * 
     * @param pc
     * @param session
     * @param request 
     */
    public void prepareFormComponent(PresentationController pc,
            HttpSession session, HttpServletRequest request){
    }

    /**
     * 
     * @param pc
     * @param session
     * @param request
     * @throws DataException
     * @throws PresentationException 
     */
    public void newComponent(PresentationController pc,
            HttpSession session, HttpServletRequest request) throws DataException, PresentationException {
        String description = (String) request.getParameter("description");
        String helpText = (String) request.getParameter("helpText");
        int length = Integer.parseInt((String) request.getParameter("length"));
        int width = Integer.parseInt((String) request.getParameter("width"));
        int height = Integer.parseInt((String) request.getParameter("height"));
        float price = Float.parseFloat((String) request.getParameter("price"));

        if (description != null && !description.isEmpty()
                && helpText != null && !helpText.isEmpty()
                && length >= 0
                && width >= 0
                && height >= 0
                && price >= 0.0) {
            pc.createComponent(new Component(description, helpText, length, width, height, price));
        } else {
            throw new PresentationException("Der skal stå noget i alle felter. ");
        }

        session.setAttribute("components", pc.getAllComponents
        ());
    }

    /**
     * 
     * @param pc
     * @param session
     * @param request
     * @throws DataException
     * @throws PresentationException 
     */
    public void removeComponent(PresentationController pc,
            HttpSession session, HttpServletRequest request) throws DataException, PresentationException {
        try {
            int componentID = Integer.parseInt((String) request.getParameter("componentID"));

            if (componentID > 0) {
                pc.deleteComponent(pc.getComponent(componentID));
            } else {
                throw new PresentationException("Der skal stå noget i alle felter. ");
            }
        } catch (NumberFormatException ex) {
            throw new PresentationException("kunne ikke læse materiales ID nummer.");
        }
        session.setAttribute("components", pc.getAllComponents());
    }
}
