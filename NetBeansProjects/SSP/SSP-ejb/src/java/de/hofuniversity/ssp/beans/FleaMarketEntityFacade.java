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
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author lothar
 */
@Stateless
public class FleaMarketEntityFacade extends AbstractFacade<FleaMarketEntity> implements de.hofuniversity.ssp.beans.FleaMarketEntityFacadeRemote {

    @PersistenceContext(unitName = "SSP")
    private EntityManager em;

    /**
     *
     * @return
     */
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    /**
     *
     */
    public FleaMarketEntityFacade() {
        super(FleaMarketEntity.class);
    }

    /**
     *
     * @param customer_id
     * @return
     */
    @Override
    public List<FleaMarketEntity> getReservedFleaMarketsOfCustomer(long customer_id) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<FleaMarketEntity> c = cq.from(FleaMarketEntity.class);
        cq.select(c);
        cq.where(builder.equal(c.get("customer_id"), customer_id));

        System.out.println("customer_id" + customer_id);

        return (List<FleaMarketEntity>) getEntityManager().createQuery(cq).getResultList();
    }

    /**
     *
     * @param street
     * @param length
     * @param streetLength
     * @return
     */
    @Override
    public boolean isFleaMarketFree(String street, int length, int streetLength) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<FleaMarketEntity> c = cq.from(FleaMarketEntity.class);
        cq.select(c);
        cq.where(builder.equal(c.get("street"), street));

        List<FleaMarketEntity> list = getEntityManager().createQuery(cq).getResultList();

        int resultLength = 0;

        for (int i = 0; i < list.size(); i++) {
            FleaMarketEntity entity = list.get(i);
            resultLength += entity.getStandLength();

        }

        return streetLength > resultLength + length;
    }

    /**
     *
     * @return
     */
    @Override
    public List<FleaMarketEntity> findAllOrdered(){
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<FleaMarketEntity> c = cq.from(FleaMarketEntity.class);
        cq.select(c);
        cq.orderBy(builder.asc(c.get("reservationDate")));
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    /**
     *
     * @param date
     * @return
     */
    @Override
    public int deleteExpiredReservations(Date date) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaDelete cq = builder.createCriteriaDelete(FleaMarketEntity.class);
        Root<FleaMarketEntity> c = cq.from(FleaMarketEntity.class);
        
        cq.where(builder.lessThan(c.get("reservationDate"), date));
        return getEntityManager().createQuery(cq).executeUpdate();
    }
}
