/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.List;
import javax.ejb.Local;
import persistence.Operation;
import persistence.OperationPK;

/**
 *
 * @author kimo
 */
@Local
public interface ManageOperation {
    public void create(Operation operation);

    public List<Operation> findAll();

    public Operation find(OperationPK operationPK);

    public void edit(Operation operation);

    public void remove(Operation operation);

    public List<Operation> findRange(int[] range);

    public int count();
}
