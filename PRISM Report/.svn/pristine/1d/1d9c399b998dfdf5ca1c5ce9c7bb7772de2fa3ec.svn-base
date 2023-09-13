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
 * REST Web Service
 *
 * @author gautham.r
 */
@Path("/spa_Arabic")
public class SpaReportArabic {

    @Context
    private UriInfo context;
    DbPackageCall dbPackageCall = new DbPackageCall();

    /**
     * Creates a new instance of ReservationContract
     */
    public SpaReportArabic() {
    }

    /**
     * Retrieves representation of an instance of com.omniyat.rpt.ReservationContract
     * @param P_Block_Num 
     * @param P_Spa_Template 
     * @param P_Org_Id 
     * @param P_File_Type 
     * @return an instance of java.lang.String
     */
    @Path("/report")
    @GET
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response spaReportArabic(
            @QueryParam(value = "P_Block_Num") String P_Block_Num ,
            @QueryParam(value = "P_Spa_Template") String P_Spa_Template ,
            @QueryParam(value = "P_Org_Id") String P_Org_Id ,
            @QueryParam(value = "P_File_Type") String P_File_Type){
        String fileName = "";
        if(P_File_Type.equals("pdf")){
        fileName = P_Block_Num +".pdf";
        }
        else if(P_File_Type.equals("doc")){
        fileName = P_Block_Num +".docx";
        }
        else{
        fileName = P_Block_Num +".xlsx";
        }
      //  fileName = p_block_num +P_File_Type;
        String xmlData = dbPackageCall.spaReportArabic(P_Block_Num);
//        String filePath = "/u01/data/reports/Arabic_SPA.rtf";
        String filePath = getFilePath(P_Org_Id,P_Spa_Template);
        
        if(xmlData !=null){
            ResponseBuilder builder = Response.ok(rtfReport(xmlData, filePath,P_File_Type));
            builder.header("Content-Disposition", "attachment; filename=" + fileName);
            return builder.build();
        }else{
            Response.ResponseBuilder builder = Response.ok(dbPackageCall.responseToRest("No data found"));
            return builder.build();
        }
    }
    public String getFilePath(String org_id,String spa_template ) {
        String filePath = null;
        if(org_id.equals("300000007984748"))//AVA at Palm Jumeriah//
        {
        switch (spa_template) {
            case "Deffered":
                filePath = "/u01/data/reports/spa/AVA - SPA (Dorchester) - DEFERRED FINAL";
                break;
            case "Non-Deferred":
                filePath = "/u01/data/reports/spa/AVA - SPA (Dorchester) - NON-DEFERRED.rtf";
                break;
        }
        }
        else if(org_id.equals("300000007551023"))//ORLA By Omniyat//
        {
        switch (spa_template) {
            case "Deffered":
                filePath = "/u01/data/reports/spa/ORLA - SPA - DEFERRED.rtf";
                break;
            case "Non-Deferred":
                filePath = "/u01/data/reports/spa/ORLA - SPA - NON-DEFERRED.rtf";
                break;
        }
        }
        else if(org_id.equals("300000002526040"))//The Sterling West House//
        {
        switch (spa_template) {
            case "Deffered":
                filePath = "/u01/data/reports/spa/STERLING - WEST - SPA - RESIDENTIAL  - DEFERRED (Blank ACD).rtf";
                break;
            case "Non-Deferred":
                filePath = "/u01/data/reports/spa/STERLING - EAST - SPA - RESIDENTIAL - NON DEFERRED.rtf";
                break;
        }
        }
        else if(org_id.equals("300000002522710"))//The Anwa By Omniyat//
        {
        switch (spa_template) {
            case "Deffered":
                filePath = "";
                break;
            case "Non-Deferred":
                filePath = "/u01/data/reports/spa/ANWA - SPA - RESIDENTIAL - NON DEFERRED.rtf";
                break;
        }
        }
        else if(org_id.equals("300000002526020"))//The Residences Dorchester Collection Dubai//
        {
        switch (spa_template) {
            case "Non-Deffered":
                filePath = "/u01/data/reports/spa/DORCHESTER - SPA RESIDENTIAL - NON DEFERRED.rtf";
                break;
            case "Deferred":
                filePath = "/u01/data/reports/spa/DORCHESTER - SPA RESIDENTIAL - DEFERRED.rtf";
                break;
        }
        }
        else if(org_id.equals("300000002526030"))//The Sterling East House//
        {
        switch (spa_template) {
            case "Non-Deffered":
                filePath = "/u01/data/reports/spa/STERLING - EAST - SPA - RESIDENTIAL - NON DEFERRED.rtf";
                break;
            case "Deferred":
                filePath = "/u01/data/reports/spa/STERLING - EAST -  SPA - DEFERRED PAYMENTS (Blank ACD).rtf";
                break;
        }
        }
        return filePath;
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
           // outFileTypeByte = FOProcessor.FORMAT_PDF;
                    
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
            Logger.getLogger(SpaReportArabic.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBytes;
    }
}

