package com.ps.dao;

import com.ps.model.Dealership;
import com.ps.model.Vehicle;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DealershipDAOImpl implements DealershipDAOInterface{

    private DataSource dataSource;

    public DealershipDAOImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Dealership getDealershipByDealershipId(int dealershipId) {
        String query = "SELECT * FROM Dealership WHERE dealership_id = ?;";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, dealershipId);
            try(
                    ResultSet resultSet = preparedStatement.executeQuery();
            ){
                if(resultSet.next()){
                    return mapDealership(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Dealership> getAllDealership() {
        List<Dealership> dealerships = new ArrayList<>();
        String query = "SELECT * FROM Dealership;";
        try (
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                ResultSet resultSet = preparedStatement.executeQuery();
        ) {
            while (resultSet.next()) {
                Dealership dealership = mapDealership(resultSet);
                dealerships.add(dealership);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return dealerships;
    }

    @Override
    public void createDealership(Dealership dealership) {
        String query = "INSERT INTO Dealership (`dealership_id`, `name`, `address`, `phone`) VALUES(?,?,?,?)";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ) {
            preparedStatement.setString(1, dealership.getName());
            preparedStatement.setString(2, dealership.getAddress());
            preparedStatement.setString(3, dealership.getPhone());
            int effectedRows = preparedStatement.executeUpdate();
            if(effectedRows < 1){
                System.out.println("Error: Dealership not added");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void updateDealership(int dealershipId, Dealership dealership) {
        String query = "UPDATE Dealership SET name=?, address=?, phone=? WHERE dealership_id=?";

        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setString(1, dealership.getName());
            preparedStatement.setString(2, dealership.getAddress());
            preparedStatement.setString(3, dealership.getPhone());
            preparedStatement.setInt(4, dealershipId);

            int rowsEffected = preparedStatement.executeUpdate();

            if(rowsEffected < 1){
                System.out.println("Error: Dealership not updated");
            }

        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDealership(int dealershipId) {
        String query = "DELETE FROM Dealership WHERE dealership_id = ?";
        try(
                Connection connection = this.dataSource.getConnection();
                PreparedStatement preparedStatement = connection.prepareStatement(query);
        ){
            preparedStatement.setInt(1, dealershipId);
            int effectedRows = preparedStatement.executeUpdate();
            if(effectedRows < 1){
                System.out.println("Error: Dealership not deleted");
            }
        } catch(SQLException e){
            e.printStackTrace();
        }

    }

    private Dealership mapDealership(ResultSet resultSet) throws SQLException {
        String name = resultSet.getString("name");
        String address = resultSet.getString("address");
        String phone = resultSet.getString("phone");
        return new Dealership(name, address, phone);

    }
}
