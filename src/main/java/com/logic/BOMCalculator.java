/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Order;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Martin Brandstrup
 */
public class BOMCalculator
{
    /**
     * Retreives all data from an order in order to build a bill of material
     * seperated into carport, roof and shed (if applicable).
     * 
     * @param order
     * @return A BillOfMaterials DTO entity consisting of three HashMaps
     */
    public BillOfMaterials calculateBOM(Order order)
    {
        boolean hasShed = false;
        Map<Integer, Integer> carportMap = calculateCarport(order);
        Map<Integer, Integer> roofMap = calculateRoof(order);
        Map<Integer, Integer> shedMap = null;
        
        if(order.getShed() != null)
        {
            hasShed = true;
            shedMap = calculateShed(order);
        }
        
        return BillOfMaterials((HashMap) carportMap, (HashMap) shedMap, (HashMap) roofMap);
    }
    
    private Map<Integer, Integer> calculateCarport(Order order)
    {
        int length = order.getCarport().getLength();
        int width = order.getCarport().getWidth();
        int height = order.getCarport().getHeight();  
        Map<Integer, Integer> carportMap = new HashMap();
        
        int id1Number = length/2000*2;
        int id2Number = length/550;
        int id3Number = 2;
        
        carportMap.put(1, id1Number);
        carportMap.put(2, id2Number);
        carportMap.put(3, id3Number);
        
        //antager at component id:
        //   1 = lodrette stolper
        //   2 = tagspær (på tvers)
        //   3 = tagspær (på langs)
        
        return carportMap;
    }
    
    private Map<Integer, Integer> calculateRoof(Order order)
    {
        String type = order.getRoof().getType();
        String version = order.getRoof().getVersion();
        String color = order.getRoof().getColor();
        int slant = order.getRoof().getSlant();
        Map<Integer, Integer> roofMap = new HashMap();
        
        
        
        
        return roofMap;
    }
    
    private Map<Integer, Integer> calculateShed(Order order)
    {
        int length = order.getShed().getLength();
        int width = order.getShed().getWidth();
        int height = order.getRoof().getHeight();
        Map<Integer, Integer> shedMap = new HashMap();
        
        
        return shedMap;
    }
}
