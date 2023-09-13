/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.prism.reports;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author gautham.r
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(com.prism.reports.ArDetail.class);
        resources.add(com.prism.reports.ArSummary.class);
        resources.add(com.prism.reports.CancellationReport.class);
        resources.add(com.prism.reports.CustomerMaster.class);
        resources.add(com.prism.reports.CustomerStatement.class);
        resources.add(com.prism.reports.DLDStatusReport.class);
        resources.add(com.prism.reports.DailyCollectionReport.class);
        resources.add(com.prism.reports.DailyCollectionReport_New.class);
        resources.add(com.prism.reports.ErrorStatus.class);
        resources.add(com.prism.reports.ForecastReport.class);
        resources.add(com.prism.reports.ForecastReportSummary.class);
        resources.add(com.prism.reports.InventoryStatus.class);
        resources.add(com.prism.reports.InvoiceReport.class);
        resources.add(com.prism.reports.New_AR_Detail_Prod.class);
        resources.add(com.prism.reports.New_AR_Summary_Prod.class);
        resources.add(com.prism.reports.New_Cancellation_Prod.class);
        resources.add(com.prism.reports.New_CollectionSummary_Prod.class);
        resources.add(com.prism.reports.New_DLDStatusReport_Prod.class);
        resources.add(com.prism.reports.New_Daily_Collection_Prod.class);
        resources.add(com.prism.reports.New_Forecast_Report_Prod.class);
        resources.add(com.prism.reports.New_Forecast_Summary_Prod.class);
        resources.add(com.prism.reports.New_Inventory_Prod.class);
        resources.add(com.prism.reports.New_Pricelist_Prod.class);
        resources.add(com.prism.reports.New_Sales_Report_Prod.class);
        resources.add(com.prism.reports.New_Sales_Summary_Prod.class);
        resources.add(com.prism.reports.OtherChargesReport.class);
        resources.add(com.prism.reports.PdcReport.class);
        resources.add(com.prism.reports.Receipt.class);
        resources.add(com.prism.reports.ReceiptDetail.class);
        resources.add(com.prism.reports.ReceiptUpdated.class);
        resources.add(com.prism.reports.ReservationContract.class);
        resources.add(com.prism.reports.SaleSummary.class);
        resources.add(com.prism.reports.SaleSummaryNew.class);
        resources.add(com.prism.reports.SalesReport.class);
        resources.add(com.prism.reports.SpaReport.class);
        resources.add(com.prism.reports.SpaReportArabic.class);
        resources.add(com.prism.reports.UnAppliedReceipt.class);
        resources.add(com.prism.reports.UnitPriceList.class);
        resources.add(com.prism.reports.UnitStatus.class);
        resources.add(com.prism.reports.UnitStatusNew.class);
        resources.add(com.prism.reports.UnitWiseCollectionReport.class);
        resources.add(com.prism.reports.UpdatedInvoiceReport.class);
    }
    
}
