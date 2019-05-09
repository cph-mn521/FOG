package com.logic;

import com.entities.dto.Component;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import java.io.FileOutputStream;
import java.util.Date;

import com.itextpdf.text.Anchor;
import com.itextpdf.text.BadElementException;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chapter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Section;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPRow;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Brandstrup
 */
public class PDFCalculator
{

    private String FILE = "src/main/webapp/pdf/Bill.pdf";
    private Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private Font verySmall = new Font(Font.FontFamily.TIMES_ROMAN, 11,
            Font.NORMAL);

    
    //Temp main method for testing purposes
    public static void main(String[] args)
    {
        String author = "Brandstrup";

        Map<Component, Integer> bom = new HashMap();
        Random rand = new Random();

        for (int i = 0; i < 5; i++)
        {
            int l = rand.nextInt(9999) + 1;
            int w = rand.nextInt(9999) + 1;
            int h = rand.nextInt(9999) + 1;
            float p = rand.nextFloat() * 100;
            int a = rand.nextInt(10) + 1;
            bom.put(new Component("38x57mm T1 Lægte Stemplet og godkendt til tag",
                    "Max afstand 32cm.", l, w, h, p), a);
        }

        new PDFCalculator().generatePDF(bom, author);
    }

    /**
     * The main method to initialize the generation of the PDF document. Employs
     * several private methods to generate each section of the document.
     * 
     * @param bom the Bill of Materials Map containing the data required
     * @param author the author of the document; ie. the person generating it
     */
    public void generatePDF(Map<Component, Integer> bom, String author)
    {
//        LogicFacade ctrl = new LogicFacade(DBURL.TEST);

        Document document = new Document();
        String title = "Stykliste";
//        java.util.List<String> stringList = ctrl.stringExtractor(bom);
        java.util.List<String> stringList = stringExtractor(bom);

        try
        {
            PdfWriter.getInstance(document, new FileOutputStream(FILE));
            document.open();
            addMetaData(document, title);
            addBill(document, author, stringList);
            document.close();
        }
        catch (FileNotFoundException | DocumentException ex)
        {

        }
    }

    /**
     * Takes a HashMap<Component, Integer> and formats them into usable Strings
     * that can be used for presentation.
     *
     * @param bom the Map from which to extract data
     * @return an List of Strings formatted to be presented
     * @author Brandstrup
     */
    public java.util.List<String> stringExtractor(Map<Component, Integer> bom)
    {
        if (bom.isEmpty() || bom.size() < 1)
        {
            throw new IllegalArgumentException("Map is empty!");
        }

        java.util.List<String> data = new ArrayList();

        bom.forEach((k, v) ->
        {
            String[] dimensions = new String[3];
            dimensions[0] = Integer.toString(k.getLength());
            dimensions[1] = Integer.toString(k.getWidth());
            dimensions[2] = Integer.toString(k.getHeight());
            //formats and rounds the price to 2 decimals
            String price = String.format("%.2f", k.getPrice()) + "kr.";
            String amount = Integer.toString(v);

            //formats the strings of the dimensions so that it shows 4520 as 4,520m
            for (String s : dimensions)
            {
                s = s.substring(0, s.length() - 2) + ","
                        + s.substring(s.length() - 2) + "m";
            }

            data.add(k.getDescription());
            data.add(k.getHelpText());
            data.add(dimensions[0]);    //length
            data.add(dimensions[1]);    //width 
            data.add(dimensions[2]);    //height
            data.add(price);
            data.add(amount);
        });

        return data;
    }

    /**
     * Initiates the PDF document with a title, author and timestamp. Also calls
     * separate methods to generate a table for the bill of materials.
     * 
     * @param document the document object used for the bill
     * @param author the author of the document
     * @param stringList the data used for the bill of materials
     * @throws DocumentException 
     */
    private void addBill(Document document, String author, java.util.List<String> stringList) throws DocumentException
    {
        Paragraph para1 = new Paragraph();
        addEmptyLine(para1, 1);
        para1.add(new Paragraph("Stykliste for Carport", catFont));
        addEmptyLine(para1, 1);
        para1.add(new Paragraph("Generated by: " + author + ", " + new Date(), smallBold));
        addEmptyLine(para1, 4);

        generateTable(para1, stringList);

        document.add(para1);
    }

    /**
     * Calculates and generates a table used for the bill of materials.
     * 
     * @param paragraph the paragraph you want to add the table to
     * @param stringList the data to populate the table with
     * @throws BadElementException
     * @throws DocumentException 
     */
    private void generateTable(Paragraph paragraph, java.util.List<String> stringList) throws BadElementException, DocumentException
    {
        if (stringList.size() % 7 > 0)
        {
            throw new IllegalArgumentException("StringList has illegal size!");
        }
        PdfPTable table = new PdfPTable(7);
        float[] ws = new float[7];

        table.setWidthPercentage(110);
        ws[0] = 35f;
        ws[1] = 25f;
        ws[2] = 8f;
        ws[3] = 8f;
        ws[4] = 8f;
        ws[5] = 8f;
        ws[6] = 8f;

        table.setWidths(ws);
        addTableHeader(table);

        int amountOfRows = 0;
        for (int i = 0; i < (stringList.size() / 7) - 1; i++)
        {
            addRowToTable(table);
            amountOfRows++;
        }

        int cellCounter = 0;
        for (int i = 1; i < amountOfRows + 1; i++)
        {
            PdfPRow r = table.getRow(i);
            for (PdfPCell c : r.getCells())
            {
                Phrase content = new Phrase(stringList.get(cellCounter), verySmall);
                c.setPhrase(content);
                cellCounter++;
            }
        }

        paragraph.add(table);
    }

    /**
     * Adds a header row to a table with descriptions of each column.
     * 
     * @param table the table you want to add the header to
     */
    private void addTableHeader(PdfPTable table)
    {
        PdfPCell cell;

        float cellPaddingBottom = 7;

        cell = new PdfPCell(new Phrase("Beskrivelse", smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(cellPaddingBottom);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Hjælpetext", smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(cellPaddingBottom);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Længde", smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(cellPaddingBottom);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Bredde", smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(cellPaddingBottom);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Højde", smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(cellPaddingBottom);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Pris", smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(cellPaddingBottom);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Antal", smallBold));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setPaddingBottom(cellPaddingBottom);
        table.addCell(cell);

        table.setHeaderRows(1);
    }

    /**
     * Adds a row of cells to a table formatted to contain the proper data for
     * a bill of materials.
     * 
     * @param table the table you want to add the cell row to
     */
    private void addRowToTable(PdfPTable table)
    {
        PdfPCell cell;

        float cellPaddingBottom = 6;
        float cellPaddingTop = 8;
        float cellPaddingLeft = 3;

        cell = new PdfPCell(new Phrase("Beskrivelse", verySmall));
        cell.setPaddingBottom(cellPaddingBottom);
        cell.setPaddingLeft(cellPaddingLeft);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Hjælpetext", verySmall));
        cell.setPaddingBottom(cellPaddingBottom);
        cell.setPaddingLeft(cellPaddingLeft);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Længde", verySmall));
        cell.setPaddingTop(cellPaddingTop);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Bredde", verySmall));
        cell.setPaddingTop(cellPaddingTop);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Højde", verySmall));
        cell.setPaddingTop(cellPaddingTop);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Pris", verySmall));
        cell.setPaddingTop(cellPaddingTop);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);

        cell = new PdfPCell(new Phrase("Antal", verySmall));
        cell.setPaddingTop(cellPaddingTop);
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_CENTER);
        table.addCell(cell);
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe
    // Reader
    // under File -> Properties
    private void addMetaData(Document document, String text)
    {
        document.addTitle(text);
        document.addSubject("Stykliste");
        document.addKeywords("Java, PDF, iText");
        document.addAuthor("The Martins");
        document.addCreator("The Martins");
    }

    private void addEmptyLine(Paragraph paragraph, int number)
    {
        for (int i = 0; i < number; i++)
        {
            paragraph.add(new Paragraph(" "));
        }
    }
}
