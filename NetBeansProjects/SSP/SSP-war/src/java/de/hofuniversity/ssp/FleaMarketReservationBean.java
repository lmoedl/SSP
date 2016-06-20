/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp;

import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author lothar
 */
@Named(value = "fleaMarketReservationBean")
@SessionScoped
public class FleaMarketReservationBean implements Serializable {

    /**
     * Creates a new instance of FleaMarketReservationBean
     */
    public FleaMarketReservationBean() {
    }
    
}
