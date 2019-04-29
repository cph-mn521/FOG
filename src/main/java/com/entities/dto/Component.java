/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entities.dto;

import java.util.Objects;

/**
 *
 * @author nille
 */
public class Component {

    String description, helpText;
    int length, width, height, componentId;
    float price;

    public Component(String description, String helpText, int length, int width, int height, float price) {
        this.description = description;
        this.helpText = helpText;
        this.length = length;
        this.width = width;
        this.height = height;
        this.price = price;
    }

    public Component(int componentId, String description, String helpText, int length, int width, int height, float price) {
        this.componentId = componentId;
        this.description = description;
        this.helpText = helpText;
        this.length = length;
        this.width = width;
        this.height = height;
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getHelpText() {
        return helpText;
    }

    public void setHelpText(String helpText) {
        this.helpText = helpText;
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

    public int getComponentId() {
        return componentId;
    }

    public void setComponentId(int componentId) {
        this.componentId = componentId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.description);
        hash = 23 * hash + Objects.hashCode(this.helpText);
        hash = 23 * hash + this.length;
        hash = 23 * hash + this.width;
        hash = 23 * hash + this.height;
        hash = 23 * hash + this.componentId;
        hash = 23 * hash + (int) (Double.doubleToLongBits(this.price) ^ (Double.doubleToLongBits(this.price) >>> 32));
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
        final Component other = (Component) obj;
        if (this.length != other.length) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.componentId != other.componentId) {
            return false;
        }
        if (Double.doubleToLongBits(this.price) != Double.doubleToLongBits(other.price)) {
            return false;
        }
        if (!Objects.equals(this.description, other.description)) {
            return false;
        }
        if (!Objects.equals(this.helpText, other.helpText)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Component{" + "description=" + description + ", helpText=" + helpText + ", length=" + length + ", width=" + width + ", height=" + height + ", componentId=" + componentId + ", price=" + price + '}';
    }

}
