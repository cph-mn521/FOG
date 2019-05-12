/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.entities.dto.Case;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Martin
 */
public class TakeCase extends Command{
    
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        /*String CaseId = request.getParameter("caseId");
        try {
            PresentationController PC = new PresentationController(DBURL.PRODUCTION);
            HttpSession ses = request.getSession();
            List<Case> cases = (List<Case>)ses.getAttribute("Cases");
            Case updCase = (Case)ses.getAttribute("inspCase");
            request.getSession().setAttribute("Cases", cases);
            response.getWriter().write("succes");
        } catch (DataException ex) {
            try {
                response.getWriter().write("failure");
            } catch (IOException ex1) {
                Logger.getLogger(TakeCase.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(TakeCase.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        return "0";
        
    }
    
}
