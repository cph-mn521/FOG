package com.presentation.command;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Carport;
import com.entities.dto.Component;
import com.entities.dto.Customer;
import com.entities.dto.Order;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import com.exceptions.LoginException;
import com.exceptions.PDFException;
import com.google.gson.Gson;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Command execution regarding all Order pages
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

            case "showorder":
                page = "showallorders";
                showOrder(pc, session, request);
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

            case "ordersent":
                page = "showallorders";
                orderSent(pc, session, request);
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

    /**
     * Command preparing session objects when commmand showOrders is used
     *
     * @param pc
     * @param session
     * @param request
     * @throws LoginException
     * @throws DataException
     */
    public void showOrders(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException {
        List<Order> orders = pc.getAllOrders();
        session.setAttribute("orders", orders);
    }

    /**
     * Command preparing session objects when commmand showOrder is used
     *
     * @param pc
     * @param session
     * @param request
     * @throws LoginException
     * @throws DataException
     */
    public void showOrder(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException {
        try {
            int orderID = (int) session.getAttribute("odrerID"); //session attribut stavet sådan
            Order order = pc.getOrder(orderID);
            List<Order> orders = new ArrayList();
            orders.add(order);
            session.setAttribute("orders", orders);
        } catch (NumberFormatException ex) {
            throw new DataException("kunne ikke læse ordre ID.");
        }
    }

    /**
     * Command preparing session objects when commmand prepareOrder is used
     *
     * @param pc
     * @param session
     * @param request
     * @throws LoginException
     * @throws DataException
     */
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

                session.setAttribute("bomUnconverted", bom);
                Map<Component, Integer> bomme = pc.convertBOMMap(bom);
                session.setAttribute("bomMap", bomme);

//              getting the tomcat root folder
                String filePath = getDownloadFolder();
                pc.generatePDFFromOrder(order, filePath);
                String fileName = "FOGCarportstykliste_" + order.getOrder_id() + "_" + order.getOrder_receive_date().toString();
                session.setAttribute("pdffilename", fileName + ".pdf");

            }
        } catch (NumberFormatException ex) {
            throw new DataException("kunne ikke læse ordre ID.");
        } catch (PDFException ex) {
            throw new DataException("kunne ikke oprette PDF file af stykliste");
        }
    }

    /**
     * Command preparing session objects when commmand changeOrder is used
     *
     * @param pc
     * @param session
     * @param request
     * @throws LoginException
     * @throws DataException
     * @throws FormException
     */
    public void changedOrder(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException, FormException {
        try {

//          Changed order values
            float totalPrice = Float.parseFloat((String) request.getParameter("totalPrice"));

            Order oldOrder = (Order) session.getAttribute("order");
            Order newOrder = new Order(oldOrder.getOrder_id(), oldOrder.getOrder_receive_date(),
                    oldOrder.getOrder_send_date(), oldOrder.getCustomer_address(), oldOrder.getOrder_status(), oldOrder.getTotal_price());

            // changing order values in DB
            if (newOrder.getOrder_id() > 0) {

//            Change totalPrice
                if (totalPrice > 0.0) {
                    newOrder.setTotal_price(totalPrice);
                }
            }

            pc.updateOrder(oldOrder, newOrder);

            session.setAttribute("orders", pc.getAllOrders());
            session.setAttribute("order", newOrder);

        } catch (NumberFormatException ex) {
            throw new FormException("Der skal stå noget i alle felter, og tal i tal rubrikker");

        }
    }

    /**
     * Command preparing session objects when commmand prepareFormOrder is used
     *
     * @param pc
     * @param session
     * @param request
     * @throws LoginException
     * @throws DataException
     */
    public void prepareFormOrder(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException {
        session.setAttribute("roofs", pc.getAllRoofs());
    }

    /**
     *
     * @param session
     * @param request
     */
    private void placeOrder(HttpSession session, HttpServletRequest request) {
        Gson gson = new Gson();
        String json = request.getParameter("JSON");
        Carport C = gson.fromJson(json, Carport.class);

    }

    /**
     * Command preparing session objects when commmand newOrder is used
     *
     * @param pc
     * @param session
     * @param request
     * @throws LoginException
     * @throws DataException
     * @throws FormException
     * @throws PDFException
     */
    public void newOrder(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException, FormException, PDFException {
        try {
            Customer customer = (Customer) session.getAttribute("customer");
            if (customer == null) {
                customer = pc.getCustomer(1); // Fog Standard user
            }
            String customerAddress = (String) request.getParameter("customerAddress");
            String roofType = (String) request.getParameter("roofTypeID");
            int roofTypeID = Integer.parseInt(roofType.split(":")[0]);
            int cartportLength = Integer.parseInt((String) request.getParameter("cartportLength"));
            int cartportWidth = Integer.parseInt((String) request.getParameter("cartportWidth"));
            int cartportHeight = Integer.parseInt((String) request.getParameter("cartportHeight"));

            int shedLength = Integer.parseInt((String) request.getParameter("shedLength"));// Value = 0, Shed is future upgrade
            int shedWidth = Integer.parseInt((String) request.getParameter("shedWidth"));//   Value = 0, Shed is future upgrade
            int shedHeight = Integer.parseInt((String) request.getParameter("shedHeight"));// Value = 0, Shed is future upgrade

            String msg = (String) request.getParameter("msg");

            if (customer != null && customer.getCustomer_id() > 0
                    && customerAddress != null && !customerAddress.isEmpty()
                    && roofTypeID > 0
                    && cartportLength > 0
                    && cartportWidth > 0
                    && cartportHeight > 0) {

//              getting the tomcat root folder
                String filePath = getDownloadFolder();

                try {
                    Files.createDirectories(Paths.get(filePath));
                } catch (IOException ex) {
                    throw new PDFException("Fejl i pdf filnavn eller filsti.");
                }

                Order order = pc.createOrder(customer, customerAddress, roofTypeID,
                        cartportLength, cartportWidth, cartportHeight,
                        shedLength, shedWidth, shedHeight, filePath);

//              getting the tomcat root folder
                pc.generatePDFFromOrder(order, filePath);
                String fileName = "FOGCarportstykliste_" + order.getOrder_id() + "_" + order.getOrder_receive_date().toString();
                session.setAttribute("pdffilename", fileName + ".pdf");

            } else {
                throw new FormException("Der skal stå noget i alle felter. ");
            }
        } catch (NumberFormatException ex) {
            throw new FormException("Fejl i indtastning");
        }

        session.setAttribute("employees", pc.getAllEmployees());
    }

    /**
     *
     * Command preparing session objects when commmand removeOrder is used
     *
     * @param pc
     * @param session
     * @param request
     * @throws LoginException
     * @throws DataException
     * @throws FormException
     */
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

    /**
     * Command preparing session objects when commmand orderSent is used
     *
     * @param pc
     * @param session
     * @param request
     * @throws LoginException
     * @throws DataException
     * @throws FormException
     */
    public void orderSent(PresentationController pc,
            HttpSession session, HttpServletRequest request)
            throws LoginException, DataException, FormException {
        try {
            int orderID = Integer.parseInt((String) request.getParameter("orderID"));

            if (orderID > 0) {
                pc.markOrderAsSent(orderID);
            } else {
                throw new FormException("Der skal stå noget i alle felter. ");
            }
        } catch (NumberFormatException ex) {
            throw new DataException("kunne ikke læse ordres ID nummer.");
        }
        session.setAttribute("orders", pc.getAllOrders());
    }

    /**
     * The following if-construct was necessary because of
     * System.getProperty("user.dir") will not show you Netbeans project folder
     * (as it normally do), but instead the tomcat install folder, while that's
     * being used.
     *
     * @param userPath - resulat of getDownloadFolder
     * @return path of tomcat root folder (/ on digital ocean server)
     */
    private String getDownloadFolder() {
        String userPath = System.getProperty("user.dir");
        if ("/".equals(userPath)) { //deployed on digital ocean
            return "/opt/tomcat/webapps/FOG/pdf/";
        } else if ("/home/martin/Programmer/apache-tomcat-8.0.27/bin".equals(userPath)) { // dev Bøgh's folders
            return "/home/martin/NetBeansProjects/FOG/src/main/webapp/pdf/";
        }
        return "";
    }
}
