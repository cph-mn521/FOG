package com.presentation.command;

import com.entities.dto.BillOfMaterials;
import com.enumerations.DBURL;
import com.exceptions.DataException;
import com.exceptions.FormException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author martin bøgh
 */
public class DownloadPDF extends Command
{

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws DataException
    {
        try
        {
            PrintWriter out = response.getWriter();
            String gurufile = "FirstPdf.pdf";
            String gurupath = "";
            response.setContentType("APPLICATION/OCTET-STREAM");
            response.setHeader("Content-Disposition", "attachment; filename=\""
                    + gurufile + "\"");

            FileInputStream fileInputStream = new FileInputStream(gurupath
                    + gurufile);

            int i;
            while ((i = fileInputStream.read()) != -1)
            {
                out.write(i);
            }
            fileInputStream.close();
            out.close();
        } catch (FileNotFoundException ex)
        {
            throw new DataException("Kunne ikke downloade fil (fileNF)");
        } catch (IOException ex)
        {
            throw new DataException("Kunne ikke downloade fil (IO)");
        }

        return "index";
    }
}
