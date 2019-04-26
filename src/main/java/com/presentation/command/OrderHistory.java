/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Customer;
import com.entities.dto.User;
import com.exceptions.LoginException;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author nille
 */
public class OrderHistory extends Command
{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException
    {
        HttpSession session = request.getSession();
        Customer customer = (Customer) session.getAttribute("customer");
//         ArrayList<BillOfMaterials> bills = FrontController.getHistory(customer.getCustomer_id());
//         session.setAttribute("bills", bills);

        //Ændres til noget relevant
        return "page";
    }
}
