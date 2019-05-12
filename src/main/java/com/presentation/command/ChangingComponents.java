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
public class ChangingComponents extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
            int compID = Integer.parseInt((String) request.getParameter("componentID"));
            if (compID > 0) {
                Component comp = pc.getComponent(compID);
                session.setAttribute("component", comp);
            }
            request.getRequestDispatcher("WEB-INF/jsp/changingcomponents.jsp").include(request, response);
        } catch (NumberFormatException | IOException ex) {
            throw new DataException("kunne ikke få komponent ID." + ex.getMessage());
        } catch (ServletException ex) {
            Logger.getLogger(ShowOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "w";
    }
}