/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp;

import de.hofuniversity.ssp.beans.UserEntityFacadeRemote;
import de.hofuniversity.ssp.entities.CustomerEntity;
import de.hofuniversity.ssp.enums.Role;
import de.hofuniversity.ssp.interfaces.LoggedIn;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.inject.Produces;

/**
 *
 * @author lothar
 */
@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

    @EJB
    private UserEntityFacadeRemote userEntityFacade;
    
    private String email;
    private String password;
    
    private CustomerEntity customer;
    
    public LoginBean() {
        
    }
    
    public String login(){
        
        CustomerEntity entity = userEntityFacade.findUserByEmail(email);
        
        if(entity != null){
            customer = entity;
        }
        
        
        return "/index.xhtml";
    }
    
    public String logout(){
        customer = null;
        return "index";
    }
    
    public String checkLoginStatus(){
        if(isLoggedIn()){
            logout();
            return "index";
        }else{
            return "login";
        }
    }
    
    public boolean isLoggedIn(){
        return customer != null;
    }
    
    public boolean isAdmin(){
        return customer != null && customer.getUserRole().equals(Role.ADMIN.toString());
    }
    
    @Produces @LoggedIn CustomerEntity getCurrentUser(){
        return customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    
    
    
}
