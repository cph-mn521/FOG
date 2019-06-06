package com.logic;

import com.data.PDF.PDFGenerator;
import com.entities.dto.BillOfMaterials;
import com.entities.dto.Component;
import com.entities.dto.Order;
import com.exceptions.LogicException;
import com.exceptions.PDFException;
import java.sql.Date;
import java.util.ArrayList;
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
     * @throws LogicException if an error occurs in the logic layer
     * @author Brandstrup
     */
    public void generatePDFFromOrder(Order order, String filePath, Map<Component, Integer> bom) throws PDFException, LogicException
    {
        PDFGenerator calc = new PDFGenerator();
        List<String> BOMStringList = stringExtractor(bom);
        
        String customerAddress = order.getCustomer_address();
        int orderId = order.getOrder_id();
        Date orderReceiveDate = order.getOrder_receive_date();
        calc.generatePDFFromBill(BOMStringList, "Fog", "FOGCarportstykliste_" 
                + orderId + "_" + orderReceiveDate.toString(),
                filePath, "Stykliste", "Stykliste for Carport", orderId, customerAddress);
    }
    
    /**
     * Takes a HashMap<Component, Integer> and formats them into usable Strings
     * that can be used for presentation.
     *
     * @param bom the Map from which to extract data
     * @return an List of Strings formatted to be presented
     * @throws LogicException if the provided Map is empty
     * @author Brandstrup
     */
    public List<String> stringExtractor(Map<Component, Integer> bom) throws LogicException
    {
        if(bom.isEmpty() || bom.size() < 1)
        {
            throw new LogicException("Map is empty!");
        }

        List<String> data = new ArrayList();

        bom.forEach((Component k, Integer v) ->
        {
            String[] dimensions = new String[3];
            dimensions[0] = Integer.toString(k.getLength());
            dimensions[1] = Integer.toString(k.getWidth());
            dimensions[2] = Integer.toString(k.getHeight());
            //formats and rounds the price to 2 decimals
            String price = String.format("%.2f", k.getPrice()) + "kr.";
            String amount = Integer.toString(v);

            //formats the strings of the dimensions so that it shows 4520 as 4,520m
            for (int i = 0; i < dimensions.length; i++)
            {
                StringBuilder builder = new StringBuilder(dimensions[i]);
                switch(dimensions[i].length())
                {
                    case 5:
                        builder.insert(2, ",");
                        builder.append("m");
                        break;
                    case 4:
                        builder.insert(1, ",");
                        builder.append("m");
                        break;
                    case 3:
//                        builder.insert(2, ",");
                        builder.append("mm");
                        break;
                    case 2:
//                        builder.insert(0, "0,0");
                        builder.append("mm");
                        break;
                    case 1:
//                        builder.insert(0, "0,00");
                        builder.append("mm");
                        break;
                }
                dimensions[i] = builder.toString();
            }

            data.add(k.getDescription());
            data.add(k.getHelpText());
            data.add(dimensions[0]);    //length
            data.add(dimensions[1]);    //width 
            data.add(dimensions[2]);    //height
            data.add(price);
            data.add(amount);
        });

        return data;
    }
}
