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
public class ChangedEmployee extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8"); 
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
            String name = (String) request.getParameter("name");
            String rank = (String) request.getParameter("rank");
            String email = (String) request.getParameter("email");
            String phone_number = (String) request.getParameter("phone_number");
            Employee oldempl = (Employee) session.getAttribute("employee");
            Employee empl = oldempl;

            if (empl != null) {
//            Change name
                if (!name.isEmpty()) {
                    empl.setName(name);
                }

//            Change rank
                if (!rank.isEmpty()) {
                    empl.setRank(rank);
                }

//            Change component
                if (!email.isEmpty()) {
                    empl.setEmail(email);
                }

                if (!phone_number.isEmpty()) {
                    empl.setPhone_number(phone_number);
                }

//                if (deleted != null && deleted.equals("true"))
//                {
//                    session.setAttribute("component", null);
//                    pc.deleteComponent(pc.getComponent(comp.getComponentId()));
//                } else
//                {
                pc.updateEmployee(oldempl, empl);
//                }
            }

            session.setAttribute("employees", pc.getAllEmployees());
            if (empl.getEmployee_id()> 0) {
                session.setAttribute("employees", pc.getComponent(empl.getEmployee_id()));
            }
              try {
                request.getRequestDispatcher("WEB-INF/jsp/showallemployees.jsp").include(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(ChangedEmployee.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ChangedEmployee.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException ex) {
            throw new FormException("Der skete en fejl ved hentning af materiale");
        }

        return "index";
    }
}
