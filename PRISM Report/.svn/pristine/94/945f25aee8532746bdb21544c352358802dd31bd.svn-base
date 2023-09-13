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
 * @author Nandhini
 */
@Path("/SalesReport")
public class New_Sales_Report_Prod {
     @Context
    private UriInfo context;
    DbPackageCall dbPackageCall = new DbPackageCall();

    
    @Path("/saleReport/new")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response New_Sales_Report_Prod(
            @QueryParam(value = "P_ORG")   String P_ORG,
            @QueryParam(value = "P_UNIT_TYPE") String P_UNIT_TYPE,
            @QueryParam(value = "P_File_Type") String P_File_Type,
            @QueryParam(value = "P_FROMDATE")      String P_FROMDATE,
            @QueryParam(value = "P_TODATE")      String P_TODATE)  throws ParseException{
        
         String fileName = "";
        if(P_File_Type.equals("pdf")){
        fileName = "Sales Summary New Report.pdf";
        }
        else if(P_File_Type.equals("doc")){
        fileName = "Sales Summary New Report.docx";
        }
        else
        {
            fileName = "Sales Summary New Report.xlsx";
        }
        
        String xmlData = dbPackageCall.saleReportProd(P_ORG, P_UNIT_TYPE, formatDate(P_FROMDATE), formatDate(P_TODATE));
        String filePath = "/u01/data/reports/Sales_New_Report.rtf";
//        String filePath = "D:/RTF/Sales_New_Report.rtf";
        
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
            Logger.getLogger(New_Sales_Report_Prod.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBytes;
    }
    
    String formatDate(String p_date)  throws ParseException{
        SimpleDateFormat parser = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = parser.parse(p_date); 
        SimpleDateFormat pkgFormatter = new SimpleDateFormat("dd-MMM-yyyy");
        String dateFormat = pkgFormatter.format(date); 
        return dateFormat;
    }
   
    
}
