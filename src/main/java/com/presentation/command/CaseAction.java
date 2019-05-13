/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.entities.dto.Case;
import com.entities.dto.Employee;
import com.entities.dto.User;
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
public class CaseAction extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession ses = request.getSession();
        try {
            switch (request.getParameter("Action")) {
                case "takeCase":
                    takeCase(request, response, ses);
                    break;
                case "workCase":
                    workCase(request, response, ses);
                    break;
                case "reopenCase":

                    break;
            }

        } catch (DataException ex) {
            try {
                response.getWriter().write(ex.getMessage());
            } catch (IOException ex1) {
                Logger.getLogger(CaseAction.class.getName()).log(Level.SEVERE, null, ex1);
            }
        } catch (IOException ex) {
            Logger.getLogger(CaseAction.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "0";

    }

    private void reopencase(HttpServletRequest request, HttpServletResponse response, HttpSession ses) {

    }

    private void workCase(HttpServletRequest request, HttpServletResponse response, HttpSession ses) throws IOException {
        Case WC = (Case) ses.getAttribute("inspCase");
        ses.setAttribute("currentCase", WC);
        if (WC.getOrderId() != 0) {
            ses.setAttribute("odrerID", WC.getOrderId());
        }
        ses.setAttribute("inspCase", null);
        response.getWriter().write("succes");
    }

    private void takeCase(HttpServletRequest request, HttpServletResponse response, HttpSession ses) throws DataException, IOException {
        PresentationController PC = new PresentationController(DBURL.PRODUCTION);
        List<Case> cases = (List<Case>) ses.getAttribute("Cases");
        Case updCase = (Case) ses.getAttribute("inspCase");
        Employee empl = (Employee) ses.getAttribute("user");
        PC.TakeCase(empl.getEmployee_id(), updCase.getCaseId());
        cases.add(updCase);
        ses.setAttribute("Cases", cases);
        response.getWriter().write("succes");
    }

    private void closeCase(HttpServletRequest request, HttpServletResponse response, HttpSession ses) throws DataException, IOException {
        PresentationController PC = new PresentationController(DBURL.PRODUCTION);
        List<Case> cases = (List<Case>) ses.getAttribute("Cases");
        Case updCase = (Case) ses.getAttribute("currentCase");
        Employee empl = (Employee) ses.getAttribute("empl");
        PC.closeCase(updCase.getCaseId());
        cases.remove(updCase);
        ses.setAttribute("Cases", cases);
        ses.setAttribute("currentCase", null);
        response.getWriter().write("succes");
    }

    private void editCase(HttpServletRequest request, HttpServletResponse response, HttpSession ses) {

    }

}
