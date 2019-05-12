package com.presentation.command;

import com.entities.dto.Order;
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
public class ChangingOrder extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
            int orderID = Integer.parseInt((String) request.getParameter("orderID"));
            if (orderID > 0) {
                session.setAttribute("order", pc.getOrder(orderID));
                session.setAttribute("roofs", pc.getAllRoofs());
                session.setAttribute("carport", pc.get());
            }
            request.getRequestDispatcher("WEB-INF/jsp/changingorder.jsp").include(request, response);
        } catch (NumberFormatException | IOException ex) {
            throw new DataException("kunne ikke få komponent ID.");
        } catch (ServletException ex) {
            throw new DataException("Servlet problem. ");
        }
        return "w";
    }
}
