package com.presentation.command;

import com.entities.dto.Employee;
import com.entities.dto.Message;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import static com.presentation.command.CaseAction.getBody;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Martin
 */
public class msgAction extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            switch (request.getParameter("Action")) {
                case "new":
                    createMsg(request, response);
                    break;

            }

        } catch (DataException | IOException ex) {
            Logger.getLogger(msgAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "o";
    }

    private void createMsg(HttpServletRequest request, HttpServletResponse response) throws DataException, IOException {
        PresentationController PC = new PresentationController(DBURL.PRODUCTION);
        String body = getBody(request);
        String[] MSG = body.split("--.--..-");
        Message msg = new Message(0, MSG[0], Date.valueOf(LocalDate.MAX), MSG[1], MSG[2]);
        PC.createMsg(msg);
        response.getWriter().write("succes");

    }

}
