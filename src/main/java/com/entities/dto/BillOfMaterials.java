/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities.dto;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Martin & Niels
 */
public class BillOfMaterials {

    private int billId;
    Map<Integer, Integer> components = new HashMap();

    public BillOfMaterials(int billId, HashMap components) {
        this.billId = billId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public Map<Integer, Integer> getComponents() {
        return components;
    }

    public void setComponents(Map<Integer, Integer> components) {
        this.components = components;
    }
}
