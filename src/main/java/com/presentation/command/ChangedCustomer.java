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
public class ChangedCustomer extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
            String name = (String) request.getParameter("name");
            String email = (String) request.getParameter("email");
            String phone_number = (String) request.getParameter("phone_number");
            Customer oldCustomer = (Customer) session.getAttribute("customer");
            Customer customer = oldCustomer;

            if (customer != null) {

//            Change component
                if (!name.isEmpty()) {
                    customer.setName(name);
                }

                if (!email.isEmpty()) {
                    customer.setEmail(email);
                }

                if (!phone_number.isEmpty()) {
                    customer.setPhone_number(phone_number);
                }

//                if (deleted != null && deleted.equals("true"))
//                {
//                    session.setAttribute("component", null);
//                    pc.deleteComponent(pc.getComponent(comp.getComponentId()));
//                } else
//                {
                pc.updateCustomer(oldCustomer, customer);
//                }
            }

            session.setAttribute("customers", pc.getAllCustomers());
            if (customer.getCustomer_id() > 0) {
                session.setAttribute("customer", pc.getCustomer(customer.getCustomer_id()));
            }
            try {
                request.getRequestDispatcher("WEB-INF/jsp/showallcustomers.jsp").include(request, response);
            } catch (ServletException ex) {
                throw new DataException("Servlet problem. " + ex.getMessage());
            } catch (IOException ex) {
                throw new DataException("kunne ikke læse kundes data. " + ex.getMessage());
            }
        } catch (NumberFormatException ex) {
            throw new FormException("Der skete en fejl ved hentning af materiale");
        }

        return "index";
    }
}
