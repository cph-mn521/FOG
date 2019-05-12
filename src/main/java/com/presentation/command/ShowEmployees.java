package com.presentation.command;

import com.entities.dto.Employee;
import com.entities.dto.Order;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.LoginException;
import java.io.IOException;
import java.util.List;
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
public class ShowEmployees extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException, DataException {
        response.setContentType("text/plain;charset=UTF-8");  // Set content type of the response so that jQuery knows what it can expect.
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        
        try {
            List<Employee> employees = pc.getAllEmployees();
            session.setAttribute("employees", employees);
            request.getRequestDispatcher("WEB-INF/jsp/showallemployees.jsp").include(request, response);
        } catch (IOException ex) {
            return "ohnoes";
        } catch (ServletException ex) {
            Logger.getLogger(ShowEmployees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "succes!";
    }
}
