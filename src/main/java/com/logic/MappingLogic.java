package com.logic;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Component;
import com.entities.dto.Order;
import com.exceptions.LogicException;
import com.exceptions.PDFException;
import java.net.URISyntaxException;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Brandstrup
 */
public class MappingLogic
{
    //Navn på klassen er op til diskussion, eller om det er overflødigt at have
    //en hel seperat klasse til de her metoder; skal de bare ligge inde i facaden?

    /**
     * Receives a bill of material object consisting of a HashMap containing the
     * IDs (key) of the Components it contains as well as the amount (value),
     * then converts these integers into a new HashMap containing actual DTOs
     * of these Components (as well as the amount).
     * 
     * @param bom the bill of material object to convert into a usable Map
     * @param componentList the entire list of components contained in the 
     * database to iterate through in order to check a components existence in
     * the provided bill of materials
     * @return a Map<Component, Integer> that is easier to use in presentation
     * @throws LogicException if the parameters are invalid
     */
    public Map<Component, Integer> convertBOMMap(BillOfMaterials bom, List<Component> componentList) throws LogicException
    {
        if (bom == null || bom.getComponents().isEmpty())
        {
            throw new LogicException("Invalid parameters");
        }

        Map<Component, Integer> newBOMMap = new HashMap();
        Map<Integer, Integer> oldBOMMap = bom.getComponents();
        int id;
        int amount;

        for (Component component : componentList)
        {
            id = component.getComponentId();
            if (oldBOMMap.containsKey(id))
            {
                amount = oldBOMMap.get(id);
                if (amount < 1)
                {
                    continue;
                }
                newBOMMap.put(component, amount);
            }
        }

        return newBOMMap;
    }
    
    /**
     * Formats a float value into a string with the format '$$.$$kr.'.
     * 
     * @param totalCost the Float value to format
     * @return the String in the correct format
     * @author Brandstrup
     */
    public String formatTotalCostFloatToString(Float totalCost)
    {
        return String.format("%.2f", totalCost) + "kr.";
    }
    
    /**
     * Saves a complete PDF file to a specified path.
     *
     * @param order the order to which the PDF is associated
     * @param filePath the path to save the PDF file
     * @param bom the BOM<Component, Integer> object to generate a PDF file from
     * @throws PDFException if an error occurs during the generatio of the PDF
     * @author Brandstrup
     */
    public void generatePDFFromOrder(Order order, String filePath, Map<Component, Integer> bom) throws PDFException
    {
        PDFCalculator calc = new PDFCalculator();
        
        int orderId = order.getOrder_id();
        Date orderReceiveDate = order.getOrder_receive_date();
        calc.generatePDFFromBill(bom, "Fog", "FOGCarportstykliste_" + orderId + "_" + orderReceiveDate.toString(), filePath);
    }
}
