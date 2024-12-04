package com.ps;


public class SalesContract extends Contract {

    private double salesTaxAmount;
    private double recordingFee;
    private double processingFee;
    private boolean isFinanced;


    public SalesContract(boolean isFinanced, String date, String customerName, Vehicle vehicleSold, String customerEmail) {
        super( date,  customerName, vehicleSold,  customerEmail);
        this.salesTaxAmount = .05;
        this.recordingFee = 100;
        this.isFinanced = isFinanced;
        if (vehicleSold.getPrice() <= 10000) {
            this.processingFee = 295;
        } else {
            this.processingFee = 495;
        }

    }

    public double getSalesTaxAmount() {
        return salesTaxAmount;
    }

    public void setSalesTaxAmount(double salesTaxAmount) {
        this.salesTaxAmount = salesTaxAmount;
    }

    public double getRecordingFee() {
        return recordingFee;
    }

    public void setRecordingFee(double recordingFee) {
        this.recordingFee = recordingFee;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean getFinanced() {
        return isFinanced;
    }

    public void setFinanced(boolean financed) {
        isFinanced = financed;
    }

    @Override
    public double getTotalPrice() {
        return (getVehicleSold().getPrice() * salesTaxAmount) + getVehicleSold().getPrice() + recordingFee + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        double totalPrice = getTotalPrice();
        double monthlyRate = 0;
        double monthlyPayment = 0;
        if (totalPrice >= 10000) {
            monthlyRate = 4.25/12/100;
            monthlyPayment = totalPrice*(monthlyRate/(1-(Math.pow(1+monthlyRate, -48))));

        } else {
            monthlyRate = 5.25/12/100;
            monthlyPayment = totalPrice*(monthlyRate/(1-(Math.pow(1+monthlyRate, -24))));

        }
        return monthlyPayment;
    }

    @Override
    public String toString() {
        return "SALE|" +
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
                salesTaxAmount + "|" +
                recordingFee + "|" +
                processingFee + "|" +
                isFinanced + "|" +
                getMonthlyPayment() + "|" +
                getTotalPrice();
    }
}

