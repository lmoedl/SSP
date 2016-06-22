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
import javax.inject.Inject;

/**
 *
 * @author lothar
 */
@Named(value = "userAdministrationBean")
@SessionScoped
public class UserAdministrationBean implements Serializable {

    @EJB
    private UserEntityFacadeRemote userEntityFacade;
    
    @Inject
    private UserBean userBean;
    
    public UserAdministrationBean() {
    }
    
    public List<CustomerEntity> getUsers(){
        return userEntityFacade.findAll();
    }
    
    public void deleteUser(CustomerEntity customerEntity){
        userEntityFacade.remove(customerEntity);
    }
    
    public String editUser(CustomerEntity entity){
        userBean.edit(entity);
        
        return "register";
    }
    
    
}
