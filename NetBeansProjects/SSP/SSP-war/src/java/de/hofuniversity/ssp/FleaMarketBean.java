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
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Inject;

/**
 *
 * @author lothar
 */
@Named(value = "fleaMarketBean")
@SessionScoped
public class FleaMarketBean implements Serializable {

    
    private String street;
    private int length;
    
    @EJB
    private FleaMarketEntityFacadeRemote fleaMarketEntityFacade;
    
    @Inject
    private LoginBean loginBean;
    
    public FleaMarketBean() {
    }
    
    public void reserve(){
        FleaMarketEntity entity = new FleaMarketEntity();
        entity.setStandLength(length);
        entity.setStreet(street);
        
        fleaMarketEntityFacade.create(entity);
    }
    
    public List<FleaMarketEntity> getFleaMarkets(){
        return fleaMarketEntityFacade.getReservedFleaMarketsOfCustomer(loginBean.getCustomer().getId());
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
