package com.ps.model;


public class LeaseContract {

    private String date;
    private String customerName;
    private String customerEmail;
    private int vehicleId;
    private double totalPrice;
    private double monthlyPayment;
    private double expectedEnding;
    private double leaseFee;

    public LeaseContract() {
    }

    public LeaseContract(String date, String customerName, String customerEmail, int vehicleId, double totalPrice, double monthlyPayment, double expectedEnding, double leaseFee) {
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleId = vehicleId;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
        this.expectedEnding = expectedEnding;
        this.leaseFee = leaseFee;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void setMonthlyPayment(double monthlyPayment) {
        this.monthlyPayment = monthlyPayment;
    }

    public double getExpectedEnding() {
        return expectedEnding;
    }

    public void setExpectedEnding(double expectedEnding) {
        this.expectedEnding = expectedEnding;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    @Override
    public String toString() {
        return "LeaseContract{" +
                ", date='" + date + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", vehicleId=" + vehicleId +
                ", totalPrice=" + totalPrice +
                ", monthlyPayment=" + monthlyPayment +
                ", expectedEnding=" + expectedEnding +
                ", leaseFee=" + leaseFee +
                '}';
    }
}

