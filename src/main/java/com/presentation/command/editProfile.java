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
        data.put("oldPassword", request.getParameter("oldPassword"));
        data.put("newEmail", request.getParameter("newEmail"));
        data.put("phoneNumber", request.getParameter("phoneNumber"));
        data.put("address", request.getParameter("address"));

        try {
            if (obj instanceof Customer) {
                updateCustomer(data, response, (Customer) obj);
            } else {
                updateEmployee(data, response, (Employee) obj);
            }
            ses.setAttribute("user", obj);
        } catch (IOException e) {
            response.getWriter().write("Something went wrong, contact IT-Support.");
        }
        return "ja";
    }

    private void updateEmployee(HashMap<String, String> data, HttpServletResponse response, Employee employee) throws IOException, DataException {
        Employee newUser = employee;
        StringBuilder status = new StringBuilder();
        PresentationController PC = new PresentationController(DBURL.PRODUCTION);

        if (!data.get("oldPassword").equals(employee.getPassword())) {
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
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed name", employee.getEmployee_id());
                        break;
                    case "newPassword":
                        newUser.setPassword(val);
                        status.append(" Password");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed password", employee.getEmployee_id());
                        break;
                    case "newEmail":
                        newUser.setEmail(val);
                        status.append(" Email");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed email", employee.getEmployee_id());
                        break;
                    case "phoneNumber":
                        newUser.setPhone_number(val);
                        status.append(" Tlf. nr.");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed phoneNumber", employee.getEmployee_id());
                        break;
                    case "address":

                        break;
                }
            }

            String out = status.toString();
            if (out.length() == 0) {
                response.getWriter().write("Indtast den information du ønsker at ændre!");
            } else {
                PC.updateEmployee(employee, newUser);
                response.getWriter().write("Følgende information er ændret: " + out.trim().replaceAll(" ", ", ").replaceFirst("(,\\s)\\w+$", " & "));
            }
        }
    }

    private void updateCustomer(HashMap<String, String> data, HttpServletResponse response, Customer customer) throws IOException, DataException {
        Customer newUser = customer;
        StringBuilder status = new StringBuilder();
        ArrayList<String> customerData = new ArrayList<>();
        customerData.add(customer.getName());
        customerData.add(customer.getPassword());
        customerData.add(customer.getEmail());
        customerData.add(customer.getPhone_number());
        PresentationController PC = new PresentationController(DBURL.PRODUCTION);

        if (!data.get("oldPassword").equals(customer.getPassword())) {
            response.getWriter().write("Passwords must match!");
        } else {
            for (Entry<String, String> e : data.entrySet()) {
                String val = e.getValue();
                if (val == null || val.isEmpty() || customerData.contains(val)) {
                    continue;
                }
                switch (e.getKey()) {
                    case "newName":
                        newUser.setName(val);
                        status.append("Navn");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed name", customer.getCustomer_id());
                        break;
                    case "newPassword":
                        newUser.setPassword(val);
                        status.append(" Password");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed password", customer.getCustomer_id());
                        break;
                    case "newEmail":
                        newUser.setEmail(val);
                        status.append(" Email");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed email", customer.getCustomer_id());
                        break;
                    case "phoneNumber":
                        newUser.setPhone_number(val);
                        status.append(" Tlf. nr.");
                        Logger.getLogger(editProfile.class.getName()).log(Level.WARNING, "{0}changed phoneNumber", customer.getCustomer_id());
                        break;
                    case "address":
                        break;
                }
            }

            String out = status.toString();
            if (out.length() == 0) {
                response.getWriter().write("Indtast den information du ønsker at ændre!");
            } else {
                PC.updateCustomer(customer, newUser);
                response.getWriter().write("Følgende information er ændret: " + out.trim().replaceAll(" ", ", ").replaceFirst("(,\\s)\\w+$", " & "));
            }
        }
    }
}
