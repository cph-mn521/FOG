package com.presentation.command;

import com.entities.dto.Employee;
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
public class EmployeeCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException, DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");  // Set content type of the response so that jQuery knows what it can expect.

        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        String commandType = (String) request.getParameter("commandType");
        String page = null;

        switch (commandType) {
            case "show":
                page = "showallemployees";
                showEmployees(pc, session, request);
                break;

            case "prepare":
                page = "changingemployee";
                prepareEmployee(pc, session, request);
                break;

            case "changed":
                page = "showallemployees";
                changedEmployee(pc, session, request);
                break;

            case "newform":
                page = "newemployee";
                prepareFormEmployee(pc, session, request);
                break;

            case "newfinished":
                page = "showallemployees";
                newEmployee(pc, session, request);
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

    public void showEmployees(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException {
        List<Employee> employees = pc.getAllEmployees();
        session.setAttribute("employees", employees);
    }

    public void prepareEmployee(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException {
        try {
            int employee_id = Integer.parseInt((String) request.getParameter("employeeID"));
            if (employee_id > 0) {
                Employee employee = pc.getEmployee(employee_id);
                session.setAttribute("employee", employee);
            }
        } catch (NumberFormatException ex) {
            throw new DataException("kunne ikke læse ansattes ID. " + ex.getMessage());
        }
    }

    public void changedEmployee(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException, FormException {
        try {
            String name = (String) request.getParameter("name");
            String rank = (String) request.getParameter("rank");
            String email = (String) request.getParameter("email");
            String phone_number = (String) request.getParameter("phoneNumber");
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

//            Change phone_number
                if (!phone_number.isEmpty()) {
                    empl.setPhone_number(phone_number);
                }
                pc.updateEmployee(oldempl, empl);
            }

            session.setAttribute("employees", pc.getAllEmployees());

        } catch (NumberFormatException ex) {
            throw new FormException("Der skal stå noget i alle felter. ");
        }
    }

    public void prepareFormEmployee(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException {
    }

    public void newEmployee(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException, FormException {
        String name = (String) request.getParameter("name");
        String rank = (String) request.getParameter("rank");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String phone_number = (String) request.getParameter("phone_number");
        if (name != null && !name.isEmpty()
                && email != null && !email.isEmpty()
                && rank != null && !rank.isEmpty()
                && password != null && !password.isEmpty()
                && phone_number != null && !phone_number.isEmpty()) {
            pc.createEmployee(new Employee(name, phone_number, email, password, rank));
        } else {
            throw new FormException("Der skal stå noget i alle felter. ");
        }

        session.setAttribute("employees", pc.getAllEmployees());
    }
}
