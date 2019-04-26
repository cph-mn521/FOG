/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities.dto;

/**
 *
 * @author nille
 */
public class Carport {

    int carportId, billId, roofTypeId, shedId, length, width, height;

    public Carport(int carportId, int billId, int roofTypeId, int shedId, int length, int width, int height) {
        this.carportId = carportId;
        this.billId = billId;
        this.roofTypeId = roofTypeId;
        this.shedId = shedId;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Carport(int billId, int roofTypeId, int shedId, int length, int width, int height) {
        this.billId = billId;
        this.roofTypeId = roofTypeId;
        this.shedId = shedId;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int getCarportId() {
        return carportId;
    }

    public void setCarportId(int carportId) {
        this.carportId = carportId;
    }

    public int getBillId() {
        return billId;
    }

    public void setBillId(int billId) {
        this.billId = billId;
    }

    public int getRoofTypeId() {
        return roofTypeId;
    }

    public void setRoofTypeId(int roofTypeId) {
        this.roofTypeId = roofTypeId;
    }

    public int getShedId() {
        return shedId;
    }

    public void setShedId(int shedId) {
        this.shedId = shedId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.carportId;
        hash = 67 * hash + this.billId;
        hash = 67 * hash + this.roofTypeId;
        hash = 67 * hash + this.shedId;
        hash = 67 * hash + this.length;
        hash = 67 * hash + this.width;
        hash = 67 * hash + this.height;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Carport other = (Carport) obj;
        if (this.carportId != other.carportId) {
            return false;
        }
        if (this.billId != other.billId) {
            return false;
        }
        if (this.roofTypeId != other.roofTypeId) {
            return false;
        }
        if (this.shedId != other.shedId) {
            return false;
        }
        if (this.length != other.length) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Carport{" + "carportId=" + carportId + ", billId=" + billId + ", roofTypeId=" + roofTypeId + ", shedId=" + shedId + ", length=" + length + ", width=" + width + ", height=" + height + '}';
    }

}
