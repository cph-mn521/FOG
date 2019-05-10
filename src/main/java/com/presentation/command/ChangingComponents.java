package com.presentation.command;

import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class ChangingComponents extends Command
{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, FormException
    {
        PresentationController fc = new PresentationController(DBURL.PRODUCTION);
        HttpSession session = request.getSession();
        try
        {
            int compID= Integer.parseInt((String) request.getParameter("componentID"));
            if (compID > 0)
            {
                session.setAttribute("component", fc.getComponent(compID));
            }
        } catch (NumberFormatException ex)
        {
            throw new FormException("Der skete en fejl ved hentning af materiale");
        }

        return "index";
    }
}