package com.presentation.command;

import com.entities.dto.Carport;
import com.entities.dto.Roof;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.PresentationException;
import static com.presentation.command.CaseAction.getBody;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author martin bøgh
 */
public class ShowDrawing extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException, PresentationException
    {
        PresentationController pc = new PresentationController(DBURL.PRODUCTION);
//        PresentationController fc = new PresentationController(DBURL.PRODUCTION);
//        HttpSession session = request.getSession();
        try {
//            int compID= Integer.parseInt((String) request.getParameter("componentID"));
//            if (compID > 0)
//            {
//                session.setAttribute("component", fc.getComponent(compID));
//            }
        String body = getBody(request);
        
        if(!(body.length()<1)){
            Carport C = pc.getCarport(Integer.parseInt(body));
            Roof R = pc.getRoof(C.getRoofTypeId());
            request.getSession().setAttribute("carport", C);
            request.getSession().setAttribute("roof", R);
        }
        
        request.getRequestDispatcher("WEB-INF/jsp/showdrawing.jsp").include(request, response);

        } catch (NumberFormatException | IOException ex) {
            throw new PresentationException("kunne ikke se tegning" + ex.getMessage());
        } catch (ServletException ex) {
            throw new PresentationException("Problemer i servlet." + ex.getMessage());
        }
        return "w";
    }
}
