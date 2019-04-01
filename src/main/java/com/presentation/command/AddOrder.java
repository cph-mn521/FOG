/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.enteties.dto.Order;
import com.enteties.dto.User;
import com.exceptions.DataException;
import com.presentation.frontcontrol.FrontController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nille
 */
public class AddOrder extends Command {

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws DataException {
        try {
            HttpSession session = request.getSession();
            int lenght = Integer.parseInt(request.getParameter("length"));
            int width = Integer.parseInt(request.getParameter("width"));
            int height = Integer.parseInt(request.getParameter("height"));
            User user = (User) session.getAttribute("user");
            FrontController.addOrder(new Order());
            return user.role + "page";
        } catch (DataException e) {
            throw new DataException(e.getMessage());
        }
    }
}
