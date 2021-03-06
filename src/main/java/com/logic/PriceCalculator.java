package com.logic;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Component;
import com.exceptions.LogicException;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Brandstrup
 */
public class PriceCalculator
{
    
    /**
     * A method to calculate the entire price of a carport through its bill of
     * materials.
     * 
     * @param bom the bill of materials to calculate the total price of
     * @param componentList a list of component used for extracting prices
     * @return a float value representing the total price for the entire carport
     * @throws LogicException if one of the parameter objects are null or if an
     * error occurs during calculations
     * @author Brandstrup
     */
    public float calculateOrderPrice(BillOfMaterials bom, List<Component> componentList) throws LogicException
    {
        if (bom == null)
        {
            throw new LogicException("BIllOfMaterials is null");
        }
        
        Map<Integer, Integer> BOMMap = bom.getComponents();
        int id = 0;
        int amount = 0;
        float price = 0;
        float totalPrice = 0;
        
        for (Component component : componentList)
        {
            id = component.getComponentId();
            if (BOMMap.containsKey(id))
            {
                price = component.getPrice();
                amount = BOMMap.get(id);
                
                totalPrice += price*amount;
                price = 0;
                amount = 0;
            }
        }
        
        if (totalPrice < 0)
        {
            throw new LogicException("Calculation failure");
        }
        return totalPrice;
    }
}
