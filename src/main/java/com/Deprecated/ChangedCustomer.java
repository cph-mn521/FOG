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
//            String password = (String) request.getParameter("password");
            Customer oCust = (Customer) session.getAttribute("customer");
            Customer newCustomer = new Customer(oCust.getCustomer_id(), oCust.getName(), oCust.getEmail(), oCust.getPassword(), oCust.getPhone_number());

            if (newCustomer != null) {
//            Change name
                if (!name.isEmpty()) {
                    newCustomer.setName(name);
                }

//            Change email
                if (!email.isEmpty()) {
                    newCustomer.setEmail(email);
                }

//            Change password
//                if (!password.isEmpty()) {
//                    newCustomer.setPassword(password);
//                }

//            Change phone_number
                if (!phone_number.isEmpty()) {
                    newCustomer.setPhone_number(phone_number);
                }

//                if (deleted != null && deleted.equals("true"))
//                {
//                    session.setAttribute("component", null);
//                    pc.deleteComponent(pc.getComponent(comp.getComponentId()));
//                } else
//                {
                pc.updateCustomer(oCust, newCustomer);
//                }
            }

            session.setAttribute("customers", pc.getAllCustomers());
            try {
                request.getRequestDispatcher("WEB-INF/jsp/showallcustomers.jsp").include(request, response);
            } catch (ServletException ex) {
                throw new DataException("Servlet problem. " + ex.getMessage());
            } catch (IOException ex) {
                throw new DataException("kunne ikke læse kundes data. " + ex.getMessage());
            }
        } catch (NumberFormatException ex) {
            System.out.println("NumberFormatException: " + ex.getMessage());
            return "index";
        }

        return "index";
    }
}
