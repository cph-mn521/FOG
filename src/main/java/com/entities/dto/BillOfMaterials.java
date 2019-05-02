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
    
    public BillOfMaterials(int orderId, HashMap components) {
        this.orderId = orderId;
        this.components = components;
    }

    public int getOrderlId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public Map<Integer, Integer> getComponents() {
        return components;
    }

    public void setComponents(Map<Integer, Integer> components) {
        this.components = components;
    }

}
