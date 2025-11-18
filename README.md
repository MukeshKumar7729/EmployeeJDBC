Employee JDBC Management Project
Project Overview

This is a simple Java JDBC console application that performs CRUD operations (Create, Read, Update, Delete) on an Employee database using MySQL.
The project is structured as a Maven project for easy dependency management and compilation.

Features

Insert a new employee with name, address, and salary.

View all employees in the database.

Update an employee's salary by ID.

Delete an employee by ID.

Console-based menu-driven interface.

Technology Stack

Java (Java 8+ recommended)




MySQL database

JDBC (Java Database Connectivity)

Maven for project and dependency management

Project Structure
EmployeeJDBCProject/
│
├─ pom.xml                  # Maven configuration file
└─ src/
   ├─ main/
   │   ├─ java/
   │   │   └─ JDBC/
   │   │       └─ EmployeeJDBC.java  # Main Java program
   │   └─ resources/       # Optional: config files
   └─ test/
       └─ java/            # Optional: unit tests


Database Setup

Open MySQL and run:

CREATE DATABASE IF NOT EXISTS Insys;

USE Insys;

CREATE TABLE IF NOT EXISTS Employee (
    eid INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    address VARCHAR(100),
    salary INT
);


Update the database URL, username, and password in EmployeeJDBC.java:

static final String URL = "jdbc:mysql://localhost:3306/Insys";
static final String USER = "root";
static final String PASS = "your_mysql_password";




Setup & Running

Clone or download this project.

Open a terminal and navigate to the project folder:

cd EmployeeJDBCProject






Compile and run using Maven:

mvn clean compile exec:java -Dexec.mainClass="JDBC.EmployeeJDBC"


The console menu will appear. Choose options to perform operations on the Employee database.

Dependencies

MySQL Connector/J: Included in pom.xml

<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.1.0</version>
</dependency>



Notes

Use classic switch-case syntax if using Java < 14.

Make sure MySQL server is running and the database Insys exists.

This is a console application, not a GUI application.
