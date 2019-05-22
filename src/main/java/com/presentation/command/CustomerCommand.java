package com.presentation.command;

import com.entities.dto.Customer;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import com.exceptions.LoginException;
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
public class CustomerCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException, DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");  // Set content type of the response so that jQuery knows what it can expect.

        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        String commandType = (String) request.getParameter("commandType");
        String page = null;

        switch (commandType) {
            case "show":
                page = "showallcustomers";
                showCustomers(pc, session, request);
                break;

            case "prepare":
                page = "changingcustomer";
                prepareCustomer(pc, session, request);
                break;

            case "changed":
                page = "showallcustomers";
                changedCustomer(pc, session, request);
                break;

            case "newform":
                page = "newcustomer";
                prepareFormCustomer(pc, session, request);
                break;

            case "newfinished":
                page = "showallcustomers";
                newCustomer(pc, session, request);
                break;

            case "remove":
                page = "showallcustomers";
                removeCustomer(pc, session, request);
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
            throw new DataException("Problemer med at hente data.");
        } catch (ServletException ex) {
            throw new DataException("Servlet problem.");
        }
        return "succes!";
    }

    public void showCustomers(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException {
        List<Customer> customers = pc.getAllCustomers();
        session.setAttribute("customers", customers);
    }

    public void prepareCustomer(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException {
        try {
            int customerID = Integer.parseInt((String) request.getParameter("customerID"));
            if (customerID > 0) {
                Customer customer = pc.getCustomer(customerID);
                session.setAttribute("customer", customer);
            }
        } catch (NumberFormatException ex) {
            throw new DataException("kunne ikke læse kundes ID. " + ex.getMessage());
        }
    }

    public void changedCustomer(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException, FormException {
        try {
            String name = (String) request.getParameter("name");
            String email = (String) request.getParameter("email");
            String phone_number = (String) request.getParameter("phoneNumber");
            Customer oCust = (Customer) session.getAttribute("customer");
            Customer newCustomer = new Customer(oCust.getCustomer_id(),
                    oCust.getName(), oCust.getEmail(), oCust.getPassword(),
                    oCust.getPhone_number());

            if (newCustomer != null) {
//            Change name
                if (!name.isEmpty()) {
                    newCustomer.setName(name);
                }

//            Change email
                if (!email.isEmpty()) {
                    newCustomer.setEmail(email);
                }

//            Change phone_number
                if (!phone_number.isEmpty()) {
                    newCustomer.setPhone_number(phone_number);
                }

                pc.updateCustomer(oCust, newCustomer);
            }

            session.setAttribute("customers", pc.getAllCustomers());

        } catch (NumberFormatException ex) {
            throw new FormException("Der skal stå noget i alle felter. ");
        }
    }

    public void prepareFormCustomer(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException {
    }

    public void newCustomer(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException, FormException {
        String name = (String) request.getParameter("name");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String phone_number = (String) request.getParameter("phoneNumber");

        if (name != null && !name.isEmpty()
                && email != null && !email.isEmpty()
                && password != null && !password.isEmpty()
                && phone_number != null && !phone_number.isEmpty()) {
            pc.createCustomer(new Customer(name, email, password, phone_number));
        } else {
            throw new FormException("Der skal stå noget i alle felter. ");
        }

        session.setAttribute("customers", pc.getAllCustomers());
    }

    public void removeCustomer(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException, FormException {
        try {
            int customerID = Integer.parseInt((String) request.getParameter("customerID"));

            if (customerID > 0) {
                pc.deleteCustomer(pc.getCustomer(customerID));
            } else {
                throw new FormException("Der skal stå noget i alle felter. ");
            }
        } catch (NumberFormatException ex) {
            throw new DataException("kunne ikke læse kundes ID nummer.");
        }
        session.setAttribute("customers", pc.getAllCustomers());
    }
}
