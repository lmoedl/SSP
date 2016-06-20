/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp;

import de.hofuniversity.ssp.beans.UserEntityFacadeRemote;
import de.hofuniversity.ssp.entities.CustomerEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author lothar
 */
@Named(value = "userAdministrationBean")
@SessionScoped
public class UserAdministrationBean implements Serializable {

    @EJB
    private UserEntityFacadeRemote userEntityFacade;
    
    public UserAdministrationBean() {
    }
    
    public List<CustomerEntity> getUsers(){
        return userEntityFacade.findAll();
    }
    
    
}
