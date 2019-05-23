package com.presentation.command;

import com.entities.dto.Customer;
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
public class CustomerCommand extends Command {

    @Override
    /**
     * 
     */
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, PresentationException {
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
    public void showCustomers(PresentationController pc,
            HttpSession session, HttpServletRequest request) throws DataException {
        List<Customer> customers = pc.getAllCustomers();
        session.setAttribute("customers", customers);
    }

    /**
     * 
     * @param pc
     * @param session
     * @param request
     * @throws DataException
     * @throws PresentationException 
     */
    public void prepareCustomer(PresentationController pc,
            HttpSession session, HttpServletRequest request) throws DataException, PresentationException {
        try {
            int customerID = Integer.parseInt((String) request.getParameter("customerID"));
            if (customerID > 0) {
                Customer customer = pc.getCustomer(customerID);
                session.setAttribute("customer", customer);
            }
        } catch (NumberFormatException ex) {
            throw new PresentationException("kunne ikke læse kundes ID. " + ex.getMessage());
        }
    }

    /**
     * 
     * @param pc
     * @param session
     * @param request
     * @throws DataException
     * @throws PresentationException 
     */
    public void changedCustomer(PresentationController pc,
            HttpSession session, HttpServletRequest request) throws DataException, PresentationException {
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
            throw new PresentationException("Der skal stå noget i alle felter. ");
        }
    }

    public void prepareFormCustomer(PresentationController pc,
            HttpSession session, HttpServletRequest request) {
    }

    public void newCustomer(PresentationController pc,
            HttpSession session, HttpServletRequest request) throws DataException, PresentationException {
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
            throw new PresentationException("Der skal stå noget i alle felter. ");
        }

        session.setAttribute("customers", pc.getAllCustomers());
    }

    /**
     * 
     * @param pc
     * @param session
     * @param request
     * @throws DataException
     * @throws PresentationException 
     */
    public void removeCustomer(PresentationController pc,
            HttpSession session, HttpServletRequest request) throws DataException, PresentationException {
        try {
            int customerID = Integer.parseInt((String) request.getParameter("customerID"));

            if (customerID > 0) {
                pc.deleteCustomer(pc.getCustomer(customerID));
            } else {
                throw new PresentationException("Der skal stå noget i alle felter. ");
            }
        } catch (NumberFormatException ex) {
            throw new PresentationException("kunne ikke læse kundes ID nummer.");
        }
        session.setAttribute("customers", pc.getAllCustomers());
    }
}
