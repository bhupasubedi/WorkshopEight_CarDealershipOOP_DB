package com.ps.dao;

import com.ps.model.LeaseContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LeaseContractDAOImpl implements LeaseContractDAOInterface{

    private DataSource dataSource;

    public LeaseContractDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public LeaseContract getLeaseContractByLeaseContractId(int leaseContractId) {
        String query = "SELECT * FROM LeaseContract WHERE leasecontract_id = ?;";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, leaseContractId);
            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ){
                if(resultSet.next()){
                    return mapLeaseContract(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<LeaseContract> getAllLeaseContract() {
        List<LeaseContract> leaseContracts = new ArrayList<>();
        String query = "SELECT * FROM LeaseContract;";
        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                LeaseContract leaseContract = mapLeaseContract(resultSet);
                leaseContracts.add(leaseContract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return leaseContracts;
    }

    @Override
    public void createLeaseContract(LeaseContract leaseContract) {
        String query = "INSERT INTO LeaseContract (`date`, `customerName`, `customerEmail`, `vehicleId`, `totalPrice`, `monthlyPayment`, `expectedEnding`, `leaseFee`) VALUES(?,?,?,?,?,?,?,?,?)";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, leaseContract.getDate());
            preparedStatement.setString(2, leaseContract.getCustomerName());
            preparedStatement.setString(3, leaseContract.getCustomerEmail());
            preparedStatement.setInt(4, leaseContract.getVehicleId());
            preparedStatement.setDouble(5, leaseContract.getTotalPrice());
            preparedStatement.setDouble(6, leaseContract.getMonthlyPayment());
            preparedStatement.setDouble(7, leaseContract.getExpectedEnding());
            preparedStatement.setDouble(8, leaseContract.getLeaseFee());
            int effectedRows = preparedStatement.executeUpdate();
            if(effectedRows < 1){
                System.out.println("Error: LeaseContract not added");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void updateLeaseContract(int leaseContractId, LeaseContract leaseContract) {
        String query = "UPDATE LeaseContract SET date=?, customerName=?, customerEmail=?, vehicleId=?, totalPrice=?, monthlyPayment=?, expectedEnding=?, leaseFee=? WHERE leasecontract_id=?";

        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setString(1, leaseContract.getDate());
            preparedStatement.setString(2, leaseContract.getCustomerName());
            preparedStatement.setString(3, leaseContract.getCustomerEmail());
            preparedStatement.setInt(4, leaseContract.getVehicleId());
            preparedStatement.setDouble(5, leaseContract.getTotalPrice());
            preparedStatement.setDouble(6, leaseContract.getMonthlyPayment());
            preparedStatement.setDouble(7, leaseContract.getExpectedEnding());
            preparedStatement.setDouble(8, leaseContract.getLeaseFee());
            preparedStatement.setInt(9, leaseContractId);

            int rowsEffected = preparedStatement.executeUpdate();

            if(rowsEffected < 1){
                System.out.println("Error: LeaseContract not updated");
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteLeaseContract(int leaseContractId) {
        String query = "DELETE FROM LeaseContract WHERE leasecontract_id = ?";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, leaseContractId);
            int effectedRows = preparedStatement.executeUpdate();
            if(effectedRows < 1){
                System.out.println("Error: LeaseContract not deleted");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    private LeaseContract mapLeaseContract(ResultSet resultSet) throws SQLException {
        String date = resultSet.getString("date");
        String customerName = resultSet.getString("customerName");
        String customerEmail = resultSet.getString("customerEmail");
        int vehicleId = resultSet.getInt("vehicleId");
        double totalPrice = resultSet.getDouble("totalPrice");
        double monthlyPayment = resultSet.getDouble("monthlyPayment");
        double expectedEnding = resultSet.getDouble("expectedEnding");
        double leaseFee = resultSet.getDouble("leaseFee");
        return new LeaseContract(date, customerName, customerEmail, vehicleId, totalPrice, monthlyPayment, expectedEnding, leaseFee);

    }
}
