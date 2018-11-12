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
import persistence.PosteDeCharge;
import persistence.PosteDeChargePK;

/**
 *
 * @author kimo
 */
@Stateless
public class ManagePosteDeChargeImpl implements ManagePosteDeCharge{

    
    @PersistenceContext(unitName = "gpao_pu")
    private EntityManager entityManager;
    
    @Override
    public void create(PosteDeCharge posteDeCharge) {
        if (entityManager != null) {
            entityManager.persist(posteDeCharge);
        }
    }

    @Override
    public List<PosteDeCharge> findAll() {
        if (entityManager != null) {
            return entityManager.createNamedQuery("PosteDeCharge.findAll").getResultList();
        }
        return null;
    }

    @Override
    public PosteDeCharge find(PosteDeChargePK posteDeChargePK) {
        if (entityManager != null) {
            return (PosteDeCharge) entityManager.find(PosteDeCharge.class, posteDeChargePK);
        }
        return null;
    }

    @Override
    public void edit(PosteDeCharge posteDeCharge) {
        if (entityManager != null) {
            PosteDeCharge posteDeChargeExistant = entityManager.find(PosteDeCharge.class, posteDeCharge.getPosteDeChargePK());
            if (posteDeChargeExistant != null) {
                entityManager.merge(posteDeCharge);
            }
        }
    }

    @Override
    public void remove(PosteDeCharge posteDeCharge) {
        if (entityManager != null) {
            posteDeCharge = entityManager.find(PosteDeCharge.class, posteDeCharge.getPosteDeChargePK());
            if (posteDeCharge != null) {
                entityManager.remove(posteDeCharge);
            }
        }
    }

    @Override
    public List<PosteDeCharge> findRange(int[] range) {
        if (entityManager != null) {
            javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PosteDeCharge.class));
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
            javax.persistence.criteria.Root<PosteDeCharge> rt = cq.from(PosteDeCharge.class);
            cq.select(entityManager.getCriteriaBuilder().count(rt));
            javax.persistence.Query q = entityManager.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }
        return 0;
    }
    
}
