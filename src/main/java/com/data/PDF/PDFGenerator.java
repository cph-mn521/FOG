/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.data.PDF;

import com.entities.dto.Component;
import com.entities.dto.Customer;
import com.entities.dto.Order;
import com.exceptions.LogicException;
import com.exceptions.PDFException;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.logic.MappingLogic;
import com.logic.PDFCalculator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brandstrup
 */
public class PDFGenerator
{
    
    private final PDFCalculator pdfcalc = new PDFCalculator();
    
    /**
     * The method to initialize the generation of the PDF document. Employs
     * several methodsfrom Logic.PDFCalculator to generate each section of the
     * document. Saves a complete PDF file to a specified path.
     *
     * @param BOMStringList a List of Strings containing the data required to
     * fill a bill of materials. Needs to be extracted from a BOM object
     * @param author the author of the document; ie. the person generating it
     * @param fileName the name of the PDF file to save
     * @param filePath the path to save the PDF file
     * @param title the title (file) of the document
     * @param headerTitle the title (header) of the document
     * @param customer the Customer object the PDF is attached to
     * @param order the Order object the PDF is attached to
     * @throws PDFException if an error occurs during the generation of the PDF
     * @author Brandstrup
     */
    public void generatePDFFromBill(java.util.List<String> BOMStringList, 
            String author, String fileName, String filePath, String title, 
            String headerTitle, Customer customer, Order order) throws PDFException
    {
        File file = new File(filePath + fileName + ".pdf");
        Document document = new Document();
        
        try
        {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file));
            HeaderFooterPageEvent event = new HeaderFooterPageEvent();
            writer.setPageEvent(event);
            document.open();
            
            pdfcalc.addMetaData(document, title);

            Paragraph frontpage = pdfcalc.addFrontPageInfo(author, headerTitle, customer, order);
            document.add(frontpage);
            
            document.newPage();
            
            Paragraph bill = pdfcalc.addBillTable(BOMStringList);
            document.add(bill);

            document.close();
        }
        catch (FileNotFoundException ex)
        {
            throw new PDFException("Fejl i generatePDFFromBill. FileNotFoundException");
        }
        catch (IOException ex)
        {
            throw new PDFException("Fejl i generatePDFFromBill. IOException");
        }
        catch (DocumentException ex)
        {
            throw new PDFException("Fejl i generatePDFFromBill. DocumentException");
        }
    }
    
        //Temp main method for testing purposes
//    public static void main(String[] args)
//    {
//        Map<Component, Integer> bom = new HashMap();
//        Random rand = new Random();
//
//        int amountOfEntries = 20; //amount of entries to generate the test file with
//        
//        String[] desc = new String[5];
//        String[] helpt = new String[5];
//        
//        desc[0] = "Røde vingetagsten";
//        desc[1] = "Cembrit tagskrue sortblå 120mm m/skive";
//        desc[2] = "38x73 mm. taglægte T1";
//        desc[3] = "97x97 mm. trykimp. Stolpe";
//        desc[4] = "5,0 x 40 mm. beslagskruer 250 stk.";
//        
//        helpt[0] = "Gl. dansk forbrug: 14,6 stk/m2 - 6 stk/bdt - 144 stk/½pal. - 288 pr pal. lægteafstand: 325mm dækbredde 201";
//        helpt[1] = "Cembrit sortblå tagskrue med skrive, til montering af B6, B7 og B9 bølgeplader";
//        helpt[2] = "Til montering på spær, 7 rækker lægter på hver skiftevis 1 hel & 1 halv lægte";
//        helpt[3] = "Til montering på spær";
//        helpt[4] = "Til montering af universalbeslag + toplægte";
//        
//        float totalPrice = 0;
//        
//        for (int i = 0; i < amountOfEntries; i++)
//        {
//            int dIndex = rand.nextInt(desc.length);
//            int htIndex = rand.nextInt(desc.length);
//            int l = rand.nextInt(9999) + 1;
//            int w = rand.nextInt(9999) + 1;
//            int h = rand.nextInt(9999) + 1;
//            float p = rand.nextFloat() * 100;
//            int a = rand.nextInt(10) + 1;
//            String d = desc[dIndex];
//            String ht = helpt[htIndex];
//            bom.put(new Component(d, ht, l, w, h, p), a);
//            totalPrice += p;
//        }
//
//        String author = "Brandstrup";
//        String fileName = "BillTest";
//        String filePath = "src/main/resources/pdf/";
//        String title = "Stykliste";
//        String headerTitle = "Stykliste for Carport";
//        
//        Customer customer = new Customer(1, "bittie_bertha", "bertha@testmail.com", "1234", "26154895");
//        Order order = new Order(1, 1, Date.valueOf("2019-04-03"),
//                Date.valueOf("2019-04-14"), "Fantasivej 12 Lyngby", "sent", totalPrice);
//        
//
//        try
//        {
//            java.util.List<String> bomStringList = new MappingLogic().stringExtractor(bom);
//            new PDFGenerator().generatePDFFromBill(bomStringList, author, fileName, filePath, title, headerTitle, customer, order);
//        }
//        catch (PDFException | LogicException ex)
//        {
//        }
//    }
}