package com.logic;

import com.data.DAOController;
import com.entities.dto.BillOfMaterials;
import com.entities.dto.Component;
import com.exceptions.DataException;
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
     * @throws DataException if the parameters are invalid or the DAOController
     * provides bad data
     */
    public Map<Component, Integer> convertBOMMap(BillOfMaterials bom, List<Component> componentList) throws DataException
    {
        if (bom == null || bom.getComponents().isEmpty())
        {
            throw new DataException("Invalid parameters!");
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
    
    public void promoteEmployee()
    {
        
    }
    
    public void demoteEmployee()
    {
        
    }
    
    public void createNewEmployee()
    {
        
    }
    
    public void changeCustomerInfo()
    {
        
    }
    
    public void changeComponentData()
    {
        
    }
    
    public void createComponentData()
    {
        
    }
    
}