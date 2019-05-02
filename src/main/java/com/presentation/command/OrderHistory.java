package com.presentation.command;

import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.LoginException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author niller, martin bøgh
 */
public class OrderHistory extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws LoginException, SQLException, DataException
    {
        PresentationFacade fc = new PresentationFacade(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        int index = (int) request.getAttribute("index");
//        Customer customer = (Customer) session.getAttribute("customer");
        if (index > 0) {
            session.setAttribute("order", fc.getOrder(index));
        }

        return "index";
    }
}
