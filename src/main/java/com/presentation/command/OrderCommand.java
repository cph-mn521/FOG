package com.presentation.command;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Carport;
import com.entities.dto.Component;
import com.entities.dto.Customer;
import com.entities.dto.Employee;
import com.entities.dto.Order;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import com.exceptions.LoginException;
import com.exceptions.PDFException;
import com.google.gson.Gson;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.swing.filechooser.FileSystemView;

/**
 *
 * @author martin bøgh
 */
public class OrderCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException, DataException, FormException, PDFException {
        response.setContentType("text/plain;charset=UTF-8");  // Set content type of the response so that jQuery knows what it can expect.

        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        String commandType = (String) request.getParameter("commandType");
        String page = null;

        switch (commandType) {
            case "show":
                page = "showallorders";
                showOrders(pc, session, request);
                break;

            case "prepare":
                page = "changingorder";
                prepareOrder(pc, session, request);
                break;

            case "changed":
                page = "showallorders";
                changedOrder(pc, session, request);
                break;

            case "newform":
                page = "neworder";
                prepareFormOrder(pc, session, request);
                break;

            case "newfinished":
                page = "finishedorder";
                newOrder(pc, session, request);
                break;

            case "remove":
                page = "showallorders";
                removeOrder(pc, session, request);
                break;

            default:
                page = "index";

        }

        try {
            if (page.equals("index")) {
                return "index";
            } else {
                request.getRequestDispatcher("WEB-INF/jsp/" + page + ".jsp").include(request, response);
            }
        } catch (IOException ex) {
            throw new DataException("Problemer med at hente data.");
        } catch (ServletException ex) {
            throw new DataException("Servlet problem.");
        }
        return "succes!";
    }

    public void showOrders(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException {
        List<Order> orders = pc.getAllOrders();
        session.setAttribute("orders", orders);
    }

    public void prepareOrder(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException {
        try {
            int orderID = Integer.parseInt((String) request.getParameter("orderID"));
            if (orderID > 0) {
                Order order = pc.getOrder(orderID);
                session.setAttribute("order", order);
                session.setAttribute("customer", pc.getCustomer(order.getCustomer_id()));
                session.setAttribute("roofs", pc.getAllRoofs());
                Carport carport = pc.getCarport(order.getOrder_id());
                session.setAttribute("roof", pc.getRoof(carport.getRoofTypeId()));
                session.setAttribute("carport", carport);

                BillOfMaterials bom = pc.getBOM(orderID);
                session.setAttribute("orderID", bom.getOrderId());
                Map<Component, Integer> bomme = pc.convertBOMMap(bom);
                session.setAttribute("bomMap", bomme);
            }
        } catch (NumberFormatException ex) {
            throw new DataException("kunne ikke læse ordre ID. " + ex.getMessage());
        }
    }

    public void changedOrder(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException, FormException {
        try {
            String name = (String) request.getParameter("name");
            String rank = (String) request.getParameter("rank");
            String email = (String) request.getParameter("email");
            String phone_number = (String) request.getParameter("phoneNumber");
            Employee oldempl = (Employee) session.getAttribute("employee");
            Employee empl = new Employee(oldempl.getEmployee_id(), oldempl.getName(),
                    oldempl.getPhone_number(), oldempl.getEmail(), oldempl.getPassword(), oldempl.getRank());

            if (empl != null) {
//            Change name
                if (!name.isEmpty()) {
                    empl.setName(name);
                }

//            Change rank
                if (!rank.isEmpty()) {
                    empl.setRank(rank);
                }

//            Change email
                if (!email.isEmpty()) {
                    empl.setEmail(email);
                }

//            Change phone_number
                if (!phone_number.isEmpty()) {
                    empl.setPhone_number(phone_number);
                }
                pc.updateEmployee(oldempl, empl);
            }

            session.setAttribute("employees", pc.getAllEmployees());

        } catch (NumberFormatException ex) {
            throw new FormException("Der skal stå noget i alle felter. ");
        }
    }

    public void prepareFormOrder(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException {
        session.setAttribute("roofs", pc.getAllRoofs());
    }

    private void placeOrder(HttpSession session, HttpServletRequest request) {
        Gson gson = new Gson();
        String json = request.getParameter("JSON");
        Carport C = gson.fromJson(json, Carport.class);
        
        
    }

    public void newOrder(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException, FormException, PDFException {
        try {
            // OBS customer skal hentes et sted fra. 1 er placeholder
            Customer customer = pc.getCustomer(1); //Integer.parseInt((String) request.getParameter("name"));
            String customerAddress = (String) request.getParameter("customerAddress");
            String roofType = (String) request.getParameter("roofTypeID");
            int roofTypeID = Integer.parseInt(roofType.split(":")[0]);
            int cartportLength = Integer.parseInt((String) request.getParameter("cartportLength"));
            int cartportWidth = Integer.parseInt((String) request.getParameter("cartportWidth"));
            int cartportHeight = Integer.parseInt((String) request.getParameter("cartportHeight"));
            int shedLength = Integer.parseInt((String) request.getParameter("shedLength"));
            int shedWidth = Integer.parseInt((String) request.getParameter("shedWidth"));
            int shedHeight = Integer.parseInt((String) request.getParameter("shedHeight"));

            if (customer != null && customer.getCustomer_id() > 0
                    && customerAddress != null && !customerAddress.isEmpty()
                    && roofTypeID > 0
                    && cartportLength > 0
                    && cartportWidth > 0
                    && cartportHeight > 0) {
                String filePath = FileSystemView.getFileSystemView().getHomeDirectory().getPath() + "/FOGStyklistePDF/";
                try
                {
                    Files.createDirectories(Paths.get(filePath));
                }
                catch (IOException ex)
                {
                    throw new PDFException("Fejl i pdf filnavn eller filsti: " + ex.getMessage());
                }
                        
                Order order = pc.createOrder(customer, customerAddress, roofTypeID,
                        cartportLength, cartportWidth, cartportHeight,
                        shedLength, shedWidth, shedHeight, filePath);

                String fileName = "FOGCarportstykliste#" + order.getOrder_id() + "_" + order.getOrder_receive_date().toString();
                session.setAttribute("pdffilename", filePath + fileName + ".pdf");

            } else {
                throw new FormException("Der skal stå noget i alle felter. ");
            }
        } catch (NumberFormatException ex) {
            throw new FormException("Fejl i indtastning");
        }

        session.setAttribute("employees", pc.getAllEmployees());
    }

    public void removeOrder(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException, FormException {
        try {
            int orderID = Integer.parseInt((String) request.getParameter("orderID"));

            if (orderID > 0) {
                pc.deleteOrder(pc.getOrder(orderID));
            } else {
                throw new FormException("Der skal stå noget i alle felter. ");
            }
        } catch (NumberFormatException ex) {
            throw new DataException("kunne ikke læse ordres ID nummer.");
        }
        session.setAttribute("orders", pc.getAllOrders());
    }
}
