/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Carport;
import com.entities.dto.Roof;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Martin Brandstrup
 */
public class BOMCalculator
{
    /**
     * Retrieves all data from DTO entities in order to build a bill of material
     * separated into carport, roof and shed (if applicable).
     * 
     * @param carport
     * @param roof
     * @return A BillOfMaterials DTO entity consisting of three HashMaps
     */
    public BillOfMaterials calculateBOM(Carport carport, Roof roof)
    {
        Map<Integer, Integer> carportMap = calculateCarport(carport);
        Map<Integer, Integer> roofMap = calculateRoof(carport, roof);
        Map<Integer, Integer> shedMap = null;
        
        if(carport.getShedLength() > 0 && carport.getShedLength() != null)
        {
            shedMap = calculateShed(carport);
        }
        
        return BillOfMaterials((HashMap) carportMap, (HashMap) shedMap, (HashMap) roofMap);
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
    
    private Map<Integer, Integer> calculateRoof(Carport carport, Roof roof)
    {
        String type = roof.getType();
        String version = roof.getVersion();
        String color = roof.getColor();
        int slant = roof.getSlant();
        Map<Integer, Integer> roofMap = new HashMap();
        
        
        
        
        return roofMap;
    }
    
    private Map<Integer, Integer> calculateShed(Carport carport)
    {
        int length = carport.getShedLength();
        int width = carport.getShedWidth();
        int height = carport.getShedHeight();
        Map<Integer, Integer> shedMap = new HashMap();
        
        
        return shedMap;
    }
}
