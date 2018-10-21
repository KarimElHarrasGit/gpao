/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentation;

import persistence.Article;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import service.ManageArticle;

/**
 *
 * @author keharras
 */
@ManagedBean(name = "article_management")
public class ArticleManagement {

    @EJB
    private ManageArticle manageArticle;

    private Article article;

    public String create() {
        if (manageArticle != null) {
            manageArticle.create(article);
            return "/article/All_articles";
        }
        return "";
    }

    public String delete() {
        if (manageArticle != null) {
            String article_reference = javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("article_reference");
            assert (article_reference != null);
            article.setReference(article_reference);
            manageArticle.remove(article);
            return "/article/All_articles";
        }
        return "";
    }

    public java.util.List<Article> findAll() {
        if (manageArticle != null) {
            return manageArticle.findAll();
        }
        return null;
    }

    public String edit() {
        if (manageArticle != null) {
            String article_designation = javax.faces.context.FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("article_designation");
            assert (article_designation != null);
            article.setDesignation(article_designation);
            manageArticle.edit(article);
            return "/article/All_articles";
        }
        return "";

    }

    public ArticleManagement() {
        article = new Article();
    }

}
