/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Local;
import persistence.Remplacement;
import persistence.RemplacementPK;

/**
 *
 * @author kimo
 */
@Local
public interface ManageRemplacement {
    public void create(Remplacement remplacement);

    public List<Remplacement> findAll();

    public Remplacement find(RemplacementPK remplacementPK);

    public void edit(Remplacement remplacement);

    public void remove(Remplacement remplacement);

    public List<Remplacement> findRange(int[] range);

    public int count();
}
