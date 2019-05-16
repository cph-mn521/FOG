package com.presentation.command;

import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import java.io.IOException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class NewCustomer extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
            String name = (String) request.getParameter("name");
            String email = (String) request.getParameter("email");
            String password = (String) request.getParameter("password");
            String phone_number = (String) request.getParameter("phone_number");

            if (name != null && !name.isEmpty()
                    && email != null && !email.isEmpty()
                    && password != null && !password.isEmpty()
                    && phone_number != null && !phone_number.isEmpty()) {
                pc.createCustomer(new Customer(name, email, password, phone_number));
            } else {
                throw new FormException("Der skal stå noget i alle felter. ");
            }

            session.setAttribute("employees", pc.getAllEmployees());
            request.getRequestDispatcher("WEB-INF/jsp/showallcustomers.jsp").include(request, response);

        } catch (ServletException ex) {
            throw new DataException("Servlet problem. " + ex.getMessage());
        } catch (IOException ex) {
            throw new DataException("Fejl i kunde håndtering." + ex.getMessage());
        }

        return "index";
    }
}
