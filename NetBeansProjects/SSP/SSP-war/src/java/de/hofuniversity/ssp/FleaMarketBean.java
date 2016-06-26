/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp;

import de.hofuniversity.ssp.beans.FleaMarketEntityFacadeRemote;
import de.hofuniversity.ssp.beans.NewFleaMarketEntityFacadeRemote;
import de.hofuniversity.ssp.entities.FleaMarketEntity;
import de.hofuniversity.ssp.entities.NewFleaMarketEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.html.HtmlColumn;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.component.html.HtmlOutputText;
import javax.faces.component.html.HtmlPanelGrid;
import javax.faces.component.html.HtmlPanelGroup;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.swing.text.html.HTML;

/**
 *
 * @author lothar
 */
@Named(value = "fleaMarketBean")
@SessionScoped
public class FleaMarketBean implements Serializable {

    private static final int streetLength = 100;

    private HtmlPanelGroup dataTableGroup1;
    private HtmlPanelGroup dataTableGroup2;

    private String street;
    private int length;

    private FleaMarketEntity fleaMarketEntity;

    @EJB
    private FleaMarketEntityFacadeRemote fleaMarketEntityFacade;

    @EJB
    private NewFleaMarketEntityFacadeRemote newFleaMarketEntityFacade;

    @Inject
    private LoginBean loginBean;

    public FleaMarketBean() {
    }

    public String reserve() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (!loginBean.isLoggedIn()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fehlgeschlagen", "Bitte zuerst anmelden!"));
            return "";
        } else if (!loginBean.isFleaMarketRight()) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fehlgeschlagen", "Sie haben keine Berechtigung zum Reservieren eines Flohmarktstandes!"));
            return "";
        }

        if (fleaMarketEntityFacade.isFleaMarketFree(street, length, streetLength)) {
            FleaMarketEntity entity = new FleaMarketEntity();
            entity.setStandLength(length);
            entity.setStreet(street);
            entity.setReservationDate(new Date());
            entity.setCustomer_id(loginBean.getCustomer().getId());

            fleaMarketEntityFacade.create(entity);
            
            //dataTableGroup1 = null;
            //dataTableGroup2 = null;

            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolgreich", "Der Flohmarktstand mit der Länge " + length + " Meter in der Straße \"" + street + "\" ist 7 Tage für Sie reserviert!"));
        } else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fehlgeschlagen", "Es ist nicht mehr genügend Fläche verfügbar!"));
        }
        
        return "fleaMarket";
    }

    public List<FleaMarketEntity> getReservedFleaMarketsOfCustomer() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -7);
        fleaMarketEntityFacade.deleteExpiredReservations(c.getTime());
        return fleaMarketEntityFacade.getReservedFleaMarketsOfCustomer(loginBean.getCustomer().getId());
    }

    public List<NewFleaMarketEntity> getFleaMarkets() {

        newFleaMarketEntityFacade.deleteExpiredFleaMarkets(new Date());
        return newFleaMarketEntityFacade.findAll();
    }

    public void deleteReservation(FleaMarketEntity entity) {
        fleaMarketEntityFacade.remove(entity);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolgreich", "Der Flohmarktstand mit der Länge " + entity.getStandLength() + " Meter in der Straße \"" + entity.getStreet() + "\" wurde erfolgreich gelöscht!"));
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public FleaMarketEntity getFleaMarketEntity() {
        return fleaMarketEntity;
    }

    public void setFleaMarketEntity(FleaMarketEntity fleaMarketEntity) {
        this.fleaMarketEntity = fleaMarketEntity;
    }

    //Flohmarkt Grafik
    public HtmlPanelGroup getDataTableGroup1() {
     
            populateDataTable1();
        
        return dataTableGroup1;
    }

    public void setDataTableGroup1(HtmlPanelGroup dataTableGroup1) {
        this.dataTableGroup1 = dataTableGroup1;
    }

    public HtmlPanelGroup getDataTableGroup2() {
        
            populateDataTable2(); 
      
        return dataTableGroup2;
    }

    public void setDataTableGroup2(HtmlPanelGroup dataTableGroup2) {
        this.dataTableGroup2 = dataTableGroup2;
    }
    

    
    
    
    
    private void populateDataTable1() {
        HtmlDataTable dataTable = new HtmlDataTable();

        int reservedLengt = getReservedLength();
        
        for (int i = 0; i <= 50; i++) {
            HtmlColumn column = new HtmlColumn();
            dataTable.getChildren().add(column);
          
            
            

            HtmlOutputText idHeader = new HtmlOutputText();
            idHeader.setValue("ID");
            if(i < reservedLengt){
            idHeader.setStyleClass("reservedStand");
            }else{
                idHeader.setStyleClass("notReservedStand");
            }
            column.setHeader(idHeader);
            
            
        }

        dataTableGroup1 = new HtmlPanelGroup();
        dataTableGroup1.getChildren().add(dataTable);
    }
    
    private void populateDataTable2() {
        HtmlDataTable dataTable = new HtmlDataTable();

        int l = getReservedLength();
        
        int reservedLength = 0;
        if(l > 50){
            reservedLength = l - 50;
        }
        
        for (int i = 0; i <= 50; i++) {
            HtmlColumn column = new HtmlColumn();
            dataTable.getChildren().add(column);
          
            
            

            HtmlOutputText idHeader = new HtmlOutputText();
            idHeader.setValue("ID");
            if(i < reservedLength){
            idHeader.setStyleClass("reservedStand");
            }else{
                idHeader.setStyleClass("notReservedStand");
            }
            column.setHeader(idHeader);
            
            
        }

        dataTableGroup2 = new HtmlPanelGroup();
        dataTableGroup2.getChildren().add(dataTable);
    }
    
    private int getReservedLength(){
        List<FleaMarketEntity> list = fleaMarketEntityFacade.findAll();
        
        int reservedLength = 0;
        
        for(int i = 0; i< list.size(); i++){
            reservedLength += list.get(i).getStandLength();
        }
        
        return reservedLength;
        
    }
    

}
