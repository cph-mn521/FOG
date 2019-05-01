package com.presentation.command;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Component;
import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.entities.dto.Order;
import com.exceptions.DataException;
import com.logic.LogicFacade;
import com.presentation.command.Command;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author kasper
 */
@WebServlet(name = "FrontController", urlPatterns
        = {
            "/FrontController"
        })
public class FrontController extends HttpServlet {

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
            throws ServletException, IOException {
        try {
            // Convert to UTF-8
            request.setCharacterEncoding("UTF-8");
            response.setCharacterEncoding("UTF-8");

            Command action = Command.from(request);
            String view = action.execute(request, response);
            if (view.equals("index")) {
                request.getRequestDispatcher("index.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("/WEB-INF/jsp/" + view + ".jsp").forward(request, response);
            }
        } catch (Exception ex) {
            request.setAttribute("error", ex.getMessage());
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

//    public static User login(String email, String pw) throws LoginException
//    {
//        return LogicFacade.login(email, pw);
//    }
    LogicFacade logic = new LogicFacade();

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////CUSTOMER ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Customer getCustomer(String email, String password) throws SQLException, DataException {
        return logic.getCustomer(email, password);
    }

    public void createCustomer(Customer customer) throws SQLException {
        logic.createCustomer(customer);
    }

    public void updateCustomer(Customer customer, Customer newCustomer) throws SQLException {
        logic.updateCustomer(customer, newCustomer);
    }

    public void deleteCustomer(Customer customer) throws SQLException {
        logic.deleteCustomer(customer);
    }

    ///////////////////////////////////////////////////////////////////////////
    /////////////////////////////EMPLOYEE ACTIONS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Employee getEmployee(String email, String password) throws SQLException, DataException {
        return logic.getEmployee(email, password);
    }

    public void createEmployee(Employee employee) throws SQLException {
        logic.createEmployee(employee);
    }

    public void updateEmployee(Employee employee, Employee newEmployee) throws SQLException {
        logic.updateEmployee(employee, newEmployee);
    }

    public void deleteEmployee(Employee employee) throws SQLException {
        logic.deleteEmployee(employee);
    }

    ///////////////////////////////////////////////////////////////////////////
    ///////////////////////////////ORDERMAPPING////////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Order getOrder(int orderId) throws SQLException, DataException {
        return logic.getOrder(orderId);
    }

    public void createOrder(Order order) throws SQLException, DataException {
        logic.createOrder(order);
    }

    public void updateOrder(Order order, Order newOrder) throws SQLException, DataException {
        logic.updateOrder(order, newOrder);
    }

    public void deleteOrder(Order order) throws SQLException, DataException {
        logic.deleteOrder(order);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////BILL OF MATERIALS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public BillOfMaterials getBOM(int bomId) throws SQLException, DataException {
        return logic.getBOM(bomId);
    }

    public void createBOM(BillOfMaterials BOM) throws SQLException, DataException {
        logic.createBOM(BOM);
    }

    public void updateBOM(BillOfMaterials BOM, BillOfMaterials newBOM) throws SQLException, DataException {
        logic.updateBOM(BOM, newBOM);
    }

    public void deleteBOM(BillOfMaterials BOM) throws SQLException, DataException {
        logic.deleteBOM(BOM);
    }

    ///////////////////////////////////////////////////////////////////////////
    ////////////////////////////BILL OF MATERIALS//////////////////////////////
    ///////////////////////////////////////////////////////////////////////////
    public Component getComponent(int ComponentId) throws SQLException, DataException {
        return logic.getComponent(ComponentId);
    }

    public void createComponent(Component Component) throws SQLException, DataException {
        logic.createComponent(Component);
    }

    public void updateComponent(Component Component, Component newComponent) throws SQLException, DataException {
        logic.updateComponent(Component, newComponent);
    }

    public void deleteComponent(Component Component) throws SQLException, DataException {
        logic.deleteComponent(Component);
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
            throws ServletException, IOException {
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
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
