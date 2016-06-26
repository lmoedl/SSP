/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp;

import de.hofuniversity.ssp.beans.NewFleaMarketEntityFacadeRemote;
import de.hofuniversity.ssp.entities.NewFleaMarketEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author lothar
 */
@Named(value = "createFleaMarketBean")
@SessionScoped
public class CreateFleaMarketBean implements Serializable {

    private String city;
    private String street;
    private Date date;

    @EJB
    private NewFleaMarketEntityFacadeRemote newFleaMarketEntityFacade;
    
    
    public CreateFleaMarketBean() {
        
        
    }
    
    
    public String createFleaMarket(){
        NewFleaMarketEntity entity = new NewFleaMarketEntity();
        entity.setCity(city);
        entity.setStreet(street);
        entity.setFleaMarketDate(date);
        
        
        newFleaMarketEntityFacade.create(entity);
        
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolgreich", "Der Flohmarkt in der Stadt \"" + city + "\" in der Straße \"" + street + "\" am " + new SimpleDateFormat("dd.MM.yyyy").format(date) + " wurde erfolgreich erstellet!"));
        
        return "fleaMarketReservation";
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }
    


    
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    public Date getActualDate(){
        return new Date();
    }
    
}
