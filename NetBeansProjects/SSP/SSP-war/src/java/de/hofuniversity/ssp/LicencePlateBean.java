/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp;

import de.hofuniversity.ssp.entities.CustomerEntity;
import de.hofuniversity.ssp.interfaces.LoggedIn;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 *
 * @author lothar
 */
@Named(value = "licencePlateBean")
@SessionScoped
public class LicencePlateBean implements Serializable {

    private String city;
    private String letters;
    private int numbers;
    
    private @LoggedIn CustomerEntity customer;

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }
    
    

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getLetters() {
        return letters;
    }

    public void setLetters(String letters) {
        this.letters = letters;
    }

    public int getNumbers() {
        return numbers;
    }

    public void setNumbers(int numbers) {
        this.numbers = numbers;
    }
    
    
    
    public LicencePlateBean() {
    }
    
}
