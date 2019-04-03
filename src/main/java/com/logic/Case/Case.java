/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic.Case;

import java.util.HashMap;

/**
 *
 * @author Martin
 */
public abstract class Case {

    String Description;
    String Type;
    int CaseNumber;

    private static HashMap<String, Case> CaseTypes;

    private static void initCases() {
        CaseTypes = new HashMap<>();
        CaseTypes.put("Order", new Order());
        CaseTypes.put("Packing", new Packing());

    }

    public static Case from(String Type) {
        String CaseType = type;
        if (CaseTypes == null) {
            initCases();
        }
        return CaseTypes.getOrDefault(CaseType, new UnkownCase());
    }

    public void work() {
    }

    public abstract void Close();

    public abstract void ChangeType();

}
