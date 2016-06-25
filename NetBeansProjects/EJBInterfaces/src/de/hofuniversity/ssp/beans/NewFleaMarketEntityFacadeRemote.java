/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp.beans;

import de.hofuniversity.ssp.entities.NewFleaMarketEntity;
import java.util.Date;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author lothar
 */
@Remote
public interface NewFleaMarketEntityFacadeRemote {

    void create(NewFleaMarketEntity newFleaMarketEntity);

    void edit(NewFleaMarketEntity newFleaMarketEntity);

    void remove(NewFleaMarketEntity newFleaMarketEntity);

    NewFleaMarketEntity find(Object id);

    List<NewFleaMarketEntity> findAll();

    List<NewFleaMarketEntity> findRange(int[] range);

    int count();
    
    int deleteExpiredFleaMarkets(Date date);
    
}
