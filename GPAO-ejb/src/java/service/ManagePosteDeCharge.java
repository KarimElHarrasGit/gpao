/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Local;
import persistence.PosteDeCharge;
import persistence.PosteDeChargePK;

/**
 *
 * @author kimo
 */
@Local
public interface ManagePosteDeCharge {
    public void create(PosteDeCharge posteDeCharge);

    public List<PosteDeCharge> findAll();

    public PosteDeCharge find(PosteDeChargePK posteDeChargePK);

    public void edit(PosteDeCharge posteDeCharge);

    public void remove(PosteDeCharge posteDeCharge);

    public List<PosteDeCharge> findRange(int[] range);

    public int count();
}
