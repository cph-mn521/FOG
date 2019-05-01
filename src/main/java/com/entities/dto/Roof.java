package com.entities.dto;

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

}
