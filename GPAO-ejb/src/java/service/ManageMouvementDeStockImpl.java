/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import persistence.MouvementDeStock;
import persistence.MouvementDeStockPK;

/**
 *
 * @author kimo
 */
@Stateless
public class ManageMouvementDeStockImpl implements ManageMouvementDeStock{

    
    @PersistenceContext(unitName = "gpao_pu")
    private EntityManager entityManager;
    
    @Override
    public void create(MouvementDeStock mouvementDeStock) {
        if (entityManager != null) {
            entityManager.persist(mouvementDeStock);
        }
    }

    @Override
    public List<MouvementDeStock> findAll() {
        if (entityManager != null) {
            return entityManager.createNamedQuery("MouvementDeStock.findAll").getResultList();
        }
        return null;
    }

    @Override
    public MouvementDeStock find(MouvementDeStockPK mouvementDeStockPK) {
        if (entityManager != null) {
            return (MouvementDeStock) entityManager.find(MouvementDeStock.class, mouvementDeStockPK);
        }
        return null;
    }

    @Override
    public void edit(MouvementDeStock mouvementDeStock) {
        if (entityManager != null) {
            MouvementDeStock mouvementDeStockExistant = entityManager.find(MouvementDeStock.class, mouvementDeStock.getMouvementDeStockPK());
            if (mouvementDeStockExistant != null) {
                entityManager.merge(mouvementDeStock);
            }
        }
    }

    @Override
    public void remove(MouvementDeStock mouvementDeStock) {
        if (entityManager != null) {
            mouvementDeStock = entityManager.find(MouvementDeStock.class, mouvementDeStock.getMouvementDeStockPK());
            if (mouvementDeStock != null) {
                entityManager.remove(mouvementDeStock);
            }
        }
    }

    @Override
    public List<MouvementDeStock> findRange(int[] range) {
        if (entityManager != null) {
            javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(MouvementDeStock.class));
            javax.persistence.Query q = entityManager.createQuery(cq);
            q.setMaxResults(range[1] - range[0] + 1);
            q.setFirstResult(range[0]);
            return q.getResultList();
        }
        return null;
    }

    @Override
    public int count() {
        if (entityManager != null) {
            javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
            javax.persistence.criteria.Root<MouvementDeStock> rt = cq.from(MouvementDeStock.class);
            cq.select(entityManager.getCriteriaBuilder().count(rt));
            javax.persistence.Query q = entityManager.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }
        return 0;
    }
    
}
