/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic.Case;

import java.util.HashMap;

/**
 *
 * @author Martin Wulff
 */
public abstract class Case {

    private static HashMap<String, Case> CaseTypes;

    private static void initCases() {
        CaseTypes = new HashMap<>();
        CaseTypes.put("order", new OrderCase());
        CaseTypes.put("packing", new PackingCase());

    }

    abstract void construct(String[] params);

    public static Case from(String Type, String[] params) {
        String CaseType = Type;
        if (CaseTypes == null) {
            initCases();
        }
        Case c = CaseTypes.getOrDefault(CaseType, new UnknownCase());
        c.construct(params);
        return c;
    }

    public void work() {
    }

    public abstract void Close();

    public void ChangeType() {
    }
;

}
