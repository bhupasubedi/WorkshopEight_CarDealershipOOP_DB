package com.ps.dao;

import com.ps.model.Vehicle;

import java.util.List;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class VehicleDAOImpl implements VehicleDAOInterface {

    private DataSource dataSource;

    public VehicleDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Vehicle getVehicleByVin(int vin) {
        String query = "SELECT * FROM Vehicle WHERE vin = ?;";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, vin);
            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ){
                if(resultSet.next()){
                    return mapVehicle(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehicle> getAllVehicle() {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicle;";
        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Vehicle vehicle = mapVehicle(resultSet);
                vehicles.add(vehicle);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public void createVehicle(Vehicle vehicle) {
        String query = "INSERT INTO Vehicle (`vin`, `year`, `make`, `model`, `vehicleType`, `color`, `odometer`, `price`, `dealership_id`) VALUES(?,?,?,?,?,?,?,?,?)";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setInt(1, vehicle.getVin());
            preparedStatement.setInt(2, vehicle.getYear());
            preparedStatement.setString(3, vehicle.getMake());
            preparedStatement.setString(4, vehicle.getModel());
            preparedStatement.setString(5, vehicle.getVehicleType());
            preparedStatement.setString(6, vehicle.getColor());
            preparedStatement.setInt(7, vehicle.getOdometer());
            preparedStatement.setDouble(8, vehicle.getPrice());
            preparedStatement.setInt(9, vehicle.getDealership_id());
            int effectedRows = preparedStatement.executeUpdate();
            if(effectedRows < 1){
                System.out.println("Error: Vehicle not added");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateVehicle(int vin, Vehicle vehicle) {
        String query = "UPDATE Vehicle SET year=?, make=?, model=?, vehicleType=?, color=?, odometer=?, price=?, dealership_id=? WHERE vin=?";

        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, vehicle.getYear());
            preparedStatement.setString(2, vehicle.getMake());
            preparedStatement.setString(3, vehicle.getModel());
            preparedStatement.setString(4, vehicle.getVehicleType());
            preparedStatement.setString(5, vehicle.getColor());
            preparedStatement.setInt(6, vehicle.getOdometer());
            preparedStatement.setDouble(7, vehicle.getPrice());
            preparedStatement.setInt(8, vehicle.getDealership_id());
            preparedStatement.setInt(9, vin);

            int rowsEffected = preparedStatement.executeUpdate();

            if(rowsEffected < 1){
                System.out.println("Error: Vehicle not updated");
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteVehicle(int vin) {
        String query = "DELETE FROM Vehicle WHERE vin = ?";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, vin);
            int effectedRows = preparedStatement.executeUpdate();
            if(effectedRows < 1){
                System.out.println("Error: Vehicle not deleted");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Vehicle> getVehicleByPriceRange(double low, double high) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicle WHERE price >= ? AND price <= ?;";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setDouble(1, low);
            preparedStatement.setDouble(2, high);

            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ){
                while (resultSet.next()) {
                    Vehicle vehicle = mapVehicle(resultSet);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicleByMakeAndModel(String make, String model) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicle WHERE make = ? and model = ?;";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setString(1, make);
            preparedStatement.setString(2, model);

            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ){
                while (resultSet.next()) {
                    Vehicle vehicle = mapVehicle(resultSet);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;

    }

    @Override
    public List<Vehicle> getVehicleByYearRange(int minimumYear, int maximumYear) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicle WHERE year >= ? AND year <= ?;";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, minimumYear);
            preparedStatement.setInt(2, maximumYear);

            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ){
                while (resultSet.next()) {
                    Vehicle vehicle = mapVehicle(resultSet);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicleByMileageRange(int minMileage, int maxMileage) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicle WHERE odometer >= ? AND odometer <= ?;";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, minMileage);
            preparedStatement.setInt(2, maxMileage);

            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ){
                while (resultSet.next()) {
                    Vehicle vehicle = mapVehicle(resultSet);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicleByColor(String color) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicle WHERE color = ?;";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setString(1, color);
            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ){
                while (resultSet.next()) {
                    Vehicle vehicle = mapVehicle(resultSet);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    @Override
    public List<Vehicle> getVehicleByType(String vehicleType) {
        List<Vehicle> vehicles = new ArrayList<>();
        String query = "SELECT * FROM Vehicle WHERE vehicleType = ?;";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setString(1, vehicleType);
            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ){
                while (resultSet.next()) {
                    Vehicle vehicle = mapVehicle(resultSet);
                    vehicles.add(vehicle);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vehicles;
    }

    private Vehicle mapVehicle(ResultSet resultSet) throws SQLException {
        int vin = resultSet.getInt("vin");
        int year = resultSet.getInt("year");
        String make = resultSet.getString("make");
        String model = resultSet.getString("model");
        String vehicleType = resultSet.getString("vehicleType");
        String color = resultSet.getString("color");
        int odometer = resultSet.getInt("odometer");
        double price = resultSet.getDouble("price");
        int dealershipId = resultSet.getInt("dealership_id");
        return new Vehicle(vin, year, make, model, vehicleType, color, odometer, price, dealershipId);

    }
}
