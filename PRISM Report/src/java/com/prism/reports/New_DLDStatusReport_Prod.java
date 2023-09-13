/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prism.reports;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import oracle.xdo.XDOException;
import oracle.xdo.template.FOProcessor;
import oracle.xdo.template.RTFProcessor;

/**
 *
 * @author Nandhini
 */
@Path("/DLD")
public class New_DLDStatusReport_Prod {
    @Path("/status/prod")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response New_DLDStatusReport_Prod(
            @QueryParam(value = "P_PROJ_ID") String P_PROJ_ID,
            @QueryParam(value = "P_File_Type") String P_File_Type
    ) throws ParseException{
        String fileName = "";
        if(P_File_Type.equals("pdf")){
        fileName = "DLD Status Report.pdf";
        }
        else if(P_File_Type.equals("xlsx")){
        fileName = "DLD Status Report.xlsx";
        }
        else{
        fileName = "DLD Status Report.docx";
        }
        System.out.println("FileType" + P_File_Type);
        String xmlData = DbPackageCall.dldStatusReportProd(P_PROJ_ID);
        String filePath = "/u01/data/reports/DLD Status Report.rtf";
        //String filePath="D:/RTF/DLD Status Report.rtf";
        if(xmlData !=null){
            Response.ResponseBuilder builder = Response.ok(rtfReport(xmlData, filePath,P_File_Type));
            builder.header("Content-Disposition", "attachment; filename=" + fileName);
            return builder.build();  
        }else{
            Response.ResponseBuilder builder = Response.ok(DbPackageCall.responseToRest("No data found"));
            return builder.build();
        }

    }
    public byte[] rtfReport(String xmlData, String filePath,String file_Type) {
        InputStream fiS = null;
        ByteArrayInputStream xslInStream = null;
        ByteArrayInputStream dataStream = null;
        ByteArrayOutputStream pdfOutStream = null;

        byte[] dataBytes = null;
        byte outFileTypeByte = 0;
        try {

            fiS = new FileInputStream(new File(filePath));
            
             if(file_Type.equals("pdf")){
                 outFileTypeByte = FOProcessor.FORMAT_PDF;
             }
             else if(file_Type.equals("xlsx")){
               outFileTypeByte = FOProcessor.FORMAT_XLSX;
             }
             else{
               outFileTypeByte = FOProcessor.FORMAT_DOCX;
             }
            RTFProcessor rtfP = new RTFProcessor(fiS); 
            ByteArrayOutputStream xslOutStream = new ByteArrayOutputStream();
            rtfP.setOutput(xslOutStream);
            rtfP.process();
            xslInStream = new ByteArrayInputStream(xslOutStream.toByteArray());

            FOProcessor processor = new FOProcessor();
            processor.setConfig("/u01/data/font/xdo.cfg"); 
            dataStream = new ByteArrayInputStream(xmlData.getBytes());

            processor.setData(dataStream);
            processor.setTemplate(xslInStream);

            pdfOutStream = new ByteArrayOutputStream();
            processor.setOutput(pdfOutStream);

            processor.setOutputFormat(outFileTypeByte);
            processor.generate();
            dataBytes = pdfOutStream.toByteArray();
        } catch (XDOException | FileNotFoundException ex) {
            Logger.getLogger(New_DLDStatusReport_Prod.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBytes;
    }  
}