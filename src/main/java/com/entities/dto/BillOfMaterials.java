package com.entities.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Martin & Niels & Brandstrup & Martin Bøgh
 */
public class BillOfMaterials {

    private int orderId;
    private Map<Integer, Integer> components = new HashMap();
    
    public BillOfMaterials(int orderId, Map components) {
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

    @Override
    public int hashCode()
    {
        int hash = 5;
        hash = 37 * hash + this.orderId;
        hash = 37 * hash + Objects.hashCode(this.components);
        return hash;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final BillOfMaterials other = (BillOfMaterials) obj;
        if (this.orderId != other.orderId)
        {
            return false;
        }
        if (!Objects.equals(this.components, other.components))
        {
            return false;
        }
        return true;
    }

    @Override
    public String toString()
    {
        return "BillOfMaterials{" + "orderId=" + orderId + ", components=" + components + '}';
    }

    
}
