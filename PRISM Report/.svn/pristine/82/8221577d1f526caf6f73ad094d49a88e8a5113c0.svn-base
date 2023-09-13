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
@Path("/inventory")
public class New_Inventory_Prod {
     private UriInfo context;
    DbPackageCall dbPackageCall = new DbPackageCall();

    /**
     * Creates a new instance of ReservationContract
     */
    public New_Inventory_Prod(    
            ){
    };

     
    @Path(value = "/status/new")
    @GET
    @Produces(value = MediaType.APPLICATION_OCTET_STREAM)
    public Response New_Inventory_Prod(
            @QueryParam(value = "P_PROP_ID") String P_PROP_ID, 
            @QueryParam(value = "P_BUILD_ID") String P_BUILD_ID, 
            @QueryParam(value = "P_File_Type") String P_File_Type) {

       String fileName = "";
        if(P_File_Type.equals("pdf")){
        fileName = "Inventory_Status_New_Report" +".pdf";
        }
        if(P_File_Type.equals("doc")){
        fileName = "Inventory_Status_New_Report" +".docx";
        }
        else{
        fileName = "Inventory_Status_New_Report" +".xlsx";
        }
        System.out.println("FileType" + P_File_Type);
        String xmlData = dbPackageCall.inventoryStatusProd(P_PROP_ID,P_BUILD_ID);
        String filePath = "/u01/data/reports/All_Inventory_Status_New_Report.rtf";
        // String filePath = "D:/RTF/All_Inventory_Status_New_Report.rtf";
        
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
            Logger.getLogger(InventoryStatus.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBytes;
    }
    
    
    
}
