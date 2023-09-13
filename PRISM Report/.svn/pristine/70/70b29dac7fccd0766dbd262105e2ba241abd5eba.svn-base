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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * REST Web Service
 *
 * @author gautham.r
 */
@Path("/contract")
public class ReservationContract {

    @Context
    private UriInfo context;
    DbPackageCall dbPackageCall = new DbPackageCall();

    /**
     * Creates a new instance of ReservationContract
     */
    public ReservationContract() {
    }

    /**
     * Retrieves representation of an instance of
     * com.omniyat.rpt.ReservationContract
     *
     * @param p_block_num
     * @param P_File_Type
     * @return an instance of java.lang.String
     */
    @Path("/reservation")
    @GET
//    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Produces({MediaType.APPLICATION_OCTET_STREAM, MediaType.APPLICATION_JSON})
    public Response reservationContract(@QueryParam(value = "p_template_Code") String p_template_Code,
            @QueryParam(value = "p_unit_num") String p_unit_num,
            @QueryParam(value = "P_File_Type") String P_File_Type) {

        System.out.println("TYPE:::"+P_File_Type);
        String fileName = "";
//        if (P_File_Type.equals("pdf")) {
//            fileName = p_template_Code + "-" + p_unit_num + ".pdf";
//        } else if (P_File_Type.equals("xlsx")) {
//            fileName = p_template_Code + "-" + p_unit_num + ".xlsx";
//        } else if (P_File_Type.equals("doc")) {
//            fileName = p_template_Code + "-" + p_unit_num + ".docx";
//        } else {
//            fileName = p_template_Code + "-" + p_unit_num + ".pdf";
//        }
        
        if(P_File_Type.equals("pdf")){
        fileName = p_template_Code + "-" + p_unit_num +".pdf";
        }
        else if(P_File_Type.equals("doc")){
        fileName = p_template_Code + "-" + p_unit_num +".docx";
        }
        else {
                fileName = p_template_Code + "-" + p_unit_num +".xlsx";
            }

        //  fileName = p_block_num +P_File_Type;
        String xmlData = dbPackageCall.reservationContract(p_unit_num, p_template_Code);
        String filePath = getFilePath1(p_template_Code);
        System.out.println("xml data : "+xmlData);
        if (xmlData != null ) {
            ResponseBuilder builder = Response.ok(rtfReport(xmlData, filePath, P_File_Type));
            builder.header("Content-Disposition", "attachment; filename=" + fileName);
            return builder.build();
        } else {
            ResponseBuilder builder = Response.ok(responseToRest("No data found"));
            return builder.build();

        }

    }
    

    public byte[] rtfReport(String xmlData, String filePath, String file_Type) {
        InputStream fiS = null;
        ByteArrayInputStream xslInStream = null;
        ByteArrayInputStream dataStream = null;
        ByteArrayOutputStream pdfOutStream = null;

        byte[] dataBytes = null;
        byte outFileTypeByte = 0;
        try {

            fiS = new FileInputStream(new File(filePath));
            if (file_Type.equals("pdf")) {
                outFileTypeByte = FOProcessor.FORMAT_PDF;
            } else if (file_Type.equals("xlsx")) {
                outFileTypeByte = FOProcessor.FORMAT_XLSX;
            } else {
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
            Logger.getLogger(ReservationContract.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataBytes;
    }

    public String getFilePath(String TemplateCode) {
        String filePath = null;
        if (TemplateCode.equalsIgnoreCase("OPS_OFF")) {
            filePath = "/u01/data/reports/unitreservation/opus/OPUS_OFFICE.rtf";
        }
        if (TemplateCode.equalsIgnoreCase("OPS_LP")) {
            filePath = "/u01/data/reports/unitreservation/opus/OPUS_LP.rtf";
        }
        

        if (TemplateCode.equalsIgnoreCase("OPS_NLP")) {
            filePath = "/u01/data/reports/unitreservation/opus/OPUS_NLP.rtf";
        }
        if (TemplateCode.equalsIgnoreCase("LANGAM_LP")) {
            filePath = "/u01/data/reports/unitreservation/langham/LANGHAM_LP.rtf";
        }
        if (TemplateCode.equalsIgnoreCase("LANGAM_NLP")) {
            filePath = "/u01/data/reports/unitreservation/langham/LANGHAM_NLP.rtf";
        }
        if (TemplateCode.equalsIgnoreCase("ANWA")) {
            filePath = "/u01/data/reports/unitreservation/anwa/ANWA.rtf";
            //filePath="D:/Study/RTFbkp/ANWA.rtf";
        }
        if (TemplateCode.equalsIgnoreCase("ONE_PALM")) {
            filePath = "/u01/data/reports/unitreservation/onepalm/ONE_PALM.rtf";
        }
        if (TemplateCode.equalsIgnoreCase("THE_PAD")) {
            filePath = "/u01/data/reports/unitreservation/PAD.rtf";
        }
        if (TemplateCode.equalsIgnoreCase("STRL_EAST")) {
            filePath = "/u01/data/reports/unitreservation/sterling/STERLING_EAST.rtf";
        }
        if (TemplateCode.equalsIgnoreCase("STRL_WEST")) {
            filePath = "/u01/data/reports/unitreservation/sterling/STERLING_WEST.rtf";
        }
        //added  on 05-07-2022//
        if (TemplateCode.equalsIgnoreCase("AVA")) {
            filePath = "/u01/data/reports/unitreservation/ava/AVA.rtf";
            //filePath="D:/Study/RTFbkp/AVA.rtf";
        }
        return filePath;
    }

    /*(commented on 02/08/2022 new file and path added)public String getFilePath1(String TemplateCode) {
        String filePath = null;

        switch (TemplateCode) {
            case "OPS":
                filePath = "/u01/data/reports/unitreservation/opus/Opus.rtf";
                break;
            case "OPS_OFF":
                filePath = "/u01/data/reports/unitreservation/opus/OPUS_OFFICE.rtf";
                break;
            case "OPS_LP":
                filePath = "/u01/data/reports/unitreservation/opus/OPUS_LP.rtf";
                break;
            case "OPS_NLP":
                filePath = "/u01/data/reports/unitreservation/opus/OPUS_NLP.rtf";
                break;
            case "LANGAM_LP":
                filePath = "/u01/data/reports/unitreservation/langham/LANGHAM_LP.rtf";
                break;
            case "LANGAM_NLP":
                filePath = "/u01/data/reports/unitreservation/langham/LANGHAM_NLP.rtf";
                break;
            case "ANWA":
                //filePath = "/u01/data/reports/unitreservation/anwa/ANWA.rtf";
                filePath="D:/Study/RTFbkp/ANWA.rtf";
                break;
            case "ONE_PALM":
                filePath = "/u01/data/reports/unitreservation/onepalm/ONE_PALM.rtf";
                break;
            case "THE_PAD":
                filePath = "/u01/data/reports/unitreservation/pad/PAD.rtf";
                break;
            case "STRL_EAST":
                filePath = "/u01/data/reports/unitreservation/sterling_east/Sterling_East.rtf";
                break;
            case "STRL_WEST":
                filePath = "/u01/data/reports/unitreservation/sterling_west/Sterling_West.rtf";
                break;
            case "DOR_CHESTER":
                filePath = "/u01/data/reports/unitreservation/dorchester/Dorchester.rtf";
                break;
          //added  on 05/05/2022//
            case "AVA":
                filePath = "/u01/data/reports/unitreservation/ava/AVA.rtf";
                //  filePath="D:/Study/RTFbkp/AVA.rtf";
                break;    

        }

        return filePath;*/
    
    public String getFilePath1(String TemplateCode) {
        String filePath = null;

        switch (TemplateCode) {
            case "OPS_RES":
                filePath = "/u01/data/reports/prism_sale_reports/Opus Residential- Reservation Contract.rtf";
                break;
            case "OPS_RES_NOM":
                filePath = "/u01/data/reports/prism_sale_reports/Opus Residential- Reservation Contract - Nominee Clause.rtf";
                break;
            case "OPS_OFF":
                filePath = "/u01/data/reports/prism_sale_reports/Opus office - Reservation Contract.rtf";
                break;
            case "OPS_OFF_NOM":
                filePath = "/u01/data/reports/prism_sale_reports/Opus office - Reservation Contract - Nominee Clause.rtf";
                break;
            case "LANGAM_LP":
                filePath = "/u01/data/reports/unitreservation/langham/LANGHAM_LP.rtf";
                break;
            case "LANGAM_NLP":
                filePath = "/u01/data/reports/unitreservation/langham/LANGHAM_NLP.rtf";
                break;
            case "ANWA":
                filePath = "/u01/data/reports/prism_sale_reports/ANWA - Reservation Contract.rtf";
                break;
            case "ANWA_NOM":
                filePath = "/u01/data/reports/prism_sale_reports/ANWA - Reservation Contract - Nomimee Clause.rtf";
                break;    
            case "ONE_PALM":
                filePath = "/u01/data/reports/prism_sale_reports/One Palm - Reservation Contract.rtf";
                break;
            case "ONE_PALM_NOM":
                filePath = "/u01/data/reports/prism_sale_reports/One Palm - Reservation Contract - Nominee Clause.rtf";
                break;    
            case "THE_PAD":
                filePath = "/u01/data/reports/prism_sale_reports/Pad - Reservation Contract.rtf";
                break;
            case "THE_PAD_NOM":
                filePath = "/u01/data/reports/prism_sale_reports/Pad - Reservation Contract - Nominee Clause.rtf";
                break;    
            case "STRL_EAST":
                filePath = "/u01/data/reports/prism_sale_reports/Sterling East  - Reservation Contract.rtf";
                break;
            case "STRL_EAST_NOM":
                filePath = "/u01/data/reports/prism_sale_reports/Sterling East  - Reservation Contract - Nominee Clause.rtf";
                break;    
            case "STRL_WEST":
                filePath = "/u01/data/reports/prism_sale_reports/Sterling West - Reservation Contract.rtf";
                break;
            case "STRL_WEST_NOM":
                filePath = "/u01/data/reports/prism_sale_reports/Sterling West - Reservation Contract - Nominee Clause.rtf";
                break;    
            case "DOR_CHESTER":
                filePath = "/u01/data/reports/prism_sale_reports/Dorchester - Reservation Contract.rtf";
                break;
            case "DOR_CHESTER_NOM":
                filePath = "/u01/data/reports/prism_sale_reports/Dorchester - Reservation Contract Nominee Clause.rtf";
                break;    
            case "AVA":
                filePath = "/u01/data/reports/prism_sale_reports/AVA.rtf";
                break;
               }
        return filePath;
    }
 
                
    
    public String responseToRest(String result) {
        org.json.simple.JSONObject json = new org.json.simple.JSONObject();
        json.put("result", result);
        return json.toJSONString();

}
    
}
