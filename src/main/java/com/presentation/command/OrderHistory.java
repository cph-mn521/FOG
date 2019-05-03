package com.presentation.command;

import com.entities.dto.BillOfMaterials;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author niller, martin bøgh
 */
public class OrderHistory extends Command
{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException
    {
        PresentationController fc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try
        {
            int index = Integer.parseInt((String) request.getParameter("index"));
            if (index > 0)
            {
//            session.setAttribute("order", fc.getOrder(index));
                BillOfMaterials oldBom = fc.getBOM(index);
                Map<Integer, Integer> oldMap = oldBom.getComponents();
                
                
                Map<String, Integer> bomMap = new HashMap<>();

                for (Map.Entry<Integer, Integer> entry : oldMap.entrySet())
                {
                    bomMap.put(fc.getComponent(entry.getKey()).getDescription(), entry.getValue());
                
                }
                
                session.setAttribute("orderID", oldBom.getOrderId());
                session.setAttribute("bomMap", bomMap);
            }
        } catch (NumberFormatException ex)
        {
            throw new FormException("Indtast et tal");
        }
//        Customer customer = (Customer) session.getAttribute("customer");

        return "index";
    }
}
