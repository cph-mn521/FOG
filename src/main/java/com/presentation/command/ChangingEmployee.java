package com.presentation.command;

import com.entities.dto.Employee;
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
public class ChangingEmployee extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
            int employee_id = Integer.parseInt((String) request.getParameter("employeeID"));
            if (employee_id > 0) {
                Employee employee = pc.getEmployee(employee_id);
                session.setAttribute("employee", employee);
            }
            request.getRequestDispatcher("WEB-INF/jsp/changingemployee.jsp").include(request, response);
        } catch (NumberFormatException | IOException ex) {
            throw new DataException("kunne ikke læse ansattes ID. " + ex.getMessage());
        } catch (ServletException ex) {
            throw new DataException("Servlet problem. " + ex.getMessage());
        }
        return "w";
    }
}
