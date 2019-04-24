/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.entities.dto.User;
import com.exceptions.LoginException;
import com.logic.LogicFacade;
import com.presentation.command.Command;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kasper
 */
@WebServlet(name = "FrontController", urlPatterns =
{
    "/FrontController"
})
public class FrontController extends HttpServlet
{

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        try
        {
            // Convert to UTF-8
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");
            
            Command action = Command.from(request);
            String view = action.execute(request, response);
            request.getRequestDispatcher("/WEB-INF/jsp/" + view + ".jsp").forward(request, response);
        } catch (Exception ex)
        {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    public static User login(String email, String pw) throws LoginException
    {
        return LogicFacade.login(email, pw);
    }

    public static User createUser(String email, String password1) throws LoginException
    {
        return LogicFacade.createUser(email, password1);
    }

    /**
     * public static void addOrder(Order order) throws DataException {
     * LogicFacade.addOrder(order); }
     *
     * public static ArrayList<BillDTO> getHistory(int id) { ArrayList<BillDTO>
     * hist = new ArrayList<>(); try { hist = LogicFacade.getHistory(id); }
     * catch (DataException E) { E.printStackTrace(); } return hist; }
     *
     *
     *
     *
     */
// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo()
    {
        return "Short description";
    }// </editor-fold>

}
