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
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        StringBuilder status = new StringBuilder();

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
        data.put("newName", request.getParameter("newName"));
        data.put("newPassword", request.getParameter("newPassword"));
        data.put("oldPassword", request.getParameter("oldPassword"));
        data.put("newEmail", request.getParameter("newEmail"));
        data.put("phoneNumber", request.getParameter("phoneNumber"));
        data.put("address", request.getParameter("address"));

        if (!data.get("oldPassword").equals(user.getPassword())) {
            response.getWriter().write("Passwords must match!");
        } else {
            for (Entry<String, String> e : data.entrySet()) {
                String val = e.getValue();
                if (val == null || val.isEmpty()) {
                    continue;
                }
                switch (e.getKey()) {
                    case "newName":
                        newUser.setName(val);
                        status.append("Navn");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, user + "changed name");
                        break;
                    case "newPassword":
                        newUser.setPassword(val);
                        status.append(" Password");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, user + "changed password");
                        break;
                    case "newEmail":
                        newUser.setEmail(val);
                        status.append(" Email");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, user + "changed email");
                        break;
                    case "phoneNumber":
                        newUser.setPhone_number(val);
                        status.append(" Tlf. nr.");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, user + "changed phoneNumber");
                        break;
                    case "address":
                        throw new UnsupportedOperationException("Not implemented yet!");
//                        status.append(" Addresse");
//                        break;
                }
            }

            String out = status.toString();
            if (out.length() == 0) {
                response.getWriter().write("Indtast den information du ønsker at ændre!");
            }

            if (empl) {
                pc.updateEmployee((Employee) user, (Employee) newUser);
            } else {
                pc.updateCustomer((Customer) user, (Customer) newUser);
            }

            response.getWriter().write("Følgende information er ændret: " + out.trim().replaceAll(" ", ", ").replaceFirst("(,\\s)\\w+$", " & "));

            return "ja";
        }
        return "nej";
    }
}
