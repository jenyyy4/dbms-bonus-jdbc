# CRM Analytics JDBC

A Java-based CRM (Customer Relationship Management) analytics tool that connects to a MySQL database and has frequent queries such as:
- Open Opportunities
- Lead Conversions
- Customer Interactions
- Overdue Tasks
- Forecast Accuracy
- Top Performers

---

## Requirements

- Java 8 or above
- MySQL 8.x
- MySQL JDBC Driver (`mysql-connector-java-x.x.xx.jar`)

---

## 🛠Database Setup Instructions

1. **Install MySQL Server** if you haven't already.
   - On Mac: `brew install mysql`
   - On Ubuntu: `sudo apt install mysql-server`

2. **Login to MySQL:**
   ```bash
   mysql -u root -p
3. **Create and Use Database, and create required Tables:**
   ```bash
   CREATE DATABASE crm_db;
   USE crm_db;
   
   -- Leads Table
   CREATE TABLE leads (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    status VARCHAR(50)
   );

   -- Opportunities Table
   CREATE TABLE opportunities (
    id INT AUTO_INCREMENT PRIMARY KEY,
    lead_id INT,
    stage VARCHAR(50),
    forecast_amount DECIMAL(10,2),
    FOREIGN KEY (lead_id) REFERENCES leads(id)
   );

   -- Interactions Table
   CREATE TABLE interactions (
    id INT AUTO_INCREMENT PRIMARY KEY,
    lead_id INT,
    date DATE,
    type VARCHAR(50),
    FOREIGN KEY (lead_id) REFERENCES leads(id)
   );

   -- Tasks Table
   CREATE TABLE tasks (
    id INT AUTO_INCREMENT PRIMARY KEY,
    assigned_to VARCHAR(100),
    due_date DATE,
    completed BOOLEAN
   );

   -- Forecasts Table
   CREATE TABLE forecasts (
    id INT AUTO_INCREMENT PRIMARY KEY,
    expected_amount DECIMAL(10,2),
    actual_amount DECIMAL(10,2)
   );

---

## How to Run

```                                                                      
javac -cp .:mysql-connector-j-9.4.0.jar CRMAnalytics.java
java -cp .:mysql-connector-j-9.4.0.jar CRMAnalytics
