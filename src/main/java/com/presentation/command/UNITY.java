/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.presentation.command;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Carport;
import com.entities.dto.Order;
import com.entities.dto.Roof;
import com.enumerations.DBURL;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Martin
 */
public class UNITY extends Command{

    @Override
    String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        PresentationController PC = new PresentationController(DBURL.PRODUCTION);
        int order = Integer.parseInt(request.getParameter("order"));
        Carport Carp = PC.getCarport(order);
        Roof roof = PC.getRoof(Carp.getRoofTypeId());
        
        String dataString = Carp.getLength()+","+Carp.getWidth()+","+Carp.getHeight()+","+
                roof.getSlant()+","+Carp.getShedLength()+","+Carp.getShedWidth();
        
        response.getWriter().write(dataString);
        return "o"; 
    }
    
}
