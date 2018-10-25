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
import persistence.LienDeNomenclature;
import persistence.LienDeNomenclaturePK;

/**
 *
 * @author kimo
 */
@Stateless
public class ManageLienDeNomenclatureImpl implements ManageLienDeNomenclature{

    
    @PersistenceContext(unitName = "gpao_pu")
    private EntityManager entityManager;
    
    @Override
    public void create(LienDeNomenclature lienDeNomenclature) {
        if (entityManager != null) {
            entityManager.persist(lienDeNomenclature);
        }
    }

    @Override
    public List<LienDeNomenclature> findAll() {
        if (entityManager != null) {
            return entityManager.createNamedQuery("LienDeNomenclature.findAll").getResultList();
        }
        return null;
    }

    @Override
    public LienDeNomenclature find(LienDeNomenclaturePK lienDeNomenclaturePK) {
        if (entityManager != null) {
            return (LienDeNomenclature) entityManager.find(LienDeNomenclature.class, lienDeNomenclaturePK);
        }
        return null;
    }

    @Override
    public void edit(LienDeNomenclature lienDeNomenclature) {
        if (entityManager != null) {
            LienDeNomenclature lienDeNomenclatureExistant = entityManager.find(LienDeNomenclature.class, lienDeNomenclature.getLienDeNomenclaturePK());
            if (lienDeNomenclatureExistant != null) {
                entityManager.merge(lienDeNomenclature);
            }
        }
    }

    @Override
    public void remove(LienDeNomenclature lienDeNomenclature) {
        if (entityManager != null) {
            lienDeNomenclature = entityManager.find(LienDeNomenclature.class, lienDeNomenclature.getLienDeNomenclaturePK());
            if (lienDeNomenclature != null) {
                entityManager.remove(lienDeNomenclature);
            }
        }
    }

    @Override
    public List<LienDeNomenclature> findRange(int[] range) {
        if (entityManager != null) {
            javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(LienDeNomenclature.class));
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
            javax.persistence.criteria.Root<LienDeNomenclature> rt = cq.from(LienDeNomenclature.class);
            cq.select(entityManager.getCriteriaBuilder().count(rt));
            javax.persistence.Query q = entityManager.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }
        return 0;
    }
    
}
