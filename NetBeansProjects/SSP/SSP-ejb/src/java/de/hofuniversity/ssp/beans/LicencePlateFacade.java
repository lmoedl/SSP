/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp.beans;

import de.hofuniversity.ssp.entities.CustomerEntity;
import de.hofuniversity.ssp.entities.CustomerEntity_;
import de.hofuniversity.ssp.entities.FleaMarketEntity;
import de.hofuniversity.ssp.entities.LicencePlateEntity;
import de.hofuniversity.ssp.entities.LicencePlateEntity_;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.SetJoin;

/**
 *
 * @author lothar
 */
@Stateless
public class LicencePlateFacade extends AbstractFacade<LicencePlateEntity> implements de.hofuniversity.ssp.beans.LicencePlateFacadeRemote {

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
    public LicencePlateFacade() {
        super(LicencePlateEntity.class);
    }
    
    /**
     *
     * @return
     */
    @Override
    public List<LicencePlateEntity> findAllOrdered(){
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<LicencePlateEntity> c = cq.from(LicencePlateEntity.class);
        //Join<LicencePlateEntity, CustomerEntity> join = c.join("join");
        cq.select(c);
        //cq.where(builder.equal(c.get(LicencePlateEntity_.customer_id), CustomerEntity_.id));
        cq.orderBy(builder.asc(c.get("reservationDate")));
        
        return getEntityManager().createQuery(cq).getResultList();
    }
    
    /**
     *
     * @param city
     * @param letters
     * @param numbers
     * @return
     */
    @Override
    public boolean isLicencePlateExist(String city, String letters, int numbers){
        CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<LicencePlateEntity> c = cq.from(LicencePlateEntity.class);
        cq.select(c);
        cq.where(builder.equal(c.get("city"), city), builder.equal(c.get("letters"), letters), builder.equal(c.get("numbers"), numbers));

        return getEntityManager().createQuery(cq).getResultList().isEmpty();
    }
    
    /**
     *
     * @param customer_id
     * @return
     */
    @Override
    public List<LicencePlateEntity> getReservedLicencePlatesOfCustomer(long customer_id){
        CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<LicencePlateEntity> c = cq.from(LicencePlateEntity.class);
        cq.select(c);
        cq.where(builder.equal(c.get("customer_id"), customer_id));
        cq.orderBy(builder.asc(c.get("reservationDate")));
        
        System.out.println("customer_id" + customer_id);
        
        return (List<LicencePlateEntity>) getEntityManager().createQuery(cq).getResultList();
    }

    /**
     *
     * @param date
     * @return
     */
    @Override
    public int deleteExpiredReservations(Date date) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaDelete cq = builder.createCriteriaDelete(LicencePlateEntity.class);
        Root<LicencePlateEntity> c = cq.from(LicencePlateEntity.class);
        
        cq.where(builder.lessThan(c.get("reservationDate"), date));
        return getEntityManager().createQuery(cq).executeUpdate();
    }
    
}
