/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp.beans;

import de.hofuniversity.ssp.entities.NewFleaMarketEntity;
import de.hofuniversity.ssp.entities.NewFleaMarketEntity_;
import java.util.Date;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.Root;

/**
 *
 * @author lothar
 */
@Stateless
public class NewFleaMarketEntityFacade extends AbstractFacade<NewFleaMarketEntity> implements de.hofuniversity.ssp.beans.NewFleaMarketEntityFacadeRemote {

    @PersistenceContext(unitName = "SSP")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public NewFleaMarketEntityFacade() {
        super(NewFleaMarketEntity.class);
    }
    
    @Override
    public int deleteExpiredFleaMarkets(Date date) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaDelete cq = builder.createCriteriaDelete(NewFleaMarketEntity.class);
        Root<NewFleaMarketEntity> c = cq.from(NewFleaMarketEntity.class);
        
        cq.where(builder.lessThan(c.get(NewFleaMarketEntity_.fleaMarketDate), date));
        return getEntityManager().createQuery(cq).executeUpdate();
    }
    
}
