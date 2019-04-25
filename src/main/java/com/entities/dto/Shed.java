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
public class Shed {

    private int shedId, length, width, height;

    public Shed(int shedId, int length, int width, int height) {
        this.shedId = shedId;
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public Shed(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
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
        int hash = 7;
        hash = 83 * hash + this.shedId;
        hash = 83 * hash + this.length;
        hash = 83 * hash + this.width;
        hash = 83 * hash + this.height;
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
        final Shed other = (Shed) obj;
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
        return "Shed{" + "shedId=" + shedId + ", length=" + length + ", width=" + width + ", height=" + height + '}';
    }

}
