package com.ps;

import com.ps.dao.*;
import com.ps.model.LeaseContract;
import com.ps.model.SalesContract;
import com.ps.model.Vehicle;
import org.apache.commons.dbcp2.BasicDataSource;


import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class UserInterface {

    private static List<Vehicle> vehicles = new ArrayList<>();
    private static DealershipDAOInterface dealershipDAO;
    private static LeaseContractDAOInterface leaseContractDAO;
    private static SalesContractDAOInterface salesContractDAO;
    private static VehicleDAOInterface vehicleDAO;
    private static Scanner scanner = new Scanner(System.in);
    private static Scanner inputScanner = new Scanner(System.in);
    private static BasicDataSource basicDataSource = new BasicDataSource();

    public static void init(String username, String password) {
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/cardealership");
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        dealershipDAO = new DealershipDAOImpl(basicDataSource);
        leaseContractDAO = new LeaseContractDAOImpl(basicDataSource);
        salesContractDAO = new SalesContractDAOImpl(basicDataSource);
        vehicleDAO = new VehicleDAOImpl(basicDataSource);
        vehicles = vehicleDAO.getAllVehicle();
    }

    public static void display(String username, String password) {
        init(username, password);

        int choice;

        do {
            System.out.println("\nWelcome to the Dealership Inventory System:");
            System.out.println("1. Display All Vehicles");
            System.out.println("2. Search Vehicles by Price");
            System.out.println("3. Search Vehicles by Make and Model");
            System.out.println("4. Search Vehicles by Year");
            System.out.println("5. Search Vehicles by Color");
            System.out.println("6. Search Vehicles by Mileage");
            System.out.println("7. Search Vehicles by Type");
            System.out.println("8. Add Vehicle");
            System.out.println("9. Remove Vehicle");
            System.out.println("10. Sale or Lease Vehicle");

            System.out.println("99. Exit");

            System.out.print("Please select an option: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayAllVehicles();
                    break;
                case 2:
                    searchByPrice();
                    break;
                case 3:
                    searchByMakeModel(scanner);
                    break;
                case 4:
                    searchByYear(scanner);
                    break;
                case 5:
                    searchByColor(inputScanner);
                    break;
                case 6:
                    searchByMileage(inputScanner);
                    break;
                case 7:
                    searchByType(inputScanner);
                    break;
                case 8:
                    addVehicle(scanner);
                    break;
                case 9:
                    removeVehicle(scanner);
                    break;
                case 10:
                    sellOrLeaseVehicle();
                    break;
                case 99:
                    System.out.println("Exiting the system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 99);

        scanner.close();
    }

    private static void displayAllVehicles() {

        List<Vehicle> vehicles = vehicleDAO.getAllVehicle();
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

    }

    public static void searchByPrice() {
        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();

        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        List<Vehicle> vehicles = vehicleDAO.getVehicleByPriceRange(minPrice, maxPrice);

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found in the price range $" + minPrice + " to $" + maxPrice + ".");
        } else {
            System.out.println("Found vehicles in the price range $" + minPrice + " to $" + maxPrice + ":");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }

    public static void searchByMakeModel(Scanner scanner) {
        System.out.print("Enter make: ");
        String make = scanner.next(); //
        System.out.print("Enter model: ");
        String model = scanner.next();

        List<Vehicle> vehicles = vehicleDAO.getVehicleByMakeAndModel(make, model);

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found for make: " + make + " and model: " + model);
        } else {
            System.out.println("Found vehicles:");
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }

    public static void searchByYear(Scanner scanner) {
        System.out.print("Enter min car year: ");
        int minYear = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter max car year: ");
        int maxYear = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> vehicles = vehicleDAO.getVehicleByYearRange(minYear, maxYear);

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found for year(s): " + minYear + " AND " + maxYear);
        } else {
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }
    }

    public static void searchByColor(Scanner inputScanner) {
        System.out.print("Enter color: ");
        String col = inputScanner.nextLine().trim();

        if (col.isEmpty()) {
            System.out.println("Color input cannot be empty. Please try again.");
            return;
        }

        List<Vehicle> vehicles = vehicleDAO.getVehicleByColor(col);
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found for color: " + col);
        } else {
            System.out.println("Vehicles found for color: " + col);
            for (Vehicle vehicle : vehicles) {
                System.out.println(vehicle);
            }
        }

    }

    public static void searchByMileage(Scanner inputScanner) {
        System.out.print("Min Mileage: ");
        int minMileage = inputScanner.nextInt();
        System.out.print("Max Mileage: ");
        int maxMileage = inputScanner.nextInt();
        inputScanner.nextLine();

        List<Vehicle> vehicles = vehicleDAO.getVehicleByMileageRange(minMileage, maxMileage);

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found with mileage between " + minMileage + " and " + maxMileage + ".");
        } else {
            System.out.println("Found vehicles with mileage between " + minMileage + " and " + maxMileage + ":");
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }

    public static void searchByType(Scanner inputScanner) {
        System.out.print("Enter vehicle type: ");
        String type = inputScanner.nextLine().trim();

        if (type.isEmpty()) {
            System.out.println("Vehicle type input cannot be empty. Please try again.");
            return;
        }

        List<Vehicle> vehicles = vehicleDAO.getVehicleByType(type);
        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found for type: " + type);
        } else {
            System.out.println("Found vehicles for type " + type + ":");
            for (Vehicle v : vehicles) {
                System.out.println(v);
            }
        }
    }

    public static void addVehicle(Scanner scanner) {
        System.out.print("Enter Vehicle ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Make: ");
        String make = scanner.nextLine();

        System.out.print("Enter Model: ");
        String model = scanner.nextLine();

        System.out.print("Enter Type (e.g., sedan, SUV, truck): ");
        String type = scanner.nextLine();

        System.out.print("Enter Color: ");
        String color = scanner.nextLine();

        System.out.print("Enter Mileage: ");
        int mileage = scanner.nextInt();

        System.out.print("Enter Price: ");
        double price = scanner.nextDouble();

        System.out.print("Enter DealerShipId: ");
        int dealerShipId = scanner.nextInt();


        Vehicle newVehicle = new Vehicle(id, year, make, model, type, color, mileage, price, dealerShipId);


        vehicleDAO.createVehicle(newVehicle);

        System.out.println("Vehicle added successfully!");
    }

    public static void removeVehicle(Scanner scanner) {
        System.out.print("Enter Vehicle VIN to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine();

        vehicleDAO.deleteVehicle(vin);
    }

    public static void sellOrLeaseVehicle() {
        System.out.println(" Would you like to sell or lease the Vehicle?");
        System.out.println(" 1. To sell a Vehicle");
        System.out.println(" 2. To lease the Vehicle");
        int choice = inputScanner.nextInt();

        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

        System.out.println("Enter Vehicle's VIN");
        int vehicleVin = inputScanner.nextInt();

        Vehicle vehicle = vehicleDAO.getVehicleByVin(vehicleVin);
        if (vehicle == null) {
            System.out.println("Vehicle not found with vin: " + vehicleVin);
        } else {

            System.out.println(" What is today's date?");
            String date = inputScanner.next();

            System.out.println(" What is your name?");
            String customerName = inputScanner.next();

            System.out.println("what is your Email");
            String customerEmail = inputScanner.next();

            if (1 == choice) {
                SalesContract salesContract = null;
                System.out.println(" Would you like to finance the Vehicle?");
                System.out.println(" 1. Yes");
                System.out.println(" 2. No");
                int financeChoice = inputScanner.nextInt();
                boolean isFinanced;
                if (1 == financeChoice) {
                    isFinanced = true;
                } else if (2 == financeChoice) {
                    isFinanced = false;
                } else {
                    throw new RuntimeException("Invalid Choice...");
                }
                salesContract = new SalesContract(.05, date, customerName,customerEmail, vehicle.getVin(), getSalesContractTotalPrice(vehicle), getSalesContractMonthlyTotalPrice(vehicle), 100, getSalesProcessingFee(vehicle), isFinanced);
                salesContractDAO.createSalesContract(salesContract);
            } else if (2 == choice) {
                LeaseContract leaseContract = new LeaseContract(date, customerName, customerEmail, vehicle.getVin(), getLeaseContractTotalPrice(vehicle), getLeaseContractMonthlyTotalPrice(vehicle), vehicle.getPrice() * .5, vehicle.getPrice() * .07);
                leaseContractDAO.createLeaseContract(leaseContract);
            } else {
                throw new RuntimeException("Invalid Choice...");
            }
        }
    }

    public static double getSalesContractTotalPrice(Vehicle vehicle) {
        return (vehicle.getPrice() * .05) + vehicle.getPrice() + 100 + getSalesProcessingFee(vehicle);
    }

    public static double getSalesProcessingFee(Vehicle vehicle) {
        double processingFee = 0.0;
        if (vehicle.getPrice() <= 10000) {
            processingFee = 295;
        } else {
            processingFee = 495;
        }
        return processingFee;
    }

    public static double getSalesContractMonthlyTotalPrice(Vehicle vehicle) {
        double totalPrice = getSalesContractTotalPrice(vehicle);
        double monthlyRate = 0.0;
        double monthlyPayment = 0.0;
        if (totalPrice >= 10000) {
            monthlyRate = 4.25/12/100;
            monthlyPayment = totalPrice*(monthlyRate/(1-(Math.pow(1+monthlyRate, -48))));

        } else {
            monthlyRate = 5.25/12/100;
            monthlyPayment = totalPrice*(monthlyRate/(1-(Math.pow(1+monthlyRate, -24))));

        }
        return monthlyPayment;
    }

    public static double getLeaseContractTotalPrice(Vehicle vehicle) {
        return vehicle.getPrice() + vehicle.getPrice() * .07;
    }

    public static double getLeaseContractMonthlyTotalPrice(Vehicle vehicle) {
        double totalPrice = getLeaseContractTotalPrice(vehicle);
        double monthlyRate = 4.0/12/100;
        double monthlyPayment = totalPrice*(monthlyRate/(1-(Math.pow(1+monthlyRate, -36))));
        return monthlyPayment;
    }


}

