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
 * @author DineshkumarP
 */
@Path("/customer")
public class CustomerStatement {
    
    @Context
    private UriInfo context;
    DbPackageCall dbPackageCall = new DbPackageCall();

    
//    @Path("/statement")
//    @GET
//    @Produces(MediaType.APPLICATION_OCTET_STREAM)
//    public Response arDetail(
//            @QueryParam(value = "P_CUST_ID")   String P_CUST_ID,
//            @QueryParam(value = "P_File_Type") String P_File_Type,
//            @QueryParam(value = "P_DATE")      String P_DATE) throws ParseException{
//        String fileName = "";
//        if(P_File_Type.equals("pdf")){
//        fileName = "Customer Statement of Accounts"+ P_DATE +".pdf";
//        }
//        else{
//        fileName = "Customer Statement of Accounts"+ P_DATE +".xlsx";
//        }
//        //-- Date
//        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
//        java.util.Date date = parser.parse(P_DATE);
//        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
//        String dateParam = formatter.format(date);
//        SimpleDateFormat pkgFormatter = new SimpleDateFormat("dd-MMM-yyyy");
//        String dateFormat = pkgFormatter.format(date);
//        System.out.println("Date--"+dateParam);
//        
//        System.out.println("FileType" + P_File_Type);
//        String xmlData = dbPackageCall.customerStatment(P_CUST_ID, dateFormat);
//        String filePath = "/u01/data/reports/SOA.rtf";
//        Response.ResponseBuilder builder = Response.ok(rtfReport(xmlData, filePath,P_File_Type));
//        builder.header("Content-Disposition", "attachment; filename=" + fileName);
//        return builder.build();
//    };

    
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
            Logger.getLogger(ArDetail.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBytes;
    }
    
    
    @Path("/statement")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response customerStatmentAccSummary(
                                            @QueryParam(value = "P_CUST_ID")     String P_CUST_ID,
//                                            @QueryParam(value = "P_ORG_ID")     String P_ORG_ID,          
                                            @QueryParam(value = "P_File_Type")   String P_File_Type,
                                            @QueryParam(value = "P_DATE")        String P_DATE,
                                            @QueryParam(value = "P_REPORT_TYPE") String P_REPORT_TYPE
                                            ) throws ParseException{
        String fileName = "";
        String xmlData="";
        String filePath="";
        if(P_File_Type.equals("pdf")){
        fileName = "Customer Statement of Accounts"+ P_DATE +".pdf";
        }
        else{
        fileName = "Customer Statement of Accounts"+ P_DATE +".xlsx";
        }
        //-- Date
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = parser.parse(P_DATE);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd");
        String dateParam = formatter.format(date);
        SimpleDateFormat pkgFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        String dateFormat = pkgFormatter.format(date);
        //System.out.println("Date--"+dateParam);
        
        System.out.println("FileType" + P_File_Type);
        if(P_REPORT_TYPE.equalsIgnoreCase("CUST_STATEMENT_ACCOUNT")){
           //test//
            xmlData = dbPackageCall.customerStatment(P_CUST_ID,dateFormat);
            //System.out.println("data is"+xmlData);
           filePath = "/u01/data/reports/SOA.rtf"; 
           //filePath = "D:/documents/RTFbkp/SOA.rtf";
        }else if(P_REPORT_TYPE.equalsIgnoreCase("AR_SUMMARY")){
            xmlData = dbPackageCall.customerStatmentSummary(P_CUST_ID,dateFormat);
           filePath = "/u01/data/reports/SOA_Summary.rtf";
           // filePath = "D:/Study/RTFbkp/SOA_Summary.rtf";
            //System.out.println("data is"+xmlData);
        }else{
//            xmlData = dbPackageCall.customerStatment(P_ORG_ID,P_CUST_ID,dateFormat);
            xmlData = dbPackageCall.customerStatment(P_CUST_ID,dateFormat);
            filePath = "/u01/data/reports/SOA.rtf";     
           // System.out.println("data is"+xmlData);
        }

        if(xmlData !=null){
            Response.ResponseBuilder builder = Response.ok(rtfReport(xmlData, filePath,P_File_Type));
            builder.header("Content-Disposition", "attachment; filename=" + fileName);
            return builder.build();  
        }else{
            Response.ResponseBuilder builder = Response.ok(dbPackageCall.responseToRest("No data found"));
            return builder.build();
        }
        
    };
    
    
    
    
}
