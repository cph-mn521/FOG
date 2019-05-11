package com.presentation.command;

import com.entities.dto.Employee;
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
public class NewEmployee extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
            String name = (String) request.getParameter("name");
            String rank = (String) request.getParameter("rank");
            String email = (String) request.getParameter("email");
            String password = (String) request.getParameter("password");
            String phone_number = (String) request.getParameter("phone_number");

            if ((name != null && name.isEmpty()) || (email != null && email.isEmpty())
                    || (password != null && password.isEmpty()) || (phone_number != null && phone_number.isEmpty())) {
                pc.createEmployee(new Employee(name, phone_number, email, password, rank));
            } else {
                throw new FormException("Der skal stå noget i alle felter. ");
            }

            session.setAttribute("employees", pc.getAllEmployees());
            request.getRequestDispatcher("WEB-INF/jsp/showallemployees.jsp").include(request, response);

        } catch (ServletException ex) {
            Logger.getLogger(NewEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NewEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "index";
    }
}
