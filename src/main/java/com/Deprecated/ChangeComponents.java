package com.presentation.command;

import com.entities.dto.BillOfMaterials;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class ChangeComponents extends Command
{

    @Override
    public String execute(ServletContext context, HttpServletRequest request, HttpServletResponse response) throws DataException, FormException
    {
        PresentationController fc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try
        {
            int index = Integer.parseInt((String) request.getParameter("index"));
            int compID= Integer.parseInt((String) request.getParameter("componentID"));
            if (index > 0)
            {
                BillOfMaterials bom = fc.getBOM(index);
                session.setAttribute("orderID", bom.getOrderId());
                session.setAttribute("bomMap", fc.convertBOMMap(bom));
            }
        } catch (NumberFormatException ex)
        {
            throw new FormException("Indtast et tal");
        }
//        Customer customer = (Customer) session.getAttribute("customer");

        return "index";
    }
}
