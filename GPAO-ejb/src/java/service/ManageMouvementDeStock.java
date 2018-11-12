/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Local;
import persistence.MouvementDeStock;
import persistence.MouvementDeStockPK;

/**
 *
 * @author kimo
 */
@Local
public interface ManageMouvementDeStock {
    public void create(MouvementDeStock mouvementDeStock);

    public List<MouvementDeStock> findAll();

    public MouvementDeStock find(MouvementDeStockPK mouvementDeStockPK);

    public void edit(MouvementDeStock mouvementDeStock);

    public void remove(MouvementDeStock mouvementDeStock);

    public List<MouvementDeStock> findRange(int[] range);

    public int count();
}
