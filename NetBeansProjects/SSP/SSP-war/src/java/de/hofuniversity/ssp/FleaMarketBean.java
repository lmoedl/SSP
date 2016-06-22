/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp;

import de.hofuniversity.ssp.beans.FleaMarketEntityFacadeRemote;
import de.hofuniversity.ssp.entities.FleaMarketEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author lothar
 */
@Named(value = "fleaMarketBean")
@SessionScoped
public class FleaMarketBean implements Serializable {

    private static final int streetLength = 100;

    private String street;
    private int length;

    @EJB
    private FleaMarketEntityFacadeRemote fleaMarketEntityFacade;

    @Inject
    private LoginBean loginBean;

    public FleaMarketBean() {
    }

    public void reserve() {
        FacesContext context = FacesContext.getCurrentInstance();
        
        if (fleaMarketEntityFacade.isFleaMarketFree(street, length, streetLength)) {
            FleaMarketEntity entity = new FleaMarketEntity();
            entity.setStandLength(length);
            entity.setStreet(street);
            entity.setReservationDate(new Date());
            entity.setCustomer_id(loginBean.getCustomer().getId());

            fleaMarketEntityFacade.create(entity);
            
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolgreich", "Der Flohmarktstand mit der Länge " + length + " Meter in der Straße \"" + street + "\" ist 2 Tage lang für Sie reserviert."));
        }else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fehlgeschlagen", "Es ist nicht mehr genügend Fläche verfügbar!"));
        }
    }

    public List<FleaMarketEntity> getFleaMarkets() {
        return fleaMarketEntityFacade.getReservedFleaMarketsOfCustomer(loginBean.getCustomer().getId());
    }
    
    public void deleteReservation(FleaMarketEntity entity){
        fleaMarketEntityFacade.remove(entity);
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

}
