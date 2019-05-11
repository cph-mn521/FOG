package com.presentation.command;

import com.entities.dto.Customer;
import com.entities.dto.Employee;
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
public class NewOrder extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
                // OBS customer skal hentes et sted fra. 1 er placeholder
            int customerID = 1; //Integer.parseInt((String) request.getParameter("name"));
            String customerAddress = (String) request.getParameter("customerAddress");
            int roofTypeID = Integer.parseInt((String) request.getParameter("roofTypeID"));
            int cartportLength = Integer.parseInt((String) request.getParameter("cartportLength"));
            int cartportWidth = Integer.parseInt((String) request.getParameter("cartportWidth"));
            int cartportHeight = Integer.parseInt((String) request.getParameter("cartportHeight"));
            int shedLength = Integer.parseInt((String) request.getParameter("shedLength"));
            int shedWidth = Integer.parseInt((String) request.getParameter("shedWidth"));
            int shedHeight = Integer.parseInt((String) request.getParameter("shedHeight"));

            if (    customerID > 0 && 
                    customerAddress != null && !customerAddress.isEmpty() && 
                    roofTypeID > 0 && 
                    cartportLength > 0 && 
                    cartportWidth > 0 && 
                    cartportHeight > 0 && 
                    shedLength > 0 && 
                    shedWidth > 0 && 
                    shedHeight > 0) {
                
                pc.createOrder(pc.getCustomer(customerID), customerAddress, roofTypeID, 
                        cartportLength, cartportWidth, cartportHeight, 
                        shedLength, shedWidth, shedHeight);
            } else {
                throw new FormException("Der skal stå noget i alle felter. ");
            }

            session.setAttribute("orders", pc.getAllOrders());
            request.getRequestDispatcher("WEB-INF/jsp/showallorders.jsp").include(request, response);

        } catch (ServletException ex) {
            throw new DataException("Servlet problem. " + ex.getMessage());
        } catch (IOException ex) {
            throw new DataException("Fejl i ansat håndtering." + ex.getMessage());
        }

        return "index";
    }
}
