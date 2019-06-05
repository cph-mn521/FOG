/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.entities.dto.Customer;
import com.entities.dto.Roof;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Martin
 */
public class CustomerActions extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, ServletException, IOException {
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        switch (request.getParameter("Action")) {
            case "index":
                request.getRequestDispatcher("WEB-INF/Customer/CustomerIndex.jsp").forward(request, response);
                break;
            case "Buy":
                try {
                    List<Roof> roofs = pc.getAllRoofs();
                    request.setAttribute("roofs", roofs);
                    request.getRequestDispatcher("WEB-INF/Customer/jsp/BuyCarport.jsp").include(request, response);
                } catch (DataException e) {
                    String r = "reee";
                }
                break;
            case "register":
                request.getRequestDispatcher("WEB-INF/Customer/jsp/Register.jsp").include(request, response);
                break;
            case "profile":
                request.getRequestDispatcher("WEB-INF/Customer/jsp/Profile.jsp").include(request, response);
                break;
            case "MyCase":
                HttpSession session = request.getSession();
                Customer C = (Customer)session.getAttribute("customer");
                PresentationController PC = new PresentationController(DBURL.PRODUCTION);
                PC.getCustomerCases( C.getCustomer_id(), request);
                request.getRequestDispatcher("WEB-INF/Customer/jsp/MyCase.jsp").include(request, response);
                break;
        }
        return "o";
    }
}
