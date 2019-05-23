package com.presentation.command;

import com.exceptions.DataException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
