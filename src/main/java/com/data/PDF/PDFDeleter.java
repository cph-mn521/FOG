/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data.PDF;

import java.io.File;

/**
 *
 * @author Brandstrup
 */
public class PDFDeleter
{
    public void deletePDF(String filePath, int orderId)
    {
        File directory = new File(filePath);
        String PDFName = "FOGCarportstykliste_" + orderId;
        for (File f : directory.listFiles())
        {
            if(f.getName().startsWith(PDFName))
            {
                f.delete();
            }
        }
    }
}
