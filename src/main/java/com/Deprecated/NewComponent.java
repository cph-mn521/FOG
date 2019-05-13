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
public class NewComponent extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
            String description = (String) request.getParameter("description");
            String helpText = (String) request.getParameter("helpText");
            int length = Integer.parseInt((String) request.getParameter("length"));
            int width = Integer.parseInt((String) request.getParameter("width"));
            int height = Integer.parseInt((String) request.getParameter("height"));
            float price = Float.parseFloat((String) request.getParameter("price"));

            if (    description != null && !description.isEmpty() &&
                    helpText != null && !helpText.isEmpty() && 
                    length >= 0 && 
                    width >= 0 && 
                    height >= 0 &&
                    price >= 0.0) {
                pc.createComponent(new Component(description, helpText, length, width, height, price));
            } else {
                throw new FormException("Der skal stå noget i alle felter. ");
            }

            session.setAttribute("employees", pc.getAllEmployees());
            request.getRequestDispatcher("WEB-INF/jsp/showallemployees.jsp").include(request, response);

        } catch (ServletException ex) {
            throw new DataException("Servlet problem. " + ex.getMessage());
        } catch (IOException ex) {
            throw new DataException("Fejl i ansat håndtering." + ex.getMessage());
        } catch (NumberFormatException ex){
            throw new DataException("Der skete en fejl. Der skal tal i de sidste fire rubrikker. " + ex.getMessage());
        }

        return "index";
    }
}
