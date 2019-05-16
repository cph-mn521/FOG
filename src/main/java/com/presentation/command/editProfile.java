/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.entities.dto.User;
import com.enumerations.DBURL;
import java.util.HashMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nille
 */
public class editProfile extends Command {

    @Override
    String execute(ServletContext context, HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession ses = request.getSession();
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        Object obj = ses.getAttribute("user");
        User user, newUser;

        boolean empl = false;
        if (obj instanceof Customer) {
            user = (Customer) obj;
            newUser = user;
        } else {
            user = (Employee) obj;
            newUser = user;
            empl = true;
        }
        HashMap<String, String> data = new HashMap();

        String newName = request.getParameter("newName");
        String newPassword = request.getParameter("newPassword");
        String oldPassword = request.getParameter("oldPassword");
        String newEmail = request.getParameter("newEmail");
        String phoneNumber = request.getParameter("phoneNumber");

        if (!oldPassword.equals(user.getPassword())) {
            response.getWriter().write("Passwords must match!");

        } else {
            user.setPassword(newPassword);

            if (empl) {
                pc.updateEmployee((Employee) user, (Employee) newUser);
            } else {
                pc.updateCustomer((Customer) user, (Customer) newUser);
            }

            return "ja";
        }
        return "nej";
    }
}
