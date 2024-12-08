package com.ps;

import model.Dealership;
import model.LeaseContract;
import model.SalesContract;
import model.Vehicle;

import java.util.List;
import java.util.Scanner;


public class UserInterface {

    private static Dealership dealership;
    private static Scanner scanner = new Scanner(System.in);
    private static Scanner inputScanner = new Scanner(System.in);
    private static ContractFileManager contractFileManager;


    public static void init() {
        dealership = DealershipFileManager.getDealership();
        contractFileManager = new ContractFileManager();
    }

    public static void display() {
        init();

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
        DealershipFileManager.saveDealership(dealership);
    }

    private static void displayAllVehicles() {

        List<Vehicle> vehicles = dealership.getAllVehicles();
        for (Vehicle vehicle : vehicles) {
            System.out.println(vehicle);
        }

    }

    public static void searchByPrice() {
        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();

        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        List<Vehicle> vehicles = dealership.getVehiclesByPrice(minPrice, maxPrice);

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

        List<Vehicle> vehicles = dealership.getVehiclesByMakeModel(make, model);

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
        System.out.print("Enter year: ");
        int year = scanner.nextInt();
        scanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByYear(year);

        if (vehicles.isEmpty()) {
            System.out.println("No vehicles found for year: " + year);
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

        List<Vehicle> vehicles = dealership.getVehiclesByColor(col);
        System.out.println(vehicles.isEmpty() ? "No vehicles found for color: " + col : "Found vehicles for color " + col + ":");
        vehicles.forEach(System.out::println);
    }

    public static void searchByMileage(Scanner inputScanner) {
        System.out.print("Min Mileage: ");
        int minMileage = inputScanner.nextInt();
        System.out.print("Max Mileage: ");
        int maxMileage = inputScanner.nextInt();
        inputScanner.nextLine();

        List<Vehicle> vehicles = dealership.getVehiclesByMileage(minMileage, maxMileage);

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

        List<Vehicle> vehicles = dealership.getVehiclesByType(type);
        System.out.println(vehicles.isEmpty() ? "No vehicles found for type: " + type : "Found vehicles for type " + type + ":");
        vehicles.forEach(System.out::println);
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


        Vehicle newVehicle = new Vehicle(id, year, make, model, type, color, mileage, price);


        dealership.addVehicle(newVehicle);

        DealershipFileManager.saveDealership(dealership);

        System.out.println("Vehicle added successfully!");
    }

    public static void removeVehicle(Scanner scanner) {
        System.out.print("Enter Vehicle VIN to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine();


        Vehicle vehicleToRemove = null;
        for (Vehicle vehicle : dealership.getAllVehicles()) {
            if (vehicle.getVin() == vin) {
                vehicleToRemove = vehicle;
                break;
            }
        }

        if (vehicleToRemove != null) {
            dealership.removeVehicle(vehicleToRemove);
            System.out.println("Vehicle removed successfully!");


            DealershipFileManager.saveDealership(dealership);
        } else {
            System.out.println("No vehicle found with VIN: " + vin);
        }
    }

    public static void sellOrLeaseVehicle() {
        System.out.println(" Would you like to sell or lease the Vehicle?");
        System.out.println(" 1. To sell a Vehicle");
        System.out.println(" 2. To lease the Vehicle");
        int choice = inputScanner.nextInt();

        for (Vehicle vehicle : dealership.getAllVehicles()) {
            System.out.println(vehicle);
        }

        System.out.println("Enter Vehicle's VIN");
        int vehicleVin = inputScanner.nextInt();

        Vehicle vehicle = null;
        for (Vehicle vhcle : dealership.getAllVehicles()) {
            if (vehicleVin == vhcle.getVin()) {
                vehicle = vhcle;
            }
        }

        if (vehicle != null) {
            dealership.getAllVehicles().remove(vehicle);
        }


        System.out.println(" What is today's date?");
        String date = inputScanner.next();

        System.out.println(" What is your name?");
        String customerName = inputScanner.next();

        System.out.println("what is your Email");
        String customerEmail = inputScanner.next();

        Contract contract = null;
        if (1 == choice) {
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
            contract = new SalesContract(isFinanced, date, customerName, vehicle, customerEmail);
        } else if (2 == choice) {
            contract = new LeaseContract(date, customerName, vehicle, customerEmail);
        } else {
            throw new RuntimeException("Invalid Choice...");
        }
        contractFileManager.saveContract(contract);
    }
}

