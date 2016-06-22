/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp.beans;

import de.hofuniversity.ssp.entities.LicencePlateEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author lothar
 */
@Remote
public interface LicencePlateFacadeRemote {

    void create(LicencePlateEntity licencePlate);

    void edit(LicencePlateEntity licencePlate);

    void remove(LicencePlateEntity licencePlate);

    LicencePlateEntity find(Object id);

    List<LicencePlateEntity> findAll();

    List<LicencePlateEntity> findRange(int[] range);

    int count();
    
    boolean isLicencePlateExist(String city, String letters, int numbers);
    
    List<LicencePlateEntity> getReservedLicencePlatesOfCustomer(long customer_id);
    
    int deleteExpiredReservations(Date date);
    
    List<LicencePlateEntity> findAllOrdered();
    
}
