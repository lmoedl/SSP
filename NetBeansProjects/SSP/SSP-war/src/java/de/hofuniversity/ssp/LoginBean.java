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
import de.hofuniversity.ssp.security.HashAlgorithm;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

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
        
        List<CustomerEntity> list = userEntityFacade.findUserByEmail(email);
        if(list.isEmpty()){
            FacesContext context = FacesContext.getCurrentInstance();
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fehlgeschlagen", "Diese E-Mail Adresse existiert nicht!"));
             return "";
        }
        
        CustomerEntity entity = list.get(0);
        
        if(entity != null && entity.getPassword().equals(HashAlgorithm.getPasswordHash(password))){
            customer = entity;
        }else{
            FacesContext context = FacesContext.getCurrentInstance();
             context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fehlgeschlagen", "Falsches Passwort!"));
             return "";
        }
        
        
        return "/index.xhtml";
    }
    
    public String logout(){
        customer = null;
        return "/index.xhtml";
    }
    
  
    public boolean isFleaMarketRight(){
        return customer != null && customer.isIsFleaMarket();
    }
    
    public boolean isLicencePlateRight(){
        return customer != null && customer.isIsLicencePlate();
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
