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
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import oracle.xdo.XDOException;
import oracle.xdo.template.FOProcessor;
import oracle.xdo.template.RTFProcessor;

/**
 *
 * @author Ibrahim
 */
@Path("/Cancellation")
public class CancellationReport {
    
    @Context
    private UriInfo context;
    DbPackageCall dbPackageCall = new DbPackageCall();

    
    @Path("/CancellationReport")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response cancellationInfo(
            @QueryParam(value = "P_PROP_NAME")   String P_PROP_NAME,  
            @QueryParam(value = "P_File_Type") String P_File_Type,
            @QueryParam(value = "P_DATE")      String P_DATE) throws ParseException{
  
        String fileName = "";
        if(P_File_Type.equals("pdf")){
        fileName = "Cancellation_Report-"+ P_DATE +".pdf";
        }
        else if(P_File_Type.equals("doc")){
        fileName = "Cancellation_Report-"+ P_DATE +".docx";
        }
        else
        {
            fileName = "Cancellation_Report-"+ P_DATE +".xlsx";
        }
         
        //-- Date
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = parser.parse(P_DATE);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateParam = formatter.format(date);
        SimpleDateFormat pkgFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        String dateFormat1 = pkgFormatter.format(date); 
        String dateFormat = dateFormat1.toUpperCase();
        
        String xmlData = dbPackageCall.cancellationReport(P_PROP_NAME, dateFormat);
        String filePath = "/u01/data/reports/Cancellation_Report.rtf";
//        String filePath="D:/Documents/RTFbkp/Cancellation_Report.rtf";
        
        if(xmlData !=null){
            Response.ResponseBuilder builder = Response.ok(rtfReport(xmlData, filePath,P_File_Type));
            builder.header("Content-Disposition", "attachment; filename=" + fileName);
            return builder.build();
        }else{
            Response.ResponseBuilder builder = Response.ok(dbPackageCall.responseToRest("No data found"));
            return builder.build();
        }

    };
    
    
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
            Logger.getLogger(ArDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBytes;
    }
    
}
