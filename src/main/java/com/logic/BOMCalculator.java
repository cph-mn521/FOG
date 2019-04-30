package com.logic;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Carport;
import com.entities.dto.Roof;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Brandstrup
 */
public class BOMCalculator
{
    /**
     * Retrieves all data from DTO entities and the applicable order id in 
     * order to build a bill of material object.
     * 
     * @param orderId
     * @param carport
     * @param roof
     * @return a BillOfMaterials DTO entity
     */
    public BillOfMaterials calculateBOM(int orderId, Carport carport, Roof roof)
    {
        Map<Integer, Integer> carportMap = calculateCarport(carport);
        Map<Integer, Integer> roofMap = calculateRoof(carport, roof);
        Map<Integer, Integer> shedMap = null;
        Map<Integer, Integer> components = new HashMap();
        
        if(carport.getShedLength() > 0)
        {
            shedMap = calculateShed(carport);
        }
        
        components.putAll(carportMap);
        components.putAll(roofMap);
        components.putAll(shedMap);
        
        return new BillOfMaterials(orderId, (HashMap) components);
    }
    
    private Map<Integer, Integer> calculateCarport(Carport carport)
    {
        int length = carport.getLength();
        int width = carport.getWidth();
        int height = carport.getHeight();  
        Map<Integer, Integer> carportMap = new HashMap();
        
        int id1Number = length/2000*2;  //2 stolper per 2 meter
        int id2Number = length/550;     //1 tvertagspær per 0,55 meter
        int id3Number = 2;              //2 tagspær til at holde taget oppe
        
        carportMap.put(1, id1Number);
        carportMap.put(2, id2Number);
        carportMap.put(3, id3Number);
        
        //antager at component id:
        //   1 = lodrette stolper
        //   2 = tagspær (på tvers)
        //   3 = tagspær (på langs)
        
        return carportMap;
    }
    
    /**
     * 
     * @param carport
     * @param roof
     * @return 
     */
    private Map<Integer, Integer> calculateRoof(Carport carport, Roof roof)
    {
        String type = roof.getType();
        String version = roof.getVersion();
        String color = roof.getColor();
        int slant = roof.getSlant();
        Map<Integer, Integer> roofMap = new HashMap();
        
        //Code goes here
        
        return roofMap;
    }
    
    /**
     * Part of the main method 'calculateBOM'. This part governs the calculation
     * of components used for the shed.
     * 
     * @param carport
     * @return A HashMap containing all the components for a shed
     */
    private Map<Integer, Integer> calculateShed(Carport carport)
    {
        int length = carport.getShedLength();
        int width = carport.getShedWidth();
        int height = carport.getShedHeight();
        Map<Integer, Integer> shedMap = new HashMap();
        
        //Code goes here
        
        return shedMap;
    }
}
