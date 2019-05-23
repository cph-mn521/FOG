package com.presentation.command;

import com.entities.dto.Employee;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.PresentationException;
import java.io.IOException;
import java.util.Enumeration;
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
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, PresentationException {
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

            case "remove":
                page = "showallemployees";
                removeEmployee(pc, session, request);
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
     */
    public void showEmployees(PresentationController pc,
            HttpSession session, HttpServletRequest request) throws DataException {

        List<Employee> employees = pc.getAllEmployees();
        session.setAttribute("employees", employees);
    }

    /**
     *
     * @param pc
     * @param session
     * @param request
     * @throws DataException
     * @throws PresentationException
     */
    public void prepareEmployee(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws DataException, PresentationException {
        try {
            int employee_id = Integer.parseInt((String) request.getParameter("employeeID"));
            if (employee_id > 0) {
                Employee employee = pc.getEmployee(employee_id);
                session.setAttribute("employee", employee);
            }
        } catch (NumberFormatException ex) {
            throw new PresentationException("kunne ikke læse ansattes ID. " + ex.getMessage());
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
    public void changedEmployee(PresentationController pc,
            HttpSession session, HttpServletRequest request) throws DataException, PresentationException {
        try {
            String name = (String) request.getParameter("name");
            String rank = (String) request.getParameter("rank");
            String email = (String) request.getParameter("email");
            String phone_number = (String) request.getParameter("phoneNumber");
            Employee oldempl = (Employee) session.getAttribute("employee");
            Employee empl = new Employee(oldempl.getEmployee_id(), oldempl.getName(),
                    oldempl.getPhone_number(), oldempl.getEmail(), oldempl.getPassword(),
                    oldempl.getRank());

            // change employee object
            if (empl != null) {
                if (!name.isEmpty()) {
                    empl.setName(name);
                }

                if (!rank.isEmpty()) {
                    empl.setRank(rank);
                }

                if (!email.isEmpty()) {
                    empl.setEmail(email);
                }

                if (!phone_number.isEmpty()) {
                    empl.setPhone_number(phone_number);
                }
                pc.updateEmployee(oldempl, empl);
            }
//            if (user != null && !user.getRank().equals("salesperson")) {
            session.setAttribute("employees", pc.getAllEmployees());
//            }

        } catch (NumberFormatException ex) {
            throw new PresentationException("Der skal stå noget i alle felter. ");
        }
    }

    /**
     *
     * @param pc
     * @param session
     * @param request
     * @throws DataException
     */
    public void prepareFormEmployee(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws DataException {
    }

    /**
     * 
     * @param pc
     * @param session
     * @param request
     * @throws DataException
     * @throws PresentationException 
     */
    public void newEmployee(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws DataException, PresentationException {
        String name = (String) request.getParameter("name");
        String rank = (String) request.getParameter("rank");
        String email = (String) request.getParameter("email");
        String password = (String) request.getParameter("password");
        String phone_number = (String) request.getParameter("phoneNumber");
        if (name != null && !name.isEmpty()
                && email != null && !email.isEmpty()
                && rank != null && !rank.isEmpty()
                && password != null && !password.isEmpty()
                && phone_number != null && !phone_number.isEmpty()) {
            pc.createEmployee(new Employee(name, phone_number, email, password, rank));
        } else {
            throw new PresentationException("Der skal stå noget i alle felter. ");
        }

        session.setAttribute("employees", pc.getAllEmployees());
    }

    /**
     * 
     * @param pc
     * @param session
     * @param request
     * @throws DataException
     * @throws PresentationException 
     */
    public void removeEmployee(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws DataException, PresentationException {
        try {
            int employeeID = Integer.parseInt((String) request.getParameter("employeeID"));

            if (employeeID > 0) {
                pc.deleteEmployee(pc.getEmployee(employeeID));
            } else {
                throw new PresentationException("Der skal stå noget i alle felter. ");
            }
        } catch (NumberFormatException ex) {
            throw new PresentationException("kunne ikke læse ordres ID nummer.");
        }
        session.setAttribute("employees", pc.getAllEmployees());
    }

    /**
     * Tool for checking session objects
     *
     * @param session
     */
    private void seeSession(HttpSession session) {
        Enumeration e = (Enumeration) (session.getAttributeNames());

        while (e.hasMoreElements()) {
            Object tring;
            if ((tring = e.nextElement()) != null) {
                System.out.println((session.getValue((String) tring)));
                System.out.println("\n");
            }
        }
    }
}
