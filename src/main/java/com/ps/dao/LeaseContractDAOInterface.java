package com.ps.dao;

import com.ps.model.LeaseContract;
import com.ps.model.Vehicle;

import java.util.List;

public interface LeaseContractDAOInterface {
    public LeaseContract getLeaseContractByLeaseContractId(int leaseContractId);
    public List<LeaseContract> getAllLeaseContract();
    public void createLeaseContract(LeaseContract leaseContract);
    public void updateLeaseContract(int leaseContractId, LeaseContract leaseContract);
    public void deleteLeaseContract(int leaseContractId);
}
