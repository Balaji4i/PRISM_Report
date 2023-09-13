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
 *
 * @author Nandhini
 */
@Path("/daily")


public class New_Daily_Collection_Prod {
     @Path("/collection/new/prod")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response New_Daily_Collection_Prod(
            @QueryParam(value = "P_ORG_ID") String P_ORG_ID,
            @QueryParam(value = "P_FROM_DATE")   String P_FROM_DATE,
            @QueryParam(value = "P_TO_DATE")        String P_TO_DATE,
            @QueryParam(value = "P_File_Type") String P_File_Type) throws ParseException{
        String fileName = "";
        if(P_File_Type.equals("pdf")){
        fileName = "Daily Collection Report_New.pdf";
        }
        else if(P_File_Type.equals("doc")){
        fileName = "Daily Collection Report_New.docx";
        }
        else
        {
            fileName = "Daily Collection Report_New"
                    + ".xlsx";
        }
         //-- From Date
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = parser.parse(P_FROM_DATE);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateParam = formatter.format(date);
        SimpleDateFormat pkgFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        String dateFormat = pkgFormatter.format(date);
        System.out.println("Date--"+dateParam);
        
        //-- To Date
        SimpleDateFormat parser1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date1 = parser1.parse(P_TO_DATE);
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
        String dateParam1 = formatter1.format(date);
        SimpleDateFormat pkgFormatter1 = new SimpleDateFormat("dd-MMM-yyyy");
        String dateFormat1 = pkgFormatter.format(date1);
        System.out.println("Date--"+dateParam1);
        String xmlData = DbPackageCall.dailyCollectionNewProd(P_ORG_ID,dateFormat,dateFormat1);
        String filePath = "/u01/data/reports/Collection_Report_New.rtf";
//        String filePath="D:/RTF/Collection_Report_New.rtf";
        if(xmlData !=null){
            ResponseBuilder builder = Response.ok(rtfReport(xmlData, filePath,P_File_Type));
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
            Logger.getLogger(New_Daily_Collection_Prod.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBytes;
    }    
    
    
}
