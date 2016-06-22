/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp.entities;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;


/**
 *
 * @author lothar
 */
@Entity(name = "customer")
public class CustomerEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JoinColumn
    private long id;
    
    @Column(name = "email")
    private String email;
    @Column(name = "name")
    private String name;
    @Column(name="prename")
    private String prename;
    @Column(name = "password")
    private String password;
    @Column(name = "streetAddress")
    private String streetAddress;
    @Column(name = "zipCode")
    private int zipCode;
    @Column(name = "city")
    private String city;
    
    @Column(name = "isFleaMarket")
    private boolean isFleaMarket;
    
    @Column(name = "isLicencePlate")
    private boolean isLicencePlate;
    
    @Column(name = "userRole")
    private String userRole;

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    
    

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isIsFleaMarket() {
        return isFleaMarket;
    }

    public void setIsFleaMarket(boolean isFleaMarket) {
        this.isFleaMarket = isFleaMarket;
    }

    public boolean isIsLicencePlate() {
        return isLicencePlate;
    }

    public void setIsLicencePlate(boolean isLicencePlate) {
        this.isLicencePlate = isLicencePlate;
    }
    
    
    
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrename() {
        return prename;
    }

    public void setPrename(String prename) {
        this.prename = prename;
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

    
    
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) id;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CustomerEntity)) {
            return false;
        }
        CustomerEntity other = (CustomerEntity) object;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.hofuniversity.ssp.User[ id=" + id + " ]";
    }
      
}
