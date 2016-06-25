/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp;

import de.hofuniversity.ssp.beans.LicencePlateFacadeRemote;
import de.hofuniversity.ssp.entities.LicencePlateEntity;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.bean.ManagedProperty;
import javax.faces.context.FacesContext;
import javax.inject.Inject;

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

    @EJB
    private LicencePlateFacadeRemote licencePlateFacade;
    
 

    @Inject
    private LoginBean loginBean;

    public LicencePlateBean() {

    }

    public void isLicencePlateExist() {

        FacesContext context = FacesContext.getCurrentInstance();
        
        if(licencePlateFacade.isLicencePlateExist(city, letters.toUpperCase(), numbers)){
            context.addMessage(null, new FacesMessage("Erfolgreich", "Das Kennzeichen \"" + city + ":" + letters.toUpperCase() + ":" + numbers + "\" ist verfügbar!"));
        }else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fehler", "Das Kennzeichen \"" + city + ":" + letters.toUpperCase() + ":" + numbers + "\" ist leider schon vergeben!"));
        }
    }

    public void reserveLicencePlate() {
        FacesContext context = FacesContext.getCurrentInstance();
        if (licencePlateFacade.isLicencePlateExist(city, letters.toUpperCase(), numbers)) {
            LicencePlateEntity entity = new LicencePlateEntity();
            entity.setCity(city);
            entity.setLetters(letters.toUpperCase());
            entity.setNumbers(numbers);
            entity.setCustomer_id(loginBean.getCustomer().getId());     
            entity.setReservationDate(new Date());
            entity.setActive(false);

            licencePlateFacade.create(entity);
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolgreich", "Das Kennzeichen \"" + city + ":" + letters.toUpperCase() + ":" + numbers + "\" ist 2 Tage für Sie reserviert!"));
        }else{
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, "Fehler", "Das Kennzeichen \"" + city + ":" + letters.toUpperCase() + ":" + numbers + "\" ist leider schon vergeben!"));
        }
    }
    
    public List<LicencePlateEntity> getLicencePlates(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DAY_OF_YEAR, -2);
        licencePlateFacade.deleteExpiredReservations(c.getTime());
        return licencePlateFacade.getReservedLicencePlatesOfCustomer(loginBean.getCustomer().getId());
    }
    
    public void deleteReservation(LicencePlateEntity entity){
        licencePlateFacade.remove(entity);
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Erfolgreich", "Das Kennzeichen \"" + entity.getCity() + ":" + entity.getLetters().toUpperCase() + ":" + entity.getNumbers() + "\" wurde erfolgreich gelöscht!"));
        
    }
    

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
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

}
