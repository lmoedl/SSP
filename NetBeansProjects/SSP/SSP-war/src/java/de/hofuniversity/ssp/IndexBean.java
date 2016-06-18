/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp;

import de.hofuniversity.ssp.entities.CustomerEntity;
import de.hofuniversity.ssp.enums.Role;
import de.hofuniversity.ssp.interfaces.LoggedIn;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author lothar
 */
@Named(value = "indexBean")
@RequestScoped
public class IndexBean {

    private @LoggedIn CustomerEntity customer;

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    
    public boolean isLoggedIn(){
        return customer != null;
    }
    
    public boolean isAdmin(){
        return customer != null && customer.getUserRole().equals(Role.ADMIN.toString());
    }
    
    public IndexBean() {
    }
    
}
