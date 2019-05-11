package com.presentation.command;

import com.entities.dto.Customer;
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
public class ChangingCustomers extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
            int customerID = Integer.parseInt((String) request.getParameter("customerid"));
            if (customerID > 0) {
                Customer customer = pc.getCustomer(customerID);
                session.setAttribute("component", customer);
            }
            request.getRequestDispatcher("WEB-INF/jsp/changingcustomer.jsp").include(request, response);
        } catch (NumberFormatException | IOException ex) {
            throw new DataException("kunne ikke læse kunde ID." + ex.getMessage());
        } catch (ServletException ex) {
            Logger.getLogger(ShowOrder.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "w";
    }
}
