/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prism.reports;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

/**
 *
 * @author gautham.r
 */
public class DbPackageCall {

    static Connection connection;
    static Statement statement;
    static ResultSet resultSet;
    static PreparedStatement ps;

        public static Connection getConnectionDS(String datasource) throws SQLException,
            NamingException {
        Connection con = null;
        DataSource data = null;
        Context initialContext = new InitialContext();
        if (initialContext == null) {

        }
        data = (DataSource) initialContext.lookup(datasource);
        if (data != null) {
            con = data.getConnection();
        } else {
            System.out.println("Failed to Find JDBC DataSource.");
        }
        return con;
    }
    
    public static Connection getDBConnectionLocal() throws SQLException, ClassNotFoundException {
        Connection con = null;
        try { 
            Class.forName("oracle.jdbc.driver.OracleDriver"); 
//            con = DriverManager.getConnection("jdbc:oracle:thin:@144.21.67.79:1533/omnipdb1.606532292.oraclecloud.internal", "XXPM_TEST", "omniyat123");
           //con = DriverManager.getConnection("jdbc:oracle:thin:@144.21.67.79:1533/omnipdb1.606532292.oraclecloud.internal", "XXREF_WK3", "OmP_Tst_d_26");
           con = DriverManager.getConnection("jdbc:oracle:thin:@144.21.67.79:1533/omnipdb1.606532292.oraclecloud.internal", "XXREF_WK4", "Orwk4_T2kQ");
//            con = DriverManager.getConnection("jdbc:oracle:thin:@130.61.109.253:1521/omnipdb1.sub08081309100.omnivcn1.oraclevcn.com", "XXPRISRO", "Tr7h_SDFd_AS2d");
         } catch (SQLException ex) {
            ex.printStackTrace();
        } 
        return con;
    }
    
    /*
    * DB Initializer
    */
    
    public static void dbInitialization() throws SQLException, NamingException, ClassNotFoundException {
 connection = getConnectionDS("jdbc/prism");
// connection=getDBConnectionLocal();     
    }
    
    
//    public static void dbInitialization() {
//        try {
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//            
//            // TEST
//           connection = DriverManager.getConnection("jdbc:oracle:thin:@144.21.67.79:1533/omnipdb1.606532292.oraclecloud.internal", "XXPM_TEST", "omniyat123");
//          
//            // PROD
////            connection = DriverManager.getConnection("jdbc:oracle:thin:@130.61.109.253:1521/omnipdb1.sub08081309100.omnivcn1.oraclevcn.com", "XXPRISM", "PrIsm##123");
//
//        } catch (SQLException ex) {
//            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
//        } catch (ClassNotFoundException ex) {
//            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//    }

    public static String unitStatus(String P_PROJ_NAME,String P_PROP_NAME) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_report_pkg.XXPRISM_UNIT_STATUS('"+ P_PROJ_NAME +"','"+P_PROP_NAME +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
    
     public static String unitStatusNew(String P_PROJ_NAME,String P_PROP_NAME) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_report_pkg.XXPRISM_UNIT_STATUS_NEW('"+ P_PROJ_NAME +"','"+P_PROP_NAME +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
    public static String priceList(String P_PROJ_ID,String P_PROP_ID,String P_UNIT_STATUS) {
       String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_report_pkg.XXPRISM_PRICELIST_REPORT('"+ P_PROJ_ID +"','"+ P_PROP_ID +"','"+ P_UNIT_STATUS +"') xml from dual";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    } 
    public static String inventoryStatus(String P_PROP_NAME,String P_BUILD_NAME) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_report_pkg.XXPRISM_ALL_INVENTORY_STATUS('"+ P_PROP_NAME +"','"+P_BUILD_NAME +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
      public static String spaReport(String P_BLOCK_NUM) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_report_pkg.XXPRISM_SPA_REPORT('"+ P_BLOCK_NUM +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
      
      public static String spaReportArabic(String P_BLOCK_NUM) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_report_pkg.XXPRISM_ARABIC_SPA_REPORT('"+ P_BLOCK_NUM +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
      
      
       public static String arSummary(String P_PROJ_NAME,String P_PROP_NAME, String P_DATE) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_report_pkg.XXPRISM_AR_SUMMARY('"+ P_PROJ_NAME +"','"+ P_PROP_NAME +"','"+P_DATE +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
       
      public static String arDetail(String P_PROJ_NAME,String P_PROP_NAME, String P_DATE) {
        String xmlString = null;
        try {
            dbInitialization();
//            String sql = "select XXPRISM_report_pkg.XXPRISM_AR_DETAIL('"+ P_PROJ_NAME +"','"+P_PROP_NAME +"','To_DATE('"+P_DATE+"'','DD-MM-YYYY')) xml from dual";
            String sql = "select XXPRISM_report_pkg.XXPRISM_AR_DETAIL('"+ P_PROJ_NAME +"','"+P_PROP_NAME +"','"+P_DATE +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
      }

 
    public static String newArDetail(String P_PROJ_ID,String P_PROP_ID, String P_DATE,String P_AMOUNT) {
        String xmlString = null;
        try {
            dbInitialization();
//            String sql = "select XXPRISM_report_pkg.XXPRISM_AR_DETAIL('"+ P_PROJ_NAME +"','"+P_PROP_NAME +"','To_DATE('"+P_DATE+"'','DD-MM-YYYY')) xml from dual";
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_AR_DETAIL('"+ P_PROJ_ID +"','"+P_PROP_ID +"','"+P_DATE +"','"+P_AMOUNT +"') xml from dual";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
      
     public static String customerStatment(String P_PROJ_NAME,String P_DATE) {
        String xmlString = null;
        try {
            dbInitialization();
//        
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_CUSTOMER_STATEMENT('"+ P_PROJ_NAME +"','"+P_DATE +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
      
      public static String customerStatmentSummary(String P_PROJ_NAME,String P_DATE) {
        String xmlString = null;
        try {
            dbInitialization();
               String sql = "select XXPRISM_REPORT_PKG.XXPRISM_SOA_SUMMARY('"+ P_PROJ_NAME +"','"+P_DATE +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    } 
      
      
    public static String unitwisecollection(String P_PROP_NAME,String P_FROM_DATE, String P_DATE) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_report_pkg.XXPRISM_COLLECTION_REPORT('"+ P_PROP_NAME +"','"+P_FROM_DATE +"','"+P_DATE +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }  
      
      
      
        public static String receipt(String P_RECP_NUM) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_report_pkg.XXPRISM_RECEIPT_REPORT('"+P_RECP_NUM+"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
      public static String receiptUpdated(String P_RECP_NUM) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_report_pkg.XXPRISM_RECEIPT_REPORT('"+P_RECP_NUM+"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }       
         public static String reservationContract(String p_unit_num, String p_property_Id) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_RESERVATION_CONTRACT('"+ p_unit_num +"','"+ p_property_Id +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
         
       
      /**====   
       * 
       * @param P_BU_NAME
       * @param P_INV_NUM
       * @param P_BOOKING_NUM
       * @return 
       */
      public static String invoiceReport(String P_BU_NAME, String P_INV_NUM, String P_BOOKING_NUM) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_TAX_INVOICE('"+ P_BU_NAME +"','"+ P_INV_NUM +"','"+ P_BOOKING_NUM +"') xml from dual";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    } 
     public static String updatedInvoiceReport(String P_BU_NAME, String P_INV_NUM, String P_BOOKING_NUM) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_TAX_INVOICE_UPDATED('"+ P_BU_NAME +"','"+ P_INV_NUM +"','"+ P_BOOKING_NUM +"') xml from dual";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    } 
     
    public static String receiptDetails(String P_PROP_NAME, String P_FROM_DATE, String P_TO_DATE) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_RECEIPT_DETAIL_REPORT('"+ P_PROP_NAME +"','"+ P_FROM_DATE +"','"+ P_TO_DATE +"') xml from dual";
            
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }   
      
      
      
      
      public static String errorStatus() {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_ERROR_STATUS() xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
      
       public static String saleReport(String ORG_ID, String UNIT_TYPE, String F_DATE, String T_DATE) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_SALES_REPORT('"+ ORG_ID +"','"+UNIT_TYPE +"','"+F_DATE +"','"+T_DATE +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
    
        public static String cancellationReport(String P_PROP_NAME, String P_DATE) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_CANCELLATION('"+ P_PROP_NAME +"','"+ P_DATE +"') xml from dual";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
       public static String pdcReport(String P_PROP_NAME, String P_DATE) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_PDC_REPORT('"+ P_PROP_NAME +"','"+ P_DATE +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
         
    public static void dbDestroy() throws SQLException {
        if (resultSet != null) {
            resultSet.close();
        }
        if (statement != null) {
            statement.close();
        }
        if (ps != null) {
            ps.close();
        }
        if (connection != null) {
            connection.close();
        }
    }

    public static String customerMasterReport(String P_CUST, String P_PRO_ID) {
       String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_CUST_MASTER('"+ P_CUST +"','"+ P_PRO_ID +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
    
    public static String otherCharges(String P_PROP_ID, String P_UNIT_ID) {
       String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_OTHERCHARGES_REPORT('"+ P_PROP_ID +"','"+ P_UNIT_ID +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }

    String unAppliedReceipts(String P_PROP_NAME, String P_DATE) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_UNAPPLIED_RECEIPT('"+ P_PROP_NAME +"','"+ P_DATE +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
    public static String saleSummary(String P_Sales_Period) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_SALES_SUMMARY_REPORT('"+P_Sales_Period+"') XML FROM DUAL";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
    public static String saleSummaryNew(String P_ORD_ID,String P_UNIT_TYPE,String P_FROM_DATE, String P_TO_DATE) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_SALES_SUMMARY_REPORT_NEW('"+ P_ORD_ID +"','"+ P_UNIT_TYPE +"','"+ P_FROM_DATE +"','"+P_TO_DATE+"') XML FROM DUAL";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
        public static String dailyCollection(String P_ORD_ID,String P_FROM_DATE, String P_TO_DATE) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_DAILYCOLLECTION_REPORT('"+ P_ORD_ID +"','"+ P_FROM_DATE +"','"+P_TO_DATE+"') XML FROM DUAL";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
       public static String dailyCollectionNew(String P_ORD_ID,String P_FROM_DATE, String P_TO_DATE) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_UNITDAILYCOLLECTION_REPORT('"+ P_ORD_ID +"','"+ P_FROM_DATE +"','"+P_TO_DATE+"') XML FROM DUAL";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }     
     public static String forecastReport(String P_ORD_ID,String P_FROM_DATE, String P_TO_DATE) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_FORECAST_REPORT('"+ P_ORD_ID +"','"+ P_FROM_DATE +"','"+P_TO_DATE+"') XML FROM DUAL";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }   
     
     public static String forecastReportSummary(String P_ORD_ID,String P_FROM_DATE, String P_TO_DATE) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_FORECAST_SUMMARY_REPORT('"+ P_ORD_ID +"','"+ P_FROM_DATE +"','"+P_TO_DATE+"') XML FROM DUAL";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }        
    
    public static String dldStatusReport(String P_PROJ_ID) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_DLD_STATUS('"+ P_PROJ_ID +"') XML FROM DUAL";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }     
        public static String collectionSummary(String P_ORD_ID,String P_FROM_DATE, String P_TO_DATE) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_COLLECTION_SUMMARY_REPORT('"+ P_ORD_ID +"','"+ P_FROM_DATE +"','"+P_TO_DATE+"') XML FROM DUAL";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
        
    public static String newArSummary(String P_PROJ_ID,String P_PROP_ID, String P_DATE,String P_AMOUNT) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_AR_SUMMARY('"+ P_PROJ_ID+"','"+ P_PROP_ID +"','"+P_DATE +"','"+P_AMOUNT +"') xml from dual";
           System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
    
    public static String newsalesReport(String P_ORD_ID,String P_UNIT_TYPE,String P_FROM_DATE, String P_TO_DATE) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_SALES_SUMMARY_REPORT_NEW('"+ P_ORD_ID +"','"+ P_UNIT_TYPE +"','"+ P_FROM_DATE +"','"+P_TO_DATE+"') XML FROM DUAL";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
     public static String newPriceList(String P_PROJ_ID,String P_PROP_ID,String P_UNIT_STATUS) {
       String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_PRICELIST_REPORT("+ P_PROJ_ID +","+ P_PROP_ID +",'"+ P_UNIT_STATUS +"') xml from dual";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
     public static String newCancellationReport(String P_PROP_ID, String P_DATE) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_CANCELLATION('"+ P_PROP_ID +"','"+ P_DATE +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
     
    public static String newInventoryStatus(String P_PROP_ID,String P_BUILD_ID) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_ALL_INVENTORY_STATUS('"+ P_PROP_ID +"','"+P_BUILD_ID +"') xml from dual";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    } 
    public static String dailyCollectionNewProd(String P_ORD_ID,String P_FROM_DATE, String P_TO_DATE) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_UNITDAILYCOLLECTION_REPORT('"+ P_ORD_ID +"','"+ P_FROM_DATE +"','"+P_TO_DATE+"') XML FROM DUAL";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }  
     public static String saleSummaryNewProd(String P_ORD_ID,String P_UNIT_TYPE,String P_FROM_DATE, String P_TO_DATE) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_SALES_SUMMARY_REPORT_NEW('"+ P_ORD_ID +"','"+ P_UNIT_TYPE +"','"+ P_FROM_DATE +"','"+P_TO_DATE+"') XML FROM DUAL";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
     public static String forecastReportProd(String P_ORD_ID,String P_FROM_DATE, String P_TO_DATE) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_FORECAST_REPORT('"+ P_ORD_ID +"','"+ P_FROM_DATE +"','"+P_TO_DATE+"') XML FROM DUAL";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    } 
    
     public static String collectionSummaryTest(String P_ORD_ID,String P_FROM_DATE, String P_TO_DATE) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_REPORT_PKG.XXPRISM_COLLECTION_SUMMARY_REPORT('"+ P_ORD_ID +"','"+ P_FROM_DATE +"','"+P_TO_DATE+"') XML FROM DUAL";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
        
        public static String responseToRest(String result) {
        org.json.simple.JSONObject json = new org.json.simple.JSONObject();
        json.put("result", result);
        return json.toJSONString();
        }

     public static String arDetailProd(String P_PROJ_ID,String P_PROP_ID, String P_DATE,String P_AMOUNT) {
        String xmlString = null;
        try {
            dbInitialization();
//            String sql = "select XXPRISM_report_pkg.XXPRISM_AR_DETAIL('"+ P_PROJ_NAME +"','"+P_PROP_NAME +"','To_DATE('"+P_DATE+"'','DD-MM-YYYY')) xml from dual";
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_AR_DETAIL('"+ P_PROJ_ID +"','"+P_PROP_ID +"','"+P_DATE +"','"+P_AMOUNT+"') xml from dual";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
      }
     public static String arSummaryProd(String P_PROJ_ID,String P_PROP_ID, String P_DATE,String P_AMOUNT) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_AR_SUMMARY('"+ P_PROJ_ID +"','"+P_PROP_ID +"','"+P_DATE +"','"+P_AMOUNT+"') xml from dual";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
      }
  public static String saleReportProd(String ORG_ID, String UNIT_TYPE, String F_DATE, String T_DATE) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_SALES_REPORT('"+ ORG_ID +"','"+UNIT_TYPE +"','"+F_DATE +"','"+T_DATE +"') xml from dual";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
  public static String priceListNewProd(String P_PROJ_ID,String P_PROP_ID,String P_UNIT_STATUS) {
       String xmlString = null;
        try {
            dbInitialization();
            String sql = "SELECT XXPRISM_NEW_REPORT_PKG.XXPRISM_PRICELIST_REPORT('"+ P_PROJ_ID +"','"+ P_PROP_ID +"','"+ P_UNIT_STATUS +"') xml from dual";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    } 
  public static String cancellationReportProd(String P_PROP_NAME, String P_DATE) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_CANCELLATION('"+ P_PROP_NAME +"','"+ P_DATE +"') xml from dual";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
  }
  
  public static String inventoryStatusProd(String P_PROP_ID,String P_BUILD_ID) {
        String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_ALL_INVENTORY_STATUS('"+ P_PROP_ID +"','"+P_BUILD_ID +"') xml from dual";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }
   public static String dldStatusReportProd(String P_PROJ_ID) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_DLD_STATUS('"+ P_PROJ_ID +"') XML FROM DUAL";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    }  
   public static String forecastReportSummaryProd(String P_ORD_ID,String P_FROM_DATE, String P_TO_DATE) {
         String xmlString = null;
        try {
            dbInitialization();
            String sql = "select XXPRISM_NEW_REPORT_PKG.XXPRISM_FORECAST_SUMMARY_REPORT('"+ P_ORD_ID +"','"+ P_FROM_DATE +"','"+P_TO_DATE+"') XML FROM DUAL";
            System.out.println(sql);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                xmlString = resultSet.getString("xml");
                System.out.println(xmlString);
            }

        } catch (SQLException ex) {
            Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                dbDestroy();
            } catch (SQLException ex) {
                Logger.getLogger(DbPackageCall.class.getName()).log(Level.SEVERE, null, ex);
            }
            return xmlString;
        }
    } 
   
}


