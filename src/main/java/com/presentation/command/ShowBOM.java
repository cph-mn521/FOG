package com.presentation.command;

import com.entities.dto.BillOfMaterials;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author niller, martin bøgh
 */
public class ShowBOM extends Command
{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException
    {
        PresentationController fc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try
        {
            int orderID = Integer.parseInt((String) request.getParameter("orderID"));
            if (orderID > 0)
            {
                BillOfMaterials bom = fc.getBOM(orderID);
                session.setAttribute("orderID", bom.getOrderId());
                session.setAttribute("bomMap", fc.convertBOMMap(bom));
            }
        } catch (NumberFormatException ex)
        {
            throw new FormException("Der er sket en fejl ved hentning af ordre (id.sb1)");
        }

        return "index";
    }
}
