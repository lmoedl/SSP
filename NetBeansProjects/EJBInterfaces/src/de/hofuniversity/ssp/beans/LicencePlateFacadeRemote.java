/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp.beans;

import de.hofuniversity.ssp.entities.LicencePlate;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author lothar
 */
@Remote
public interface LicencePlateFacadeRemote {

    void create(LicencePlate licencePlate);

    void edit(LicencePlate licencePlate);

    void remove(LicencePlate licencePlate);

    LicencePlate find(Object id);

    List<LicencePlate> findAll();

    List<LicencePlate> findRange(int[] range);

    int count();
    
}
