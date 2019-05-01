package com.logic;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Carport;
import com.entities.dto.Roof;
import com.exceptions.DataException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Brandstrup
 */
public class BOMCalculator {

    /**
     * Retrieves all data from DTO entities and the applicable order id in order
     * to build a bill of material object.
     *
     * @param orderId the orderId to use in the reutrned object
     * @param carport the Carport object from which to gather data
     * @param roof the Roof object from which to gather data
     * @return a BillOfMaterials DTO entity
     * @throws DataException - if one of the parameters are invalid
     */
    public BillOfMaterials calculateBOM(int orderId, Carport carport, Roof roof) throws DataException {
        if (orderId < 1 || carport == null || roof == null) {
            throw new DataException("Invalid orderId or objects are null!");
        }
        if (carport.getWidth() < 2400 || carport.getWidth() > 7500 || 
            carport.getLength() < 2400 || carport.getLength() > 7800 || 
            carport.getWidth() % 30 > 0 || carport.getLength() % 30 > 0)
        {
            throw new DataException("Carport object has invalid dimensions!");
        }
        if (roof.getSlant() > 0 && 
           (roof.getSlant() < 15 || roof.getSlant() > 45 || roof.getSlant() % 5 > 0))
        {
            throw new DataException("Roof object has invalid slant value!");
        }
        
        Map<Integer, Integer> carportMap = calculateCarport(carport);
        Map<Integer, Integer> roofMap = calculateRoof(carport, roof);
        Map<Integer, Integer> shedMap = calculateShed(carport);
        Map<Integer, Integer> components = new HashMap();

        
        components.forEach((k,v)->{
            if (carportMap.containsKey(k))
            {
                v += carportMap.get(k);
            }
            else
            {
                components.put(k, carportMap.get(k));
            }
        });
        
        components.forEach((k,v)->{
            if (roofMap.containsKey(k))
            {
                v += roofMap.get(k);
            }
            else
            {
                components.put(k, roofMap.get(k));
            }
        });
        
        components.forEach((k,v)->{
            if (shedMap.containsKey(k))
            {
                v += shedMap.get(k);
            }
            else
            {
                components.put(k, shedMap.get(k));
            }
        });
        
        return new BillOfMaterials(orderId, (HashMap) components);
    }
    
    /**
     * 
     * @param carport
     * @return 
     */
    private Map<Integer, Integer> calculateCarport(Carport carport)
    {

        int length = carport.getLength();
        int width = carport.getWidth();
        int height = carport.getHeight();
        Map<Integer, Integer> carportMap = new HashMap();


        int id1Number = length / 2000 * 2;  //2 stolper per 2 meter
        int id2Number = length / 550;     //1 tvertagsp�r per 0,55 meter
        int id3Number = 2;              //2 tagsp�r til at holde taget oppe


        carportMap.put(1, id1Number);
        carportMap.put(2, id2Number);
        carportMap.put(3, id3Number);

        //antager at component id:

        //   1 = 97x97	mm. trykimp. Stolpe
        //   2 = 38x73	mm. taglægte T1
        //   3 = 45x195	spærtræ	ubh.
        
        
>
        return carportMap;
    }

    /**
     *
     * @param carport
     * @param roof
     * @return
     */
    private Map<Integer, Integer> calculateRoof(Carport carport, Roof roof) {
        String type = roof.getType();
        String version = roof.getVersion();
        String color = roof.getColor();
        int slant = roof.getSlant();
        Map<Integer, Integer> roofMap = new HashMap();

        //Code goes here½
        return roofMap;
    }

    /**
     * Part of the main method 'calculateBOM'. This part governs the calculation
     * of components used for the shed.
     *
     * @param carport
     * @return A HashMap containing all the components for a shed
     */
    private Map<Integer, Integer> calculateShed(Carport carport) {
        int length = carport.getShedLength();
        int width = carport.getShedWidth();
        int height = carport.getShedHeight();
        Map<Integer, Integer> shedMap = new HashMap();

        //Code goes here
        return shedMap;
    }
}
