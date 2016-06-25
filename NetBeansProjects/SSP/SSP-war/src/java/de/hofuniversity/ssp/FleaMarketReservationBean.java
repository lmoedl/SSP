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
import java.util.Calendar;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 *
 * @author lothar
 */
@Named(value = "fleaMarketReservationBean")
@SessionScoped
public class FleaMarketReservationBean implements Serializable {

    @EJB
    private FleaMarketEntityFacadeRemote fleaMarketEntityFacade;

    public FleaMarketReservationBean() {
    }

    public List<FleaMarketEntity> getReservedFleaMarkets() {
        return fleaMarketEntityFacade.findAllOrdered();
    }

    public void deleteFleaMarketReservation(FleaMarketEntity entity) {
        fleaMarketEntityFacade.remove(entity);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolgreich", "Der Flohmarktstand mit der Länge " + entity.getStandLength() + " Meter in der Straße \"" + entity.getStreet() + "\" wurde erfolgreich gelöscht!"));
    }

    public void deleteExpiredReservations() {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -7);
        fleaMarketEntityFacade.deleteExpiredReservations(c.getTime());
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolgreich", "Alle abgelaufenen Standreservierungen wurden erfolgreich gelöscht!"));
    }

    public void setActive(FleaMarketEntity entity){
        
        entity.setActive(!entity.isActive());
        fleaMarketEntityFacade.edit(entity);
    }
}
