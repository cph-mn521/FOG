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
public class NewFormEmployee extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8");
        try {
            request.getRequestDispatcher("WEB-INF/jsp/newemployee.jsp").include(request, response);

        } catch (ServletException ex) {
            Logger.getLogger(NewFormEmployee.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(NewFormEmployee.class.getName()).log(Level.SEVERE, null, ex);
        }

        return "index";
    }
}
