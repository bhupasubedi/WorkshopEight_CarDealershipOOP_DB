DROP DATABASE IF EXISTS cardealership;

CREATE DATABASE IF NOT EXISTS cardealership;

USE cardealership;

CREATE TABLE Dealership (
    dealership_id INTEGER NOT NULL AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    address VARCHAR(255),
    phone VARCHAR(255),
    CONSTRAINT pk_dealership PRIMARY KEY (dealership_id)
);

CREATE TABLE Vehicle (
    vin INTEGER NOT NULL,
    year INTEGER NOT NULL,
    make VARCHAR(255) NOT NULL,
    model VARCHAR(255),
    vehicleType VARCHAR(255),
    color VARCHAR(255),
    odometer INTEGER NOT NULL,
    price DOUBLE(10,2),
    dealership_id INTEGER,
    CONSTRAINT pk_vin PRIMARY KEY (vin),
    FOREIGN KEY (dealership_id) REFERENCES dealership(dealership_id) ON DELETE CASCADE
);

CREATE TABLE SalesContract (
    salescontract_id INTEGER AUTO_INCREMENT,
    date VARCHAR(255),
    customerName VARCHAR(255),
    customerEmail VARCHAR(255),
    vehicleId INTEGER,
    totalPrice DOUBLE(10, 2),
    monthlyPayment DOUBLE(10, 2),
    salesTaxAmount DOUBLE(10, 2),
    recordingFee DOUBLE(10, 2),
    processingFee DOUBLE(10, 2),
    isFinanced BOOLEAN,
    CONSTRAINT pk_salescontract_id PRIMARY KEY (salescontract_id),
    FOREIGN KEY (vehicleId) REFERENCES Vehicle(vin) ON DELETE CASCADE
);


CREATE TABLE LeaseContract (
    leasecontract_id INTEGER AUTO_INCREMENT,
    date VARCHAR(255),
    customerName VARCHAR(255),
    customerEmail VARCHAR(255),
    vehicleId INTEGER,
    totalPrice DOUBLE(10, 2),
    monthlyPayment DOUBLE(10, 2),
    expectedEnding DOUBLE(10, 2),
    leaseFee DOUBLE(10, 2),
    CONSTRAINT pk_leasecontract_id PRIMARY KEY (leasecontract_id),
    FOREIGN KEY (vehicleId) REFERENCES Vehicle(vin) ON DELETE CASCADE
);


-- Insert into Dealership table
INSERT INTO Dealership (name, address, phone) VALUES
('Elite Motors', '123 Main St, Cityville', '555-1234'),
('Super Cars', '456 High St, Townsville', '555-5678'),
('Luxury Wheels', '789 Broadway, Metropolis', '555-8765'),
('Auto World', '321 Oak Ave, Rivertown', '555-4321'),
('Prime Vehicles', '654 Pine Rd, Lakeside', '555-6789'),
('Speedy Motors', '987 Maple Ln, Westwood', '555-2468'),
('Car Kingdom', '258 Elm St, Eastfield', '555-3698'),
('Zoom Autos', '369 Birch Blvd, Greenfield', '555-7531'),
('Drive Line', '135 Cedar St, Mountainview', '555-1456'),
('Quick Cars', '642 Spruce Rd, Hilltop', '555-2147');

-- Insert into Vehicle table
INSERT INTO Vehicle (vin, year, make, model, vehicleType, color, odometer, price, dealership_id) VALUES
(1001, 2024, 'Toyota', 'Camry', 'Sedan', 'Blue', 5000, 25000.00, 1),
(1002, 2023, 'Honda', 'Civic', 'Sedan', 'Red', 2000, 22000.00, 2),
(1003, 2025, 'Ford', 'F-150', 'Truck', 'Black', 3000, 35000.00, 3),
(1004, 2024, 'Chevrolet', 'Silverado', 'Truck', 'Silver', 7000, 38000.00, 4),
(1005, 2022, 'Tesla', 'Model 3', 'Electric', 'White', 1500, 45000.00, 5),
(1006, 2023, 'BMW', 'X5', 'SUV', 'Gray', 4000, 60000.00, 6),
(1007, 2023, 'Audi', 'A6', 'Sedan', 'Blue', 1000, 55000.00, 7),
(1008, 2024, 'Mercedes', 'C-Class', 'Sedan', 'Black', 3500, 52000.00, 8),
(1009, 2025, 'Nissan', 'Altima', 'Sedan', 'Red', 2200, 24000.00, 9),
(1010, 2023, 'Hyundai', 'Tucson', 'SUV', 'White', 5000, 27000.00, 10);

-- Insert into SalesContract table
INSERT INTO SalesContract (date, customerName, customerEmail, vehicleId, totalPrice, monthlyPayment, salesTaxAmount, recordingFee, processingFee, isFinanced) VALUES
('2024-12-01', 'John Doe', 'johndoe@example.com', 1001, 25000.00, 450.00, 1500.00, 200.00, 300.00, TRUE),
('2024-12-02', 'Jane Smith', 'janesmith@example.com', 1002, 22000.00, 400.00, 1200.00, 250.00, 350.00, FALSE),
('2024-12-03', 'Chris Johnson', 'chrisj@example.com', 1003, 35000.00, 600.00, 2100.00, 300.00, 400.00, TRUE),
('2024-12-04', 'Patricia Lee', 'patricialee@example.com', 1004, 38000.00, 650.00, 2500.00, 350.00, 450.00, TRUE),
('2024-12-05', 'James White', 'jameswhite@example.com', 1005, 45000.00, 750.00, 2700.00, 400.00, 500.00, FALSE),
('2024-12-06', 'Michael Brown', 'michaelbrown@example.com', 1006, 60000.00, 900.00, 3600.00, 500.00, 600.00, TRUE),
('2024-12-07', 'Emily Davis', 'emilydavis@example.com', 1007, 55000.00, 850.00, 3300.00, 450.00, 550.00, TRUE),
('2024-12-08', 'David Martinez', 'davidm@example.com', 1008, 52000.00, 800.00, 3000.00, 400.00, 500.00, FALSE),
('2024-12-09', 'Sarah Wilson', 'sarahwilson@example.com', 1009, 24000.00, 400.00, 1500.00, 250.00, 350.00, FALSE),
('2024-12-10', 'Thomas Clark', 'thomasclark@example.com', 1010, 27000.00, 450.00, 1600.00, 200.00, 300.00, TRUE);

-- Insert into LeaseContract table
INSERT INTO LeaseContract (date, customerName, customerEmail, vehicleId, totalPrice, monthlyPayment, expectedEnding, leaseFee) VALUES
('2024-12-01', 'Carlos Green', 'carlosgreen@example.com', 1001, 25000.00, 450.00, 15000.00, 500.00),
('2024-12-02', 'Olivia Scott', 'oliviascott@example.com', 1002, 22000.00, 400.00, 13000.00, 550.00),
('2024-12-03', 'Sophia Adams', 'sophiaadams@example.com', 1003, 35000.00, 600.00, 21000.00, 600.00),
('2024-12-04', 'Benjamin Harris', 'benjaminharris@example.com', 1004, 38000.00, 650.00, 23000.00, 650.00),
('2024-12-05', 'Isabella Thompson', 'isabellathompson@example.com', 1005, 45000.00, 750.00, 27000.00, 700.00),
('2024-12-06', 'Lucas Jackson', 'lucasjackson@example.com', 1006, 60000.00, 900.00, 32000.00, 750.00),
('2024-12-07', 'Amelia White', 'ameliawhite@example.com', 1007, 55000.00, 850.00, 31000.00, 700.00),
('2024-12-08', 'Ethan Young', 'ethanyoung@example.com', 1008, 52000.00, 800.00, 30000.00, 800.00),
('2024-12-09', 'Madison King', 'madisonking@example.com', 1009, 24000.00, 400.00, 14000.00, 450.00),
('2024-12-10', 'Jacob Lee', 'jacoblee@example.com', 1010, 27000.00, 450.00, 16000.00, 500.00);






