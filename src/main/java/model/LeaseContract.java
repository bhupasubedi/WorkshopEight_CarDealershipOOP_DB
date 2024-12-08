package model;


public class LeaseContract {

    private int leasecontract_id;
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

    public LeaseContract(int leasecontract_id, String date, String customerName, String customerEmail, int vehicleSold, double totalPrice, double monthlyPayment, double expectedEnding, double leaseFee) {
        this.leasecontract_id = leasecontract_id;
        this.date = date;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.vehicleId = vehicleSold;
        this.totalPrice = totalPrice;
        this.monthlyPayment = monthlyPayment;
        this.expectedEnding = expectedEnding;
        this.leaseFee = leaseFee;
    }

    public int getLeasecontract_id() {
        return leasecontract_id;
    }

    public void setLeasecontract_id(int leasecontract_id) {
        this.leasecontract_id = leasecontract_id;
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
                "leasecontract_id=" + leasecontract_id +
                ", date='" + date + '\'' +
                ", customerName='" + customerName + '\'' +
                ", customerEmail='" + customerEmail + '\'' +
                ", vehicleSold=" + vehicleId +
                ", totalPrice=" + totalPrice +
                ", monthlyPayment=" + monthlyPayment +
                ", expectedEnding=" + expectedEnding +
                ", leaseFee=" + leaseFee +
                '}';
    }
}

