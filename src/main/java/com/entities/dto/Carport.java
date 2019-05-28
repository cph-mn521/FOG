package com.entities.dto;

/**
 *
 * @author nille & Brandstrup
 */
public class Carport implements java.io.Serializable {

    private int orderId, roofTypeId, length, width, height, shedLength, shedWidth, shedHeight;

    public Carport(int orderId, int roofTypeId, int length, int width, int height, int shedLength, int shedWidth, int shedHeight) {
        this.orderId = orderId;
        this.roofTypeId = roofTypeId;
        this.length = length;
        this.width = width;
        this.height = height;
        this.shedLength = shedLength;
        this.shedWidth = shedWidth;
        this.shedHeight = shedHeight;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getRoofTypeId() {
        return roofTypeId;
    }

    public void setRoofTypeId(int roofTypeId) {
        this.roofTypeId = roofTypeId;
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

    public int getShedLength() {
        return shedLength;
    }

    public void setShedLength(int shedLength) {
        this.shedLength = shedLength;
    }

    public int getShedWidth() {
        return shedWidth;
    }

    public void setShedWidth(int shedWidth) {
        this.shedWidth = shedWidth;
    }

    public int getShedHeight() {
        return shedHeight;
    }

    public void setShedHeight(int shedHeight) {
        this.shedHeight = shedHeight;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + this.orderId;
        hash = 67 * hash + this.roofTypeId;
        hash = 67 * hash + this.length;
        hash = 67 * hash + this.width;
        hash = 67 * hash + this.height;
        hash = 67 * hash + this.shedLength;
        hash = 67 * hash + this.shedWidth;
        hash = 67 * hash + this.shedHeight;
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
        if (this.orderId != other.orderId) {
            return false;
        }
        if (this.roofTypeId != other.roofTypeId) {
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
        if (this.shedLength != other.shedLength) {
            return false;
        }
        if (this.shedWidth != other.shedWidth) {
            return false;
        }
        if (this.shedHeight != other.shedHeight) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Carport{" + "orderId=" + orderId + ", roofTypeId=" + roofTypeId
                + ", length=" + length + ", width=" + width + ", height=" + height
                + ", shedLength=" + shedLength + ", shedWidth=" + shedWidth
                + ", shedHeight" + shedHeight + '}';
    }

}
