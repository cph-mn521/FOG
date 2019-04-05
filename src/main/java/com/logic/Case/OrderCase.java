/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.logic.Case;

/**
 *
 * @author Martin
 */
public class OrderCase extends Case {

    private String[] params;

    String Description;
    String Type;
    int CaseNumber;

    @Override
    public void construct(String[] params) {
        this.params = params;
    }

    @Override
    public void Close() {
    }
;
}
