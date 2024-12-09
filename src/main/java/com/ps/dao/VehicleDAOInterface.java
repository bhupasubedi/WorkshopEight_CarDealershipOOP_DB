package com.ps.dao;

import com.ps.model.Vehicle;
import java.util.List;

public interface VehicleDAOInterface {
    public Vehicle getVehicleByVin(int vin);
    public List<Vehicle> getAllVehicle();
    public void createVehicle(Vehicle vehicle);
    public void updateVehicle(int vin, Vehicle vehicle);
    public void deleteVehicle(int vin);
    public List<Vehicle> getVehicleByPriceRange(double low, double high);
    public List<Vehicle> getVehicleByMakeAndModel(String make, String model);
    public List<Vehicle> getVehicleByYearRange(int minimumYear, int maximumYear);
    public List<Vehicle> getVehicleByMileageRange(int minMileage, int maxMileage);
    public List<Vehicle> getVehicleByColor(String color);
    public List<Vehicle> getVehicleByType(String vehicleType);
}
