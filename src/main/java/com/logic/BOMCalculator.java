/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

import com.entities.dto.BillOfMaterials;
import com.entities.dto.Order;

/**
 *
 * @author Martin Brandstrup
 */
public class BOMCalculator
{
    public final int length;
    public final int width;
    public final int height;
    public final boolean hasShed;
    public final String roofType;

    public BOMCalculator(Order order)
    {
        this.length = order.getCarport().getLength();
        this.width = order.getCarport().getWidth();
        this.height = order.getCarport().getHeight();
        if(order.getShed() == null)
        {
            this.hasShed = false;
        }
        else
        {
            this.hasShed = true;
        }
        this.roofType = order.getRoof().getType();
    }
    
    public BillOfMaterials calculateCarport()
    {
        
    }
    
}
