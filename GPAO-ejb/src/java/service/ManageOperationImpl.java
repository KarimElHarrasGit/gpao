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
import persistence.Operation;
import persistence.OperationPK;

/**
 *
 * @author keharras
 */
@Stateless
public class ManageOperationImpl implements ManageOperation {

    @PersistenceContext(unitName = "gpao_pu")
    private EntityManager entityManager;

    @Override
    public void create(Operation operation) {
        if (entityManager != null) {
            entityManager.persist(operation);
        }
    }

    @Override
    public List<Operation> findAll() {
        if (entityManager != null) {
            return entityManager.createNamedQuery("Operation.findAll").getResultList();
        }
        return null;
    }

    public Operation find(OperationPK operationPK) {
        if (entityManager != null) {
            return (Operation) entityManager.find(Operation.class, operationPK);
        }
        return null;
    }

    @Override
    public void edit(Operation operation) {
        if (entityManager != null) {
            Operation operationExistant = entityManager.find(Operation.class, operation.getOperationPK());
            if (operationExistant != null) {
                entityManager.merge(operation);
            }
        }
    }

    @Override
    public void remove(Operation operation) {
        if (entityManager != null) {
            operation = entityManager.find(Operation.class, operation.getOperationPK());
            if (operation != null) {
                entityManager.remove(operation);
            }
        }
    }

    @Override
    public List<Operation> findRange(int[] range) {
        if (entityManager != null) {
            javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Operation.class));
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
            javax.persistence.criteria.Root<Operation> rt = cq.from(Operation.class);
            cq.select(entityManager.getCriteriaBuilder().count(rt));
            javax.persistence.Query q = entityManager.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }
        return 0;
    }

}
