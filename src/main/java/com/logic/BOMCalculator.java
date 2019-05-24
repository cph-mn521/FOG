package com.logic;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Carport;
import com.entities.dto.Roof;
import com.exceptions.DataException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Brandstrup + Niels
 */
public class BOMCalculator {

    /**
     * Retrieves all data from DTO entities and the applicable order id in order
     * to build a bill of material object.
     *
     * @param orderId the orderId to use in the returned object
     * @param carport the Carport object from which to gather data
     * @param roof the Roof object from which to gather data
     * @return a BillOfMaterials DTO entity
     * @throws DataException if one of the parameters are invalid
     * @author Brandstrup
     */
    public BillOfMaterials calculateBOM(int orderId, Carport carport, Roof roof) throws DataException {
        if (orderId < 1 || carport == null || roof == null) {
            throw new DataException("Invalid orderId or objects are null!");
        }
        if (carport.getWidth() < 2400 || carport.getWidth() > 7500
                || carport.getLength() < 2400 || carport.getLength() > 7800
                || carport.getWidth() % 30 > 0 || carport.getLength() % 30 > 0) {
            throw new DataException("Carport object has invalid dimensions!");
        }
        if (roof.getSlant() > 0
                && (roof.getSlant() < 15 || roof.getSlant() > 45 || roof.getSlant() % 5 > 0)) {
            throw new DataException("Roof object has invalid slant value!");
        }

        Map<Integer, Integer> carportMap = calculateCarport(carport);
        Map<Integer, Integer> roofMap = calculateRoof(carport, roof);
        Map<Integer, Integer> shedMap = calculateShed(carport);
        Map<Integer, Integer> components = new HashMap();

        carportMap.forEach((k, v) -> {
            if (components.containsKey(k)) {
                v += components.get(k);
                components.put(k, v);
            } else {
                components.put(k, v);
            }
        });

        roofMap.forEach((k, v) -> {
            if (components.containsKey(k)) {
                v += components.get(k);
                components.put(k, v);
            } else {
                components.put(k, v);
            }
        });

        shedMap.forEach((k, v) -> {
            if (components.containsKey(k)) {
                v += components.get(k);
                components.put(k, v);
            } else {
                components.put(k, v);
            }
        });

        return new BillOfMaterials(orderId, (HashMap) components);
    }

    /**
     * Calculates the components required to build the carport skeleton with
     * the provided dimensions.
     *
     * @param carport the Carport containing the dimension data required
     * @return a Map of the component ids and the value of each of these ids
     * @author Brandstrup
     */
    private Map<Integer, Integer> calculateCarport(Carport carport) {

        int length = carport.getLength();
        int width = carport.getWidth();
        int height = carport.getHeight();
        Map<Integer, Integer> carportMap = new HashMap();

        int id9Number = length / 2000 * 2;      //2 stolper per 2 meter
        int id10Number = 2;                  //2 remme til at holde taget oppe
        int id11Number = id9Number * 2;        //2 bræddebolte per stolpe
        int id12Number = id9Number * 2;        //2 firkantskiver per stolpe
        int id13Number = 8;                  //Højremonteret universalbeslag på remmen til spær
        int id14Number = id13Number;          //tilsvarende venstremonterede universalbeslag
        int id15Number = 1;                  //1 pakke x 250 skruer til beslag

        carportMap.put(9, id9Number);
        carportMap.put(10, id10Number);
        carportMap.put(11, id11Number);
        carportMap.put(12, id12Number);
        carportMap.put(13, id13Number);
        carportMap.put(14, id14Number);
        carportMap.put(15, id15Number);

        //antager at component id:
        //   9 = 97x97	mm. trykimp. Stolpe - til montering på spær
        //  10 = 45x195 spærtræ ubh. - Remme i sider, sadles ned i stolper Carport del
        //  11 = bræddebolt 10 x 120 mm. - Til montering af rem på stolper
        //  12 = firkantskiver 40x40x11mm - Til montering af rem på stolper
        //  13 = universalbeslag 190 mm. højre - til montering af spær på rem
        //  14 = universalbeslag 190 mm. venstre - til montering af spær på rem
        //  15 = 5,0 x 40 mm. beslagskruer 250 stk. - Til montering af universalbeslag + toplægte
        return carportMap;
    }

    /**
     * Method for estimation of roof BoM.
     *
     * Assumes symmetrical roof.
     *
     * @param carport
     * @param roof Object: requires 'Eternit' or 'Betontagsten' type.
     * @return Map with component id and amount required for construction.
     * @author Niels
     */
    private Map<Integer, Integer> calculateRoof(Carport carport, Roof roof) {
        String type = roof.getType();
        int slant = roof.getSlant();
        Map<Integer, Integer> roofMap = new HashMap();

        double cpL, cpW, a, b, c, areal, lathDistance, xLaths, nLaths,
                lægteLængde, lathTotal;
        double edge = 100; //antager 10 centimeters "overhæng".
        cpL = carport.getLength();
        cpW = carport.getWidth();
        lægteLængde = 6600;

        b = cpW / 2;
        a = b * Math.tan(Math.toRadians(slant));
        c = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));

        double roofLength = cpL + edge * 2;
        double roofWidth = (Math.max(b, c) + edge) * 2;

        areal = (roofLength * roofWidth) / 2;
        switch (type) {
            case "Eternittag":
                double plateWidth = 1016;
                double plateLength = 1180 - 134;
                int nPlate = (int) Math.ceil((areal * 2) / ((plateWidth) * plateLength));
                lathDistance = 535;
                xLaths = roofLength / lathDistance;
                lathTotal = xLaths * roofWidth;
                nLaths = (lathTotal * 2) / lægteLængde;
                double nails = (lathTotal * 2) / (147 * 4); // Nail on every 4th top, on all laths.
                int nailPack = (int) Math.ceil(nails);
                roofMap.put(7, nPlate); // Number of eternit plates
                roofMap.put(1, (int) Math.ceil(nLaths)); //Number of laths
                roofMap.put(6, nailPack);
                break;

            case "Betontagsten":
                lathDistance = 325;
                double nPerMM2 = 14.6 / 1000000; //m2 to mm2
                double roofStones = 2 * Math.ceil(areal * nPerMM2);

                xLaths = roofLength / lathDistance;
                lathTotal = xLaths * roofWidth;
                nLaths = (lathTotal * 2) / lægteLængde;

                roofMap.put(1, (int) Math.ceil(nLaths)); //Antal lægter.
                roofMap.put(8, (int) roofStones);
                break;
        }

        /* Der antages at der bruges følgende materialer:
        til eternittag:
        Cembrit tagskrue sortblå 6x100 400stk /pk el. 100stk/pk
        og:
        CEMBRIT B6S FK SORTBLÅ BØLGEPLADE - 1090X1180MM - MODEL 2013
        min 14 deg hældning


        til betontagsten:
        RØDE VINGETAGSTEN GL. DANSK FORBRUG: 14,6 STK/M2 - 6STK/BDT - 144 STK/½PAL - 288 PR PAL.
        Længde: 404 mm.
        Bredde: 236 mm. - OBS! GUL: 229 mm.
        Dækbredde: 207 mm. - OBS! GUL: 201 mm.
        Lægteafstand: 325-331 mm.
        Taghældning ved undertag min. 20°.
        Pr. palle 320/160/8 Naturrød.

         */
        return roofMap;
    }

    /**
     * Part of the main method 'calculateBOM'. This part governs the calculation
     * of components used for the shed.
     * 
     * -- Currently not implemented --
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
