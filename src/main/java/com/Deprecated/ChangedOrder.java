package com.presentation.command;

import com.entities.dto.Component;
import com.entities.dto.Order;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class ChangedOrder extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException {
        response.setContentType("text/plain;charset=UTF-8"); 
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try {
            String description = (String) request.getParameter("description");
            String helpText = (String) request.getParameter("helpText");
            int width = Integer.parseInt((String) request.getParameter("width"));
            int height = Integer.parseInt((String) request.getParameter("height"));
            float price = Float.parseFloat((String) request.getParameter("price"));
            Order oOrder = (Order) session.getAttribute("component");
            Order newOrder = new Order(oOrder.getOrder_id(), oOrder.getCustomer_id(), 
                    oOrder.getOrder_receive_date(),oOrder.getOrder_send_date(), oOrder.getCustomer_address(), oOrder.getOrder_status(), oOrder.getTotal_price());

            if (newOrder != null) {

//            Change order
//                if (!description.isEmpty()) {
//                    newOrder.setDescription(description);
//                }
//
//                if (!helpText.isEmpty()) {
//                    newOrder.setHelpText(helpText);
//                }
//
//                if (width != 0) {
//                    newOrder.setWidth(width);
//                }
//
//                if (height != 0) {
//                    newOrder.setHeight(height);
//                }
//
//                if (price != 0) {
//                    newOrder.setPrice(price);
//                }

//                if (deleted != null && deleted.equals("true"))
//                {
//                    session.setAttribute("component", null);
//                    pc.deleteComponent(pc.getComponent(comp.getComponentId()));
//                } else
//                {
                pc.updateOrder(oOrder, newOrder);
//                }
            }

            //session.setAttribute("components", pc.getAllComponents());
            if (newOrder.getOrder_id()> 0) {
                session.setAttribute("component", pc.getOrder(newOrder.getOrder_id()));
            }
              try {
                request.getRequestDispatcher("WEB-INF/jsp/showallcomponents.jsp").include(request, response);
            } catch (ServletException ex) {
                Logger.getLogger(ChangedOrder.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(ChangedOrder.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (NumberFormatException ex) {
            System.out.println("NumberFormatException: " + ex.getMessage());
            return "index";
        }

        return "success";
    }
}
