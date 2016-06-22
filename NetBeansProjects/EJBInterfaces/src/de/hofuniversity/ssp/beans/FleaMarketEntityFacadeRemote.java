/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp.beans;

import de.hofuniversity.ssp.entities.FleaMarketEntity;
import de.hofuniversity.ssp.entities.LicencePlateEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author lothar
 */
@Remote
public interface FleaMarketEntityFacadeRemote {

    void create(FleaMarketEntity fleaMarketEntity);

    void edit(FleaMarketEntity fleaMarketEntity);

    void remove(FleaMarketEntity fleaMarketEntity);

    FleaMarketEntity find(Object id);

    List<FleaMarketEntity> findAll();

    List<FleaMarketEntity> findRange(int[] range);

    int count();
    
    List<FleaMarketEntity> getReservedFleaMarketsOfCustomer(long customer_id);
    
    boolean isFleaMarketFree(String street, int length, int streetLength);
    
    List<FleaMarketEntity> findAllOrdered();
    
    int deleteExpiredReservations(Date date);
    
}
