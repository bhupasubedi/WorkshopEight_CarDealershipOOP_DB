package com.ps.dao;

import com.ps.model.Dealership;

import java.util.List;

public interface DealershipDAOInterface {
    public Dealership getDealershipByDealershipId(int dealershipId);
    public List<Dealership> getAllDealership();
    public void createDealership(Dealership dealership);
    public void updateDealership(int dealershipId, Dealership dealership);
    public void deleteDealership(int dealershipId);
}
