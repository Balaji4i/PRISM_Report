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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import oracle.xdo.XDOException;
import oracle.xdo.template.FOProcessor;
import oracle.xdo.template.RTFProcessor;

/**
 * REST Web Service
 *
 * @author gautham.r
 */
@Path("/ar")
public class ArSummary {

    @Context
    private UriInfo context;
    DbPackageCall dbPackageCall = new DbPackageCall();

    /**
     * Creates a new instance of ReservationContract
     */
    public ArSummary() {
    }

    /**
     * Retrieves representation of an instance of com.omniyat.rpt.ReservationContract
     * @param P_PROJ_NAME 
     * @param P_PROP_NAME 
     * @param P_File_Type 
     * @return an instance of java.lang.String
     */
    @Path("/summary")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response arSummary(
             @QueryParam(value = "P_PROJ_NAME") String P_PROJ_NAME,
             @QueryParam(value = "P_PROP_NAME") String P_PROP_NAME,
             @QueryParam(value = "P_File_Type") String P_File_Type,
             @QueryParam(value = "P_DATE")      String P_DATE) throws ParseException{
         String fileName = "";
        if(P_File_Type.equals("pdf")){
        fileName = "AR_Summary_Report" +".pdf";
        }
        else{
        fileName = "AR_Summary_Report" +".xlsx";
        }
        
        //-- Date
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = parser.parse(P_DATE);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateParam = formatter.format(date);
        SimpleDateFormat pkgFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        String dateFormat = pkgFormatter.format(date);
        System.out.println("Date--"+dateParam);
        
        
       // String fileName = P_PROJ_NAME +".xlsx";
        String xmlData = DbPackageCall.arSummary(P_PROJ_NAME,P_PROP_NAME, dateFormat);
       String filePath = "/u01/data/reports/AR_SUMMARY_OMNIYAT.rtf";//prod//
//        /*TEST*/     String filePath = "/u01/data/reports/AR_Summary_Omniyat.rtf";
//         String filePath="D:/Documents/RTFbkp/AR_SUMMARY_OMNIYAT.rtf";
        
        if(xmlData !=null){
            ResponseBuilder builder = Response.ok(rtfReport(xmlData, filePath,P_File_Type));
            builder.header("Content-Disposition", "attachment; filename=" + fileName);
            return builder.build();  
        }else{
            Response.ResponseBuilder builder = Response.ok(dbPackageCall.responseToRest("No data found"));
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
           // outFileTypeByte = FOProcessor.FORMAT_XLSX;
              if(file_Type.equals("pdf")){
                 outFileTypeByte = FOProcessor.FORMAT_PDF;
             }
             else{
               outFileTypeByte = FOProcessor.FORMAT_XLSX;
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
            Logger.getLogger(ArSummary.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBytes;
    }
}
