
import Data.DataException;
import com.data.DAOController;
import com.entities.dto.BillOfMaterials;
import java.sql.SQLException;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
     * @param bom - the bill of materials to calculate the total price of
     * @param data - a data mapper controller to use for extracting price from
     * the component objects from the database
     * @return a float value representing the total price for the entire carport
     * @throws DataException - if one of the parameter objects are null or if an
     * error occurs during calculations
     * @throws SQLException - inherited from the DAOController
     */
    public float calculateOrderPrice(BillOfMaterials bom, DAOController data) throws DataException, SQLException
    {
        if (bom == null || data == null)
        {
            throw new DataException("BIllOfMaterials is null!");
        }
        
        float totalPrice = 0;
        
        for(Map.Entry<Integer, Integer> entry : bom.getComponents().entrySet())
        {
            totalPrice += data.getComponent(entry.getKey()).getPrice()*entry.getValue();
        }
        
        if (totalPrice < 1)
        {
            throw new DataException("Calculation failure!");
        }
        return totalPrice;
    }
}
