package com.presentation.command;

import com.entities.dto.Order;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author niller, martin bøgh
 */
public class ShowOrders extends Command
{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException
    {
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try
        {
            {
                List<Order> orders = pc.getAllOrders();
                session.setAttribute("orders", orders);
            }
        } catch (DataException ex)
        {
            throw new DataException("Der er sket en fejl ved hentning af ordreliste");
        }
        return "index";
    }
}
