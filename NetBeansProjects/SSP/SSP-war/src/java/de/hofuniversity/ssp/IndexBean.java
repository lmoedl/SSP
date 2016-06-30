/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp;

import de.hofuniversity.ssp.beans.FleaMarketEntityFacadeRemote;
import de.hofuniversity.ssp.beans.LicencePlateFacadeRemote;
import de.hofuniversity.ssp.beans.NewFleaMarketEntityFacadeRemote;
import de.hofuniversity.ssp.beans.UserEntityFacadeRemote;
import de.hofuniversity.ssp.entities.CustomerEntity;
import de.hofuniversity.ssp.entities.FleaMarketEntity;
import de.hofuniversity.ssp.entities.LicencePlateEntity;
import de.hofuniversity.ssp.entities.NewFleaMarketEntity;
import de.hofuniversity.ssp.enums.Role;
import de.hofuniversity.ssp.interfaces.LoggedIn;
import de.hofuniversity.ssp.security.HashAlgorithm;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author lothar
 */
@Named(value = "indexBean")
@RequestScoped
public class IndexBean {

    private @LoggedIn
    CustomerEntity customer;

    @EJB
    private UserEntityFacadeRemote userEntityFacade;

    @EJB
    private LicencePlateFacadeRemote licencePlateFacade;

    @EJB
    private FleaMarketEntityFacadeRemote fleaMarketEntityFacade;

    @EJB
    private NewFleaMarketEntityFacadeRemote newFleaMarketEntityFacade;

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public boolean isLoggedIn() {
        return customer != null;
    }

    public boolean isAdmin() {
        return customer != null && customer.getUserRole().equals(Role.ADMIN.toString());
    }

    public IndexBean() {
    }

    public void addExampleData() {
        if (userEntityFacade.findAll().isEmpty()) {
            addCustomers();
        }
        if (newFleaMarketEntityFacade.findAll().isEmpty()) {
            addFleaMarket();
        }
        if (fleaMarketEntityFacade.findAll().isEmpty()) {
            addFleaMarketReservations();
        }
        if (licencePlateFacade.findAll().isEmpty()) {
            addLicencePlates();
        }
    }

    private void addCustomers() {
        CustomerEntity entity1 = new CustomerEntity();
        entity1.setCity("Hof");
        entity1.setEmail("admin@admin.com");
        entity1.setIsFleaMarket(true);
        entity1.setIsLicencePlate(true);
        entity1.setName("Wurscht");
        entity1.setPassword(HashAlgorithm.getPasswordHash("1234"));
        entity1.setPrename("Hans");
        entity1.setStreetAddress("Alfons-Goppel-Platz 1");
        entity1.setUserRole(Role.ADMIN.toString());
        entity1.setZipCode(95028);
        entity1.setId(1);

        userEntityFacade.create(entity1);

        CustomerEntity entity2 = new CustomerEntity();
        entity2.setCity("Hof");
        entity2.setEmail("test@test.com");
        entity2.setIsFleaMarket(true);
        entity2.setIsLicencePlate(true);
        entity2.setName("Müller");
        entity2.setPassword(HashAlgorithm.getPasswordHash("1234"));
        entity2.setPrename("Burak");
        entity2.setStreetAddress("Wirthstraße 2");
        entity2.setUserRole(Role.USER.toString());
        entity2.setZipCode(95028);
        entity2.setId(2);

        userEntityFacade.create(entity2);

    }

    private void addLicencePlates() {
        LicencePlateEntity entity1 = new LicencePlateEntity();
        entity1.setActive(true);
        entity1.setCity("HO");
        entity1.setCustomer_id(1);
        entity1.setLetters("AA");
        entity1.setNumbers(12);
        entity1.setReservationDate(new Date());

        licencePlateFacade.create(entity1);

        LicencePlateEntity entity2 = new LicencePlateEntity();
        entity2.setActive(false);
        entity2.setCity("HO");
        entity2.setCustomer_id(1);
        entity2.setLetters("BB");
        entity2.setNumbers(121);

        Calendar c = Calendar.getInstance(Locale.GERMAN);
        c.set(2016, 8, 8);

        entity2.setReservationDate(c.getTime());

        licencePlateFacade.create(entity2);

        LicencePlateEntity entity3 = new LicencePlateEntity();
        entity3.setActive(true);
        entity3.setCity("HO");
        entity3.setCustomer_id(2);
        entity3.setLetters("XX");
        entity3.setNumbers(456);
        entity3.setReservationDate(new Date());

        licencePlateFacade.create(entity3);

    }

    private void addFleaMarket() {
        NewFleaMarketEntity entity1 = new NewFleaMarketEntity();
        entity1.setCity("Hof");
        entity1.setStreet("Enoch-Widman-Straße");
        entity1.setId(1l);

        Calendar c = Calendar.getInstance(Locale.GERMAN);
        c.set(2016, 8, 28);

        entity1.setFleaMarketDate(c.getTime());

        newFleaMarketEntityFacade.create(entity1);

        NewFleaMarketEntity entity2 = new NewFleaMarketEntity();
        entity2.setCity("Naila");
        entity2.setStreet("Königsstraße");
        entity2.setId(2l);

        Calendar c2 = Calendar.getInstance(Locale.GERMAN);
        c2.set(2016, 8, 28);

        entity2.setFleaMarketDate(c2.getTime());

        newFleaMarketEntityFacade.create(entity2);

    }

    private void addFleaMarketReservations() {
        FleaMarketEntity entity1 = new FleaMarketEntity();
        entity1.setActive(true);
        entity1.setCustomer_id(1);
        Calendar c2 = Calendar.getInstance(Locale.GERMAN);
        c2.set(2016, 8, 28);
        entity1.setFleaMarketDate(c2.getTime());
        entity1.setReservationDate(new Date());
        entity1.setStandLength(6);
        entity1.setStreet("Hof - Enoch-Widman-Straße - 28.08.2016");

        fleaMarketEntityFacade.create(entity1);

        FleaMarketEntity entity2 = new FleaMarketEntity();
        entity2.setActive(false);
        entity2.setCustomer_id(1);
        Calendar c = Calendar.getInstance(Locale.GERMAN);
        c.set(2016, 8, 28);
        entity2.setFleaMarketDate(c.getTime());
        entity2.setReservationDate(new Date());
        entity2.setStandLength(6);
        entity2.setStreet("Naila - Königsstraße - 28.08.2016");

        fleaMarketEntityFacade.create(entity2);

    }

}
