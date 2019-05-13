package com.presentation.command;

import com.entities.dto.Customer;
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
public class ChangingCustomer extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
            int customerID = Integer.parseInt((String) request.getParameter("customerID"));
            if (customerID > 0) {
                Customer customer = pc.getCustomer(customerID);
                session.setAttribute("customer", customer);
            }
            request.getRequestDispatcher("WEB-INF/jsp/changingcustomer.jsp").include(request, response);
        } catch (NumberFormatException | IOException ex) {
            throw new DataException("kunne ikke læse kunde ID." + ex.getMessage());
        } catch (ServletException ex) {
            throw new DataException("Servlet problem. " + ex.getMessage());
        }
        return "w";
    }
}
