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
 * @author Martin & Niels & Brandstrup
 */
public class BillOfMaterials {

    private int orderId;
    private Map<Integer, Integer> components = new HashMap();
    
    public BillOfMaterials(int billId, HashMap components) {
        this.orderId = billId;
        this.components = components;
    }

    public int getBillId() {
        return orderId;
    }

    public void setBillId(int billId) {
        this.orderId = billId;
    }

    public Map<Integer, Integer> getComponents() {
        return components;
    }

    public void setComponents(Map<Integer, Integer> components) {
        this.components = components;
    }

}
