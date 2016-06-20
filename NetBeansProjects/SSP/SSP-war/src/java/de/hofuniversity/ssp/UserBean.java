/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp;

import de.hofuniversity.ssp.beans.UserEntityFacadeRemote;
import de.hofuniversity.ssp.entities.CustomerEntity;
import de.hofuniversity.ssp.enums.Role;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;

/**
 *
 * @author lothar
 */
@Named(value = "userBean")
@SessionScoped
public class UserBean implements Serializable{

    @EJB
    private UserEntityFacadeRemote userEntityFacade;

     

    private String prename;
    private String name;
    private String email;
    private String password;
    private String streetAddress;
    private int zipCode;
    private String city;
        
    
    public String submit(){
        System.out.println("prename: " + prename + " name: " + name
        + " email: " + email + " password: " + password 
        + " streetAddress: " + streetAddress + 
                " zipCode: " + zipCode + " city: " + city);
        
        CustomerEntity entity = new CustomerEntity();
        entity.setCity(city);
        entity.setEmail(email);
        entity.setName(name);
        entity.setPassword(password);
        entity.setPrename(prename);
        entity.setStreetAddress(streetAddress);
        entity.setZipCode(zipCode);
        entity.setIsFleaMarket(true);
        entity.setIsLicencePlate(true);
        entity.setUserRole(Role.USER.toString());
        
        userEntityFacade.create(entity);
        
        return "/login.xhtml";
        //?faces-redirect=true
        
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getStreetAddress() {
        return streetAddress;
    }

    public void setStreetAddress(String streetAddress) {
        this.streetAddress = streetAddress;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
    
    
}
