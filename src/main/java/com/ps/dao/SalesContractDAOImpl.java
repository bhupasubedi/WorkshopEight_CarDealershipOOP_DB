package com.ps.dao;

import com.ps.model.SalesContract;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SalesContractDAOImpl implements SalesContractDAOInterface {


    private DataSource dataSource;

    public SalesContractDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public SalesContract getSalesContractBySalesContractId(int salesContractId) {
        String query = "SELECT * FROM SalesContract WHERE salescontract_id = ?;";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, salesContractId);
            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ){
                if(resultSet.next()){
                    return mapSalesContract(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<SalesContract> getAllSalesContract() {
        List<SalesContract> salesContracts = new ArrayList<>();
        String query = "SELECT * FROM SalesContract;";
        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                SalesContract salesContract = mapSalesContract(resultSet);
                salesContracts.add(salesContract);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return salesContracts;
    }

    @Override
    public void createSalesContract(SalesContract salesContract) {
        String query = "INSERT INTO SalesContract (`salesTaxAmount`, `date`, `customerName`, `customerEmail`, `vehicleId`, `totalPrice`, `monthlyPayment`, `recordingFee`, `processingFee`, `isFinanced`) VALUES(?,?,?,?,?,?,?,?,?)";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setDouble(1, salesContract.getSalesTaxAmount());
            preparedStatement.setString(2, salesContract.getDate());
            preparedStatement.setString(3, salesContract.getCustomerName());
            preparedStatement.setString(4, salesContract.getCustomerEmail());
            preparedStatement.setInt(5, salesContract.getVehicleId());
            preparedStatement.setDouble(6, salesContract.getTotalPrice());
            preparedStatement.setDouble(7, salesContract.getMonthlyPayment());
            preparedStatement.setDouble(8, salesContract.getRecordingFee());
            preparedStatement.setDouble(9, salesContract.getProcessingFee());
            preparedStatement.setBoolean(10, salesContract.isFinanced());
            int effectedRows = preparedStatement.executeUpdate();
            if(effectedRows < 1){
                System.out.println("Error: SalesContract not added");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateSalesContract(int salesContractId, SalesContract salesContract) {
        String query = "UPDATE SalesContract SET salesTaxAmount=?, date=?, customerName=?, customerEmail=?, vehicleId=?, totalPrice=?, monthlyPayment=?, recordingFee=?, processingFee=?, isFinanced=? WHERE salescontract_id=?";

        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){

            preparedStatement.setDouble(1, salesContract.getSalesTaxAmount());
            preparedStatement.setString(2, salesContract.getDate());
            preparedStatement.setString(3, salesContract.getCustomerName());
            preparedStatement.setString(4, salesContract.getCustomerEmail());
            preparedStatement.setInt(5, salesContract.getVehicleId());
            preparedStatement.setDouble(6, salesContract.getTotalPrice());
            preparedStatement.setDouble(7, salesContract.getMonthlyPayment());
            preparedStatement.setDouble(8, salesContract.getRecordingFee());
            preparedStatement.setDouble(9, salesContract.getProcessingFee());
            preparedStatement.setBoolean(10, salesContract.isFinanced());
            preparedStatement.setInt(11, salesContractId);


            int rowsEffected = preparedStatement.executeUpdate();

            if(rowsEffected < 1){
                System.out.println("Error: SalesContract not updated");
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteSalesContract(int salesContractId) {
        String query = "DELETE FROM SalesContract WHERE salescontract_id = ?";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, salesContractId);
            int effectedRows = preparedStatement.executeUpdate();
            if(effectedRows < 1){
                System.out.println("Error: SalesContract not deleted");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    private SalesContract mapSalesContract(ResultSet resultSet) throws SQLException {
        double salesTaxAmount = resultSet.getDouble("salesTaxAmount");
        String date = resultSet.getString("date");
        String customerName = resultSet.getString("customerName");
        String customerEmail = resultSet.getString("customerEmail");
        int vehicleId = resultSet.getInt("vehicleId");
        double totalPrice = resultSet.getDouble("totalPrice");
        double monthlyPayment = resultSet.getDouble("monthlyPayment");
        double recordingFee = resultSet.getDouble("recordingFee");
        double processingFee = resultSet.getDouble("processingFee");
        boolean isFinanced = resultSet.getBoolean("isFinanced");
        return new SalesContract(salesTaxAmount, date, customerName, customerEmail, vehicleId, totalPrice, monthlyPayment, recordingFee, processingFee, isFinanced);

    }
}
