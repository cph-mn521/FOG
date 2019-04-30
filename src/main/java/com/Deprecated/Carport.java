/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic;

/**
 *
 * @author Martin
 */
public class Carport {

    public final int x;
    public final int y;
    public final int z;
    public final String roof;

    /**
     * Method for instanciating a carport object. This is not an entety, and as
     * sutch wil contain logic elementes, and not be a 1-1 DB representation.
     *
     * @param x dimentional att
     * @param y dimentional att
     * @param z dimentional att
     * @param roof int to determine roof type.
     */
    Carport(int x, int y, int z, int roof) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.roof = rooftype(roof);

        // logic goes here
    }

    private String rooftype(int type) {
        switch (type) {
            case 1:
                return "fladt";
            case 2:
                return "telt";
            default:
                return null;
        }
    }

}
