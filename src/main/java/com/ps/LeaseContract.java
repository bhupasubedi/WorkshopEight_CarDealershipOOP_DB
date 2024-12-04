package com.ps;

public class LeaseContract extends Contract{

    private double expectedEnding;
    private double leaseFee;

    public LeaseContract(String date, String customerName, Vehicle vehicleSold, String customerEmail) {
        super( date,  customerName, vehicleSold, customerEmail);
        this.expectedEnding = vehicleSold.getPrice() * .5;
        this.leaseFee = vehicleSold.getPrice() * .07;
    }

    public double getLeaseFee() {
        return leaseFee;
    }

    public void setLeaseFee(double leaseFee) {
        this.leaseFee = leaseFee;
    }

    public double getExpectedEnding() {
        return expectedEnding;
    }

    public void setExpectedEnding(double expectedEnding) {
        this.expectedEnding = expectedEnding;
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        double totalPrice = getTotalPrice();
        double monthlyRate = 4.0/12/100;
        double monthlyPayment = totalPrice*(monthlyRate/(1-(Math.pow(1+monthlyRate, -36))));
        return monthlyPayment;
    }

    @Override
    public String toString() {
        return "LEASE|" +
                getDate() + "|" +
                getCustomerName() + "|" +
                getCustomerEmail() + "|" +
                getVehicleSold().getVin() + "|" +
                getVehicleSold().getYear() + "|" +
                getVehicleSold().getMake() + "|" +
                getVehicleSold().getModel() + "|" +
                getVehicleSold().getVehicleType() + "|" +
                getVehicleSold().getColor() + "|" +
                getVehicleSold().getOdometer() + "|" +
                getVehicleSold().getPrice() + "|" +
                expectedEnding + "|" +
                leaseFee + "|" +
                getMonthlyPayment() + "|" +
                getTotalPrice();
    }
}

