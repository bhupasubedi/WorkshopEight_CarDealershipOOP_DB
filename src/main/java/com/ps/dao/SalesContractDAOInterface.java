package com.ps.dao;

import com.ps.model.LeaseContract;
import com.ps.model.SalesContract;

import java.util.List;

public interface SalesContractDAOInterface {
    public SalesContract getSalesContractBySalesContractId(int salesContractId);
    public List<SalesContract> getAllSalesContract();
    public void createSalesContract(SalesContract salesContract);
    public void updateSalesContract(int salesContractId, SalesContract salesContract);
    public void deleteSalesContract(int salesContractId);
}
