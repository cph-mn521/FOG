package com.logic;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Carport;
import com.entities.dto.Roof;
import com.exceptions.DataException;
import java.util.HashMap;
import java.util.Map;
import java.lang.Math;

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

        roofMap.forEach((k, v) -> {
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
     *
     * @param carport
     * @return
     */
    private Map<Integer, Integer> calculateCarport(Carport carport) {

        int length = carport.getLength();
        int width = carport.getWidth();
        int height = carport.getHeight();
        Map<Integer, Integer> carportMap = new HashMap();

        int id1Number = length / 2000 * 2;      //2 stolper per 2 meter
        int id2Number = 2;                  //2 remme til at holde taget oppe
        int id3Number = id1Number * 2;        //2 bræddebolte per stolpe
        int id4Number = id1Number * 2;        //2 firkantskiver per stolpe
        int id5Number = 8;                  //Højremonteret universalbeslag på remmen til spær
        int id6Number = id5Number;          //tilsvarende venstremonterede universalbeslag
        int id7Number = 1;                  //1 pakke x 250 skruer til beslag

        carportMap.put(1, id1Number);
        carportMap.put(2, id2Number);
        carportMap.put(3, id3Number);
        carportMap.put(4, id4Number);
        carportMap.put(5, id5Number);
        carportMap.put(6, id6Number);
        carportMap.put(7, id7Number);

        //antager at component id:
        //   1 = 97x97	mm. trykimp. Stolpe - til montering på spær
        //   2 = 45x195	spærtræ	ubh. - Remme i sider, sadles ned i stolper Carport del
        //   3 = bræddebolt 10 x 120 mm. - Til montering af rem på stolper
        //   4 = firkantskiver 40x40x11mm - Til montering af rem på stolper
        //   5 = universalbeslag 190 mm. højre - til montering af spær på rem
        //   6 = universalbeslag 190 mm. venstre - til montering af spær på rem
        //   7 = 5,0 x 40 mm. beslagskruer 250 stk. - Til montering af universalbeslag + toplægte
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

        double cpL, cpW, a, b, c, areal;
        double edge = 100; //antager 10 centimeters "overhæng".
        cpL = carport.getLength();
        cpW = carport.getWidth();

        b = cpW / 2;
        a = b * Math.tan(slant);
        c = Math.sqrt((Math.pow(a, 2) + Math.pow(b, 2)));

        areal = slant > 0 ? (c + edge) * (cpL + edge) : (b + edge) * (cpL + edge);
        double roofLength = Math.max(b, c) + edge;
        double roofWidth = cpW + edge * 2;

        int tileSize = 100; // assuming 10
        switch (type) {
            case "eternit":
                break;
            case "betontagsten":
                double lægteafstand = 325;
                double lægter = roofLength / lægteafstand;
                int cover = 201;
                double nWidth = roofWidth / cover;

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
        //lægtelængde = cpL, lægte placeringsafstand afhængig af components. & tagtype.
        // tag længde (c) + overhæng.
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
