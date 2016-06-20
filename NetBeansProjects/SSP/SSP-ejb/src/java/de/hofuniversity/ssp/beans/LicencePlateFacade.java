/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp.beans;

import de.hofuniversity.ssp.entities.CustomerEntity;
import de.hofuniversity.ssp.entities.LicencePlateEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author lothar
 */
@Stateless
public class LicencePlateFacade extends AbstractFacade<LicencePlateEntity> implements de.hofuniversity.ssp.beans.LicencePlateFacadeRemote {

    @PersistenceContext(unitName = "SSP")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicencePlateFacade() {
        super(LicencePlateEntity.class);
    }
    
    @Override
    public boolean isLicencePlateExist(String city, String letters, int numbers){
        CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<LicencePlateEntity> c = cq.from(LicencePlateEntity.class);
        cq.select(c);
        cq.where(builder.equal(c.get("city"), city), builder.equal(c.get("letters"), letters), builder.equal(c.get("numbers"), numbers));

        return getEntityManager().createQuery(cq).getResultList().isEmpty();
    }
    
    @Override
    public List<LicencePlateEntity> getReservedLicencePlatesOfCustomer(long customer_id){
        CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<LicencePlateEntity> c = cq.from(LicencePlateEntity.class);
        cq.select(c);
        cq.where(builder.equal(c.get("customer_id"), customer_id));
        
        System.out.println("customer_id" + customer_id);
        
        return (List<LicencePlateEntity>) getEntityManager().createQuery(cq).getResultList();
    }
    
}
