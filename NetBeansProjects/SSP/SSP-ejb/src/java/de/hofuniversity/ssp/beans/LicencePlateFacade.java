/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.hofuniversity.ssp.beans;

import de.hofuniversity.ssp.entities.LicencePlate;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lothar
 */
@Stateless
public class LicencePlateFacade extends AbstractFacade<LicencePlate> implements de.hofuniversity.ssp.beans.LicencePlateFacadeRemote {

    @PersistenceContext(unitName = "SSP")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LicencePlateFacade() {
        super(LicencePlate.class);
    }
    
}
