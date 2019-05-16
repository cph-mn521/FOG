package com.presentation.command;

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
//            String password = (String) request.getParameter("password");
            String phone_number = (String) request.getParameter("phone_number");
            Employee oldempl = (Employee) session.getAttribute("employee");
            Employee empl = new Employee(oldempl.getEmployee_id(), oldempl.getName(),
                    oldempl.getPhone_number(), oldempl.getEmail(), oldempl.getPassword(), oldempl.getRank());

            if (empl != null) {
//            Change name
                if (!name.isEmpty()) {
                    empl.setName(name);
                }

//            Change rank
                if (!rank.isEmpty()) {
                    empl.setRank(rank);
                }

//            Change email
                if (!email.isEmpty()) {
                    empl.setEmail(email);
                }

////            Change password
//                if (!password.isEmpty()) {
//                    empl.setPassword(password);
//                }

//            Change phone_number
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
            try {
                request.getRequestDispatcher("WEB-INF/jsp/showallemployees.jsp").include(request, response);
            } catch (ServletException ex) {
                throw new DataException("Servlet problem. " + ex.getMessage());
            } catch (IOException ex) {
                throw new DataException("kunne ikke læse ansattes data. " + ex.getMessage());
            }
        } catch (NumberFormatException ex) {
            System.out.println("NumberFormatException: " + ex.getMessage());
            return "index";
        }

        return "index";
    }
}
