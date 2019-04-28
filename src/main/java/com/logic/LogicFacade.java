/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import com.data.DAOController;
import com.entities.dto.BillOfMaterials;
import com.entities.dto.User;
import com.exceptions.DataException;
import com.exceptions.LoginException;
import java.sql.SQLException;

/**
 *
 * @author Martin, Martin Bøgh, Martin Brandstrup
 */
public class LogicFacade {

    private static LogicFacade instance = null;

    //Brug den her linje i alle classes der skal kende til LogigFacade
    //Derefter kan man få adgang til metoderne ved at skrive 'Logic.??'
    private final LogicFacade Logic = LogicFacade.getInstance();

    private LogicFacade() {
    }

    public synchronized static LogicFacade getInstance() {
        if (instance == null) {
            instance = new LogicFacade();
        }
        return instance;
    }

    static DAOController DataCtrl = new DAOController();

    public static User login(String email, String password) throws LoginException {
        try {
            return DataCtrl.getCustomer(email, password);
        } catch (SQLException | DataException ex) {
            throw new LoginException("User not found");
        }
    }

    // Commented out because of imminent meating
//    public static User createUser(String email, String password) throws LoginException {
//        try {
//            User user = new User(email, password);
//            DataCtrl.createUser(user);
//            return user;
//        } catch (SQLException ex) {
//            throw new LoginException(ex.getMessage());
//        }
//    }

    /**
     * Communicates with the Data layer to gather information about an order in
     * order to calculate, create and persist a bill of materials to the DB.
     * 
     * @param orderId 
     */
    public void persistBOM(int orderId)
    {
        BOMCalculator calc = new BOMCalculator();
        
        int roofId = DataCtrl.getCarport(orderId).getRoofTypeId();
        Carport carport = DataCtrl.getCarport(orderId);
        Roof roof = DataCtrl.getRoof(roofId);
        BillOfMaterials bill = calc.calculateBOM(carport, roof);
                
        try
        {
            DataCtrl.createBOM(bill);
        }
        catch (DataException | SQLException ex)
        {
            //??? Hvordan og hvor skal exceptionsne håndteres?
        }
    }
}
