package com.presentation.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author niller, martin bøgh
 */
public class ShowOrders extends Command
{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) 
    {
        return "index";
    }
}
