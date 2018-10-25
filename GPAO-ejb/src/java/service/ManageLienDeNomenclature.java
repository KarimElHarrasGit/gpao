/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Local;
import persistence.LienDeNomenclature;
import persistence.LienDeNomenclaturePK;

/**
 *
 * @author keharras
 */
@Local
public interface ManageLienDeNomenclature {

    public void create(LienDeNomenclature lienDeNomenclature);

    public List<LienDeNomenclature> findAll();

    public LienDeNomenclature find(LienDeNomenclaturePK lienDeNomenclaturePK);

    public void edit(LienDeNomenclature lienDeNomenclature);

    public void remove(LienDeNomenclature lienDeNomenclature);

    public List<LienDeNomenclature> findRange(int[] range);

    public int count();

}
