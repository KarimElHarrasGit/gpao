/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import persistence.Article;
import javax.ejb.Local;

/**
 *
 * @author keharras
 */
@Local
public interface ManageArticle {

    public void create(Article article);

    public List<Article> findAll();

    public Article find(String reference);

    public void edit(Article article);

    public void remove(Article article);

    public List<Article> findRange(int[] range);

    public int count();

}
