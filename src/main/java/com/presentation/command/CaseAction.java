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
import com.entities.dto.Customer;
import com.exceptions.DataException;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Martin
 */
public class CaseAction extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
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
                    reopencase(request, response, ses);
                    break;
                case "Update":
                    editCase(request, response, ses);
                    break;
                case "closeCase":
                    closeCase(request, response, ses);
                    break;
                case "freeCase":
                    freeCase(request, response, ses);
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

    private void reopencase(HttpServletRequest request, HttpServletResponse response, HttpSession ses) throws DataException, IOException {
        Case WC = (Case) ses.getAttribute("inspCase");
        PresentationController PC = new PresentationController(DBURL.PRODUCTION);
        PC.updCaseStat(WC.getCaseId(), "open");

        ses.setAttribute("currentCase", WC);
        ses.setAttribute("odrerID", WC.getOrderId());
        List<Case> cases = (List<Case>) ses.getAttribute("Cases");
        cases.add(WC);
        ses.setAttribute("Cases", cases);
        
        List<Case> OC = (List<Case>) request.getSession().getAttribute("oldCases");
        ses.setAttribute("oldCases", OC);
        for (Case aCase : OC) {
            if (aCase.getCaseId() == WC.getCaseId()) {
                cases.remove(aCase);
                break;
            }
        }
        response.getWriter().write("succes");
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
        Case updCase = (Case) ses.getAttribute("currentCase");
        PC.updCaseStat(updCase.getCaseId(), "closed");
        List<Case> cases = (List<Case>) ses.getAttribute("Cases");
        for (Case aCase : cases) {
            if (aCase.getCaseId() == updCase.getCaseId()) {
                cases.remove(aCase);
                break;
            }
        }
        ses.setAttribute("Cases", cases);
        ses.setAttribute("currentCase", null);
        List<Case> OC = (List<Case>) request.getSession().getAttribute("oldCases");
        OC.add(updCase);
        ses.setAttribute("oldCases", OC);
        response.getWriter().write("succes");
    }

    private void freeCase(HttpServletRequest request, HttpServletResponse response, HttpSession ses) throws DataException, IOException {
        PresentationController PC = new PresentationController(DBURL.PRODUCTION);
        Case updCase = (Case) ses.getAttribute("currentCase");
        PC.updCasefree(updCase.getCaseId());

        List<Case> cases = (List<Case>) ses.getAttribute("Cases");
        for (Case aCase : cases) {
            if (aCase.getCaseId() == updCase.getCaseId()) {
                cases.remove(aCase);
                break;
            }
        }
        response.getWriter().write("succes");
        ses.setAttribute("Cases", cases);
        ses.setAttribute("currentCase", null);
        ses.setAttribute("odrerID", null);
        response.getWriter().write("succes");
    }

    private void editCase(HttpServletRequest request, HttpServletResponse response, HttpSession ses) throws DataException, IOException {
        String body = getBody(request);
        String[] JSONS = body.split("-.--.");
        Gson gson = new Gson();
        Case OCase = (Case) ses.getAttribute("currentCase");
        Case NCase = gson.fromJson(JSONS[0], Case.class);
        User UUser = gson.fromJson(JSONS[1], User.class);
        Customer OUser = (Customer) ses.getAttribute("customer");
        ///// OUSER ER  NULL! FIX!
        Customer SFL = new Customer("", OUser.getEmail(), OUser.getPassword(), "");

        AjourCase(OCase, NCase);
        AjourUser(OUser, UUser);
        // Everything Ajour, push to DB and to request.
        PresentationController PC = new PresentationController(DBURL.PRODUCTION);
        PC.updateCustomer(SFL, OUser);
        PC.updCaseMsg(OCase);
        response.getWriter().write("succes");
    }

    private void AjourCase(Case OC, Case NC) {
        if (!NC.getMsg_owner().equals(OC.getMsg_owner()) && NC.getMsg_owner().length()>1 ) {
            OC.setMsg_owner(NC.getMsg_owner());
        }
        if (!NC.getMsg_status().equals(OC.getMsg_status()) && NC.getMsg_status().length()>7) {
            OC.setMsg_status(NC.getMsg_status());
        }
    }

    private void AjourUser(Customer OU, User NU) {
        if (!OU.getEmail().equals(NU.getEmail()) && NU.getEmail().length()>1 ) {
            OU.setEmail(NU.getEmail());
        }
        if (!OU.getName().equals(NU.getName()) && NU.getName().length()>1 ) {
            OU.setName(NU.getName());
        }
        if (!OU.getPhone_number().equals(NU.getPhone_number())&& NU.getPhone_number().length()>1) {
            OU.setPhone_number(NU.getPhone_number());
        }
    }

    /**
     * Method for reading the body of a request and returning it a as a string.
     * It is more general, and should maybe be an inherit method for the command
     * class.
     *
     * @param request
     * @return String
     * @throws IOException
     */
    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}
