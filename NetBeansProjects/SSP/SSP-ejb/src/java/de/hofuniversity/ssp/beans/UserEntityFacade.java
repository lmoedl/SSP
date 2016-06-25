/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp.beans;

import de.hofuniversity.ssp.entities.CustomerEntity;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

/**
 *
 * @author lothar
 */
@Stateless
public class UserEntityFacade extends AbstractFacade<CustomerEntity> implements UserEntityFacadeRemote {

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
    public UserEntityFacade() {
        super(CustomerEntity.class);
    }
    
    /**
     *
     * @param email
     * @return
     */
    @Override
    public List<CustomerEntity> findUserByEmail(String email){
        CriteriaBuilder builder =  getEntityManager().getCriteriaBuilder();
        CriteriaQuery cq = builder.createQuery();
        Root<CustomerEntity> c = cq.from(CustomerEntity.class);
        cq.select(c);
        cq.where(builder.equal(c.get("email"), email));
        

        return (List<CustomerEntity>) getEntityManager().createQuery(cq).getResultList();
    }
    
}
