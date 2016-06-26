/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp;

import de.hofuniversity.ssp.beans.UserEntityFacadeRemote;
import de.hofuniversity.ssp.entities.CustomerEntity;
import de.hofuniversity.ssp.enums.Role;
import de.hofuniversity.ssp.security.HashAlgorithm;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

/**
 *
 * @author lothar
 */
@Named(value = "userBean")
@RequestScoped
public class UserBean implements Serializable {

    @EJB
    private UserEntityFacadeRemote userEntityFacade;

    private String prename;
    private String name;
    private String email;
    private String password;
    private String streetAddress;
    private int zipCode;
    private String city;
    private boolean isFleaMarktEdit;
    private boolean isLicencePlateEdit;

    private boolean isEdit;
    private String userRole;
    private long id;

    @Inject
    private UserAdministrationBean administrationBean;

    public UserBean() {
        isEdit = false;
    }

    public UserBean(CustomerEntity entity) {
        this.prename = entity.getPrename();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.streetAddress = entity.getStreetAddress();
        this.zipCode = entity.getZipCode();
        this.city = entity.getCity();
        this.isFleaMarktEdit = entity.isIsFleaMarket();
        this.isLicencePlateEdit = entity.isIsLicencePlate();
    }

    public void edit(CustomerEntity entity) {
        this.prename = entity.getPrename();
        this.name = entity.getName();
        this.email = entity.getEmail();
        this.password = entity.getPassword();
        this.streetAddress = entity.getStreetAddress();
        this.zipCode = entity.getZipCode();
        this.city = entity.getCity();
        this.isFleaMarktEdit = entity.isIsFleaMarket();
        this.isLicencePlateEdit = entity.isIsLicencePlate();
        this.userRole = entity.getUserRole();
        this.id = entity.getId();
        
        System.out.println("edit -> password: " + entity.getPassword() + " role: " + entity.getUserRole() + " id: " + entity.getId());

        isEdit = true;
    }

    public String submit() {
        System.out.println("prename: " + prename + " name: " + name
                + " email: " + email + " password: " + password
                + " streetAddress: " + streetAddress
                + " zipCode: " + zipCode + " city: " + city);
        if (userEntityFacade.findUserByEmail(email).isEmpty()) {
            CustomerEntity entity = new CustomerEntity();
            entity.setCity(city);
            entity.setEmail(email);
            entity.setName(name);
            entity.setPassword(HashAlgorithm.getPasswordHash(password));
            entity.setPrename(prename);
            entity.setStreetAddress(streetAddress);
            entity.setZipCode(zipCode);

            entity.setIsFleaMarket(true);
            entity.setIsLicencePlate(true);

            if (!userEntityFacade.findAll().isEmpty()) {
                entity.setUserRole(Role.USER.toString());
            } else {
                entity.setUserRole(Role.ADMIN.toString());
                entity.setId(1);
            }

            userEntityFacade.create(entity);

            return "/login.xhtml?faces-redirect=true";
        } else {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Diese E-Mail Adresse existiert bereits!", "Diese E-Mail Adresse existiert bereits!"));
        }
        //?faces-redirect=true

        return "";
    }

    public String submitEdit() {
        CustomerEntity entity = new CustomerEntity();
        entity.setCity(city);
        entity.setEmail(email);
        entity.setName(name);
        entity.setPassword(administrationBean.getCe().getPassword());
        entity.setPrename(prename);
        entity.setStreetAddress(streetAddress);
        entity.setZipCode(zipCode);

        entity.setIsFleaMarket(isFleaMarktEdit);
        entity.setIsLicencePlate(isLicencePlateEdit);
        entity.setUserRole(administrationBean.getCe().getUserRole());
        entity.setId(administrationBean.getCe().getId());
        userEntityFacade.edit(entity);
        isEdit = false;
        
        /*System.out.println("prename: " + prename + " name: " + name
                + " email: " + email + " password: " + password
                + " streetAddress: " + streetAddress
                + " zipCode: " + zipCode + " city: " + city + " id: " + id + " role: " + userRole);*/
        System.out.println("userAdministrationBean -> password: " + administrationBean.getCe().getPassword() + " role: " + administrationBean.getCe().getUserRole() + " id: " + administrationBean.getCe().getId());
        
        return "userAdministration";

    }

    public boolean isIsEdit() {
        return isEdit;
    }

    public void setIsEdit(boolean isEdit) {
        this.isEdit = isEdit;
    }

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

    public boolean isIsFleaMarktEdit() {
        return isFleaMarktEdit;
    }

    public void setIsFleaMarktEdit(boolean isFleaMarktEdit) {
        this.isFleaMarktEdit = isFleaMarktEdit;
    }

    public boolean isIsLicencePlateEdit() {
        return isLicencePlateEdit;
    }

    public void setIsLicencePlateEdit(boolean isLicencePlateEdit) {
        this.isLicencePlateEdit = isLicencePlateEdit;
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
