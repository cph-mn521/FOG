package com.entities.dto;

import java.util.Objects;

/**
 *
 * @author Brandstrup
 */
public class Roof
{

    private int roofTypeId, slant;
    private String type, color, version;

    public Roof(int roofTypeId, int slant, String type, String color, String version)
    {
        this.roofTypeId = roofTypeId;
        this.slant = slant;
        this.type = type;
        this.color = color;
        this.version = version;
    }

    public int getRoofTypeId()
    {
        return roofTypeId;
    }

    public void setRoofTypeId(int roofTypeId)
    {
        this.roofTypeId = roofTypeId;
    }

    public int getSlant()
    {
        return slant;
    }

    public void setSlant(int slant)
    {
        this.slant = slant;
    }

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }

    public String getColor()
    {
        return color;
    }

    public void setColor(String color)
    {
        this.color = color;
    }

    public String getVersion()
    {
        return version;
    }

    public void setVersion(String version)
    {
        this.version = version;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.roofTypeId;
        hash = 89 * hash + this.slant;
        hash = 89 * hash + Objects.hashCode(this.type);
        hash = 89 * hash + Objects.hashCode(this.color);
        hash = 89 * hash + Objects.hashCode(this.version);
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
        final Roof other = (Roof) obj;
        if (this.roofTypeId != other.roofTypeId) {
            return false;
        }
        if (this.slant != other.slant) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.color, other.color)) {
            return false;
        }
        if (!Objects.equals(this.version, other.version)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Roof{" + "roofTypeId=" + roofTypeId + ", slant=" + slant + ", type=" + type + ", color=" + color + ", version=" + version + '}';
    }

}
