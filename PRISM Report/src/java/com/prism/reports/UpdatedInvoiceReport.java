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
@Path("/updatedinvoice")
public class UpdatedInvoiceReport {
    
    @Context
    private UriInfo context;
    DbPackageCall dbPackageCall = new DbPackageCall();

    /**
     * Creates a new instance of ReservationContract
     */
    public UpdatedInvoiceReport() {
    }
    @Path("/report")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    // Response I-small
    public Response invoiceReport(
            @QueryParam(value = "P_BU_NAME") String P_BU_NAME,
            @QueryParam(value = "P_INV_NUM") String P_INV_NUM,
             @QueryParam(value = "P_BOOKING_NUM") String P_BOOKING_NUM,
             @QueryParam(value = "P_File_Type") String P_File_Type){
         String fileName = "";
        if(P_File_Type.equals("pdf")){
        fileName = "New Invoice Report"+P_INV_NUM +".pdf";
        }
        else{
        fileName = "New Invoice Report"+P_INV_NUM +".xlsx";
        }
        String xmlData = DbPackageCall.updatedInvoiceReport(P_BU_NAME,P_INV_NUM,P_BOOKING_NUM);
//        String filePath = "/u01/data/reports/Tax_Invoice_Report.rtf";
        String filePath = "/u01/data/reports/Tax_Invoice_Report_bu_updated.rtf";
        
        if(xmlData !=null){
            Response.ResponseBuilder builder = Response.ok(rtfReport(xmlData, filePath,P_File_Type));
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
            // Caps I
            Logger.getLogger(UpdatedInvoiceReport.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBytes;
    }
    
}
