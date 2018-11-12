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
import persistence.Remplacement;
import persistence.RemplacementPK;

/**
 *
 * @author kimo
 */
@Stateless
public class ManageRemplacementImpl implements ManageRemplacement{
    @PersistenceContext(unitName = "gpao_pu")
    private EntityManager entityManager;
    
    @Override
    public void create(Remplacement remplacement){
        if (entityManager != null) {
            entityManager.persist(remplacement);
        }
    }

    @Override
    public List<Remplacement> findAll(){
        if (entityManager != null) {
            return entityManager.createNamedQuery("Remplacement.findAll").getResultList();
        }
        return null;
    }

    @Override
    public Remplacement find(RemplacementPK remplacementPK) {
        if (entityManager != null) {
            return (Remplacement) entityManager.find(Remplacement.class, remplacementPK);
        }
        return null;
    }

    @Override
    public void edit(Remplacement remplacement) {
        if (entityManager != null) {
            Remplacement remplacementExistant = entityManager.find(Remplacement.class, remplacement.getRemplacementPK());
            if (remplacementExistant != null) {
                entityManager.merge(remplacement);
            }
        }
    }

    @Override
    public void remove(Remplacement remplacement) {
        if (entityManager != null) {
            remplacement = entityManager.find(Remplacement.class, remplacement.getRemplacementPK());
            if (remplacement != null) {
                entityManager.remove(remplacement);
            }
        }
    }

    @Override
    public List<Remplacement> findRange(int[] range) {
        if (entityManager != null) {
            javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Remplacement.class));
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
            javax.persistence.criteria.Root<Remplacement> rt = cq.from(Remplacement.class);
            cq.select(entityManager.getCriteriaBuilder().count(rt));
            javax.persistence.Query q = entityManager.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }
        return 0;
    }
    
}