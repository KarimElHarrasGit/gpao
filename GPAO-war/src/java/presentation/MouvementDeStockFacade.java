/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.MouvementDeStock;

/**
 *
 * @author kimo
 */
@Stateless
public class MouvementDeStockFacade extends AbstractFacade<MouvementDeStock> {

    @PersistenceContext(unitName = "GPAO-warPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MouvementDeStockFacade() {
        super(MouvementDeStock.class);
    }
    
}
