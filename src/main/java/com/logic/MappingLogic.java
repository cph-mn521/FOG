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
     * @param data the DAOController instance to communicate with the mappers
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
        int key;
        int value;

        for (Component component : componentList)
        {
            key = component.getComponentId();
            if (oldBOMMap.containsKey(key))
            {
                value = oldBOMMap.get(key);
                if (value < 1)
                {
                    continue;
                }
                newBOMMap.put(component, value);
            }
        }

        return newBOMMap;
    }
}
