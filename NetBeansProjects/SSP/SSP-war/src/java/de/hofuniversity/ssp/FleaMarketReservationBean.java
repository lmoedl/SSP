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
    
    public List<FleaMarketEntity> getReservedFleaMarkets(){
        return fleaMarketEntityFacade.findAllOrdered();
    }
    
    public void deleteFleaMarketReservation(FleaMarketEntity entity){
        fleaMarketEntityFacade.remove(entity);
    }
    
        public void deleteExpiredReservations(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -2);
        fleaMarketEntityFacade.deleteExpiredReservations(c.getTime());
    }
}
