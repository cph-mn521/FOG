/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nille
 */
public class editProfile extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession ses = request.getSession();
        Object obj = ses.getAttribute("user");
        HashMap<String, String> data = new HashMap();
        data.put("newName", request.getParameter("newName"));
        data.put("newPassword", request.getParameter("newPassword"));
        // data.put("oldPassword", request.getParameter("oldPassword"));
        data.put("newEmail", request.getParameter("newEmail"));
        data.put("phoneNumber", request.getParameter("newphoneNumber"));
        data.put("address", request.getParameter("address"));

        try {
            if (obj instanceof Employee) {
                updateUser(data, response, (Employee) obj);
            } else {
                updateUser(data, response, (Customer) obj);
            }

            ses.setAttribute("user", obj);

        } catch (IOException e) {
            response.getWriter().write("Something went wrong, contact IT-Support.");
        }
        return "ja";
    }

    private String updateUser(HashMap<String, String> data, HttpServletResponse response, Employee employee) throws IOException, DataException {
        Employee newUser = new Employee(null, null, null, null, null);
        StringBuilder status = new StringBuilder();
        PresentationController PC = new PresentationController(DBURL.PRODUCTION);

        for (Entry<String, String> e : data.entrySet()) {
            String val = e.getValue();
            if (val == null || val.isEmpty()) {
                continue;
            }
            switch (e.getKey()) {
                case "newName":
                    if (!val.equals(employee.getName())) {
                        status.append("Navn");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed name", employee.getEmployee_id());
                    }
                    newUser.setName(val);
                    break;
                case "newPassword":
                    if (!val.equals(employee.getPassword())) {
                        status.append(" Password");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed password", employee.getEmployee_id());
                    }
                    newUser.setPassword(val);
                    break;
                case "newEmail":
                    if (!val.equals(employee.getEmail())) {
                        status.append(" Email");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed email", employee.getEmployee_id());
                    }
                    newUser.setEmail(val);
                    break;
                case "phoneNumber":
                    if (!val.equals(employee.getPhone_number())) {
                        status.append(" Tlf. nr.");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed phoneNumber", employee.getEmployee_id());
                    }
                    newUser.setPhone_number(val);
                    break;
                case "address":

                    break;
            }
        }

        String out = status.toString();
        if (out.length() == 0) {
            return "Indtast den information du ønsker at ændre!";
        } else {
            PC.updateEmployee(employee, newUser);
            return "Følgende information er ændret: " + out.trim().replaceAll(" ", ", ").replaceFirst("(,\\s)\\w+$", " & ");
        }
    }

    private String updateUser(HashMap<String, String> data, HttpServletResponse response, Customer customer) throws IOException, DataException {
        Customer newUser = new Customer(null, null, null, null);
        StringBuilder status = new StringBuilder();
        PresentationController PC = new PresentationController(DBURL.PRODUCTION);

        for (Entry<String, String> e : data.entrySet()) {
            String val = e.getValue();
            if (val == null || val.isEmpty()) {
                continue;
            }
            switch (e.getKey()) {
                case "newName":
                    if (!val.equals(customer.getName())) {
                        status.append("Navn");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed name", customer.getCustomer_id());
                    }
                    newUser.setName(val);
                    break;
                case "newPassword":
                    if (!val.equals(customer.getPassword())) {
                        status.append(" Password");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed password", customer.getCustomer_id());
                    }
                    newUser.setPassword(val);
                    break;
                case "newEmail":
                    if (!val.equals(customer.getEmail())) {
                        status.append(" Email");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed email", customer.getCustomer_id());
                    }
                    newUser.setEmail(val);
                    break;
                case "phoneNumber":
                    if (!val.equals(customer.getPhone_number())) {
                        status.append(" Tlf. nr.");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed phoneNumber", customer.getCustomer_id());
                    }
                    newUser.setPhone_number(val);
                    break;
                case "address":

                    break;
            }
        }

        String out = status.toString();
        if (out.length() == 0) {
            return "Indtast den information du ønsker at ændre!";
        } else {
            PC.updateCustomer(customer, newUser);
            return "Følgende information er ændret: " + out.trim().replaceAll(" ", ", ").replaceFirst("(,\\s)\\w+$", " & ");
        }
    }
}
