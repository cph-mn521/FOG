package com.presentation.command;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Martin
 */
public class OpenCase extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String CaseNr = request.getParameter("casenr");
        FrontController fc = new FrontController();
        


        return "o";
    }
}
