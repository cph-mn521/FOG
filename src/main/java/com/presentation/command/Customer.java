/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.entities.dto.Roof;
import com.enumerations.DBURL;
import java.util.List;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Martin
 */
public class Customer extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PresentationController pc = new PresentationController(DBURL.TEST);
        switch (request.getParameter("Action")) {
            case "index":
                request.getRequestDispatcher("WEB-INF/Customer/CustomerIndex.jsp").include(request, response);
                break;
            case "Buy":
                List<Roof> roofs = pc.getAllRoofs();
                request.setAttribute("roofs", roofs);
                request.getRequestDispatcher("WEB-INF/Customer/jsp/BuyCarport.jsp").include(request, response);
                break;
            case "register":
                request.getRequestDispatcher("WEB-INF/Customer/jsp/Register.jsp").include(request, response);
                break;
        }
        return "o";
    }
}
