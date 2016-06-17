/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp.beans;

import de.hofuniversity.ssp.entities.CustomerEntity;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author lothar
 */
@Remote
public interface UserEntityFacadeRemote {

    void create(CustomerEntity userEntity);

    void edit(CustomerEntity userEntity);

    void remove(CustomerEntity userEntity);

    CustomerEntity find(Object id);

    List<CustomerEntity> findAll();

    List<CustomerEntity> findRange(int[] range);

    int count();
    
}
