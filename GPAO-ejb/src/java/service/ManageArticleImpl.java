/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Stateless;
import persistence.Article;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author keharras
 */
@Stateless
public class ManageArticleImpl implements ManageArticle {

    @PersistenceContext(unitName = "gpao_pu")
    private EntityManager entityManager;

    @Override
    public void create(Article article) {
        if (entityManager != null) {
            entityManager.persist(article);
        }
    }

    @Override
    public List<Article> findAll() {
        if (entityManager != null) {
            return entityManager.createNamedQuery("Article.findAll").getResultList();
        }
        return null;
    }

    public Article find(String reference) {
        if (entityManager != null) {
            return (Article) entityManager.createNamedQuery("Article.findByReference").setParameter("reference", reference).getSingleResult();
        }
        return null;
    }

    @Override
    public void edit(Article article) {
        if (entityManager != null) {
            Article articleExistant = entityManager.find(Article.class, article.getReference());
            if (articleExistant != null) {
                entityManager.merge(article);
            }
        }
    }

    @Override
    public void remove(Article article) {
        if (entityManager != null) {
            article = entityManager.find(Article.class, article.getReference());
            if (article != null) {
                entityManager.remove(article);
            }
        }
    }

    @Override
    public List<Article> findRange(int[] range) {
        if (entityManager != null) {
            javax.persistence.criteria.CriteriaQuery cq = entityManager.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Article.class));
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
            javax.persistence.criteria.Root<Article> rt = cq.from(Article.class);
            cq.select(entityManager.getCriteriaBuilder().count(rt));
            javax.persistence.Query q = entityManager.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        }
        return 0;
    }

}
