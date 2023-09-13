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
 * @author DineshkumarP
 */
@Path("/receipt")
public class ReceiptDetail {
 

    @Path("/details")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response receiptDetails(
            @QueryParam(value = "P_PROP_NAME") String P_PROP_NAME,
            @QueryParam(value = "P_FROM_DATE") String P_FROM_DATE,
            @QueryParam(value = "P_TO_DATE") String P_TO_DATE,
            @QueryParam(value = "P_File_Type") String P_File_Type) throws ParseException{
        
        String fileName = "";

        if(P_File_Type.equals("pdf")){
            fileName = "ReceiptDetail.pdf";
        }
        else{
            fileName = "ReceiptDetail.xlsx";
        }
        
                //-- Date
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = parser.parse(P_FROM_DATE);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateParam = formatter.format(date);
        SimpleDateFormat pkgFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        String dateFormatFrom = pkgFormatter.format(date);
//        
        
                //-- Date
        SimpleDateFormat parser1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date1 = parser1.parse(P_TO_DATE);
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy/MM/dd");
        String dateParam1 = formatter1.format(date1);
        SimpleDateFormat pkgFormatter1 = new SimpleDateFormat("dd-MMM-yyyy");
        String dateFormatTo = pkgFormatter1.format(date1);
        
        
        
       // String fileName = P_BLOCK_NUM +".pdf";
        String xmlData = DbPackageCall.receiptDetails(P_PROP_NAME, dateFormatFrom, dateFormatTo);
        String filePath = "/u01/data/reports/Receipt_Detail.rtf";
        
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
            //outFileTypeByte = FOProcessor.FORMAT_PDF;
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
            Logger.getLogger(Receipt.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBytes;
    }
    
    
    
    
    
    
    
    
    
    
}
