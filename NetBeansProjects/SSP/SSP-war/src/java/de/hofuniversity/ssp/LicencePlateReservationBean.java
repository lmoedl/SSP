/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp;

import de.hofuniversity.ssp.beans.LicencePlateFacadeRemote;
import de.hofuniversity.ssp.entities.LicencePlateEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author lothar
 */
@Named(value = "licencePlateReservationBean")
@SessionScoped
public class LicencePlateReservationBean implements Serializable {

    @EJB
    private LicencePlateFacadeRemote licencePlateFacade;
    
    public LicencePlateReservationBean() {
    }
    
    
    public List<LicencePlateEntity> getLicencePlates(){
        
        return licencePlateFacade.findAllOrdered();
    }
    
    public void deleteExpiredReservations(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_MONTH, -2);
        licencePlateFacade.deleteExpiredReservations(c.getTime());
    }
    
    public void deleteLicencePlateReservation(LicencePlateEntity entity){
        licencePlateFacade.remove(entity);
    }
    
    
}
