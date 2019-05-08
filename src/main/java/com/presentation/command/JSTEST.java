package com.presentation.command;

import com.exceptions.LoginException;
import java.io.IOException;
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
public class JSTEST extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException {

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        try {
            response.getWriter().write("{\"var\":1,\"msg\":\"foo\");");
        } catch (IOException ex) {
            return "ree";
        }
        return "Succses";
    }
}

/**
 * The purpose of Login is to...
 *
 * @author martin bøgh
 */
