package com.presentation.command;

import com.entities.dto.Case;
import com.entities.dto.Message;
import com.entities.dto.Customer;
import com.entities.dto.User;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Martin
 */
public class getJSP extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        try {
            PresentationController PC = new PresentationController(DBURL.PRODUCTION);
            //response.getWriter().write("{ \"name\":\"John\", \"age\":30, \"car\":null }");
            switch (request.getParameter("page")) {
                case "availCases":
                    try {
                        String Rank = (String) request.getSession().getAttribute("rank");
                        request.setAttribute("freeCases", PC.getFreeCases(Rank));
                        request.setAttribute("msg", PC.getMessages(Rank));
                        request.getRequestDispatcher("WEB-INF/jsp/availCases.jsp").include(request, response);
                    } catch (DataException e) {
                        request.getRequestDispatcher("WEB-INF/jsp/404.jsp").include(request, response);

                    }
                    break;
                case "viewCase":
                    try {
                        String caseID = (String) request.getParameter("caseID");
                        Case C = PC.getCase(caseID);
                        request.setAttribute("case", C);
                        request.setAttribute("user", PC.getCustomer(C.getCustomerId()));
                        request.getRequestDispatcher("WEB-INF/jsp/viewCase.jsp").include(request, response);
                        request.getSession().setAttribute("inspCase", C);
                    } catch (DataException e) {
                        request.getRequestDispatcher("WEB-INF/jsp/404.jsp").include(request, response);
                    }
                    break;
                case "ActiveCase":
                    try {
                        HttpSession ses = request.getSession();
                        Case C = (Case) ses.getAttribute("currentCase");
                        request.setAttribute("case", C);
                        Customer u = PC.getCustomer(C.getCustomerId());
                        ses.setAttribute("customer", u);
                        User us = new User(u.getName(), u.getEmail(), u.getPhone_number());
                        request.setAttribute("owner", us);
                        request.getRequestDispatcher("WEB-INF/jsp/ActiveCase.jsp").include(request, response);
                    } catch (DataException e) {
                        request.getRequestDispatcher("WEB-INF/jsp/404.jsp").include(request, response);
                    }
                    break;
                case "viewMessage":
                    try {
                        String msgID = (String) request.getParameter("msgID");
                        Message M = PC.getMessage(msgID);
                        request.setAttribute("msg", M);
                        request.getRequestDispatcher("WEB-INF/jsp/viewMessage.jsp").include(request, response);
                    } catch (DataException e) {
                        request.getRequestDispatcher("WEB-INF/jsp/404.jsp").include(request, response);
                    }
                    break;
                case "activeCases":
                    try {
                        String Rank = (String) request.getSession().getAttribute("rank");
                        List<Case> cases = (List<Case>) request.getSession().getAttribute("Cases");
                        request.setAttribute("freeCases", cases);
                        request.setAttribute("msg", PC.getMessages(Rank));
                        request.getRequestDispatcher("WEB-INF/jsp/availCases.jsp").include(request, response);
                    } catch (DataException e) {
                        request.getRequestDispatcher("WEB-INF/jsp/404.jsp").include(request, response);

                    }
                    break;
                case "oldCases":
                    try {
                        String Rank = (String) request.getSession().getAttribute("rank");
                        List<Case> cases = (List<Case>) request.getSession().getAttribute("oldCases");
                        request.setAttribute("freeCases", cases);
                        request.setAttribute("msg", PC.getMessages(Rank));
                        request.getRequestDispatcher("WEB-INF/jsp/availCases.jsp").include(request, response);
                    } catch (DataException e) {
                        request.getRequestDispatcher("WEB-INF/jsp/404.jsp").include(request, response);

                    }
                    break;
                default:
                    request.getRequestDispatcher("WEB-INF/jsp/" + request.getParameter("page") + ".jsp").include(request, response);

            }

        } catch (ServletException | IOException | DataException ex) {
            return "ohnoes";
        }
        return "0";

    }
}
