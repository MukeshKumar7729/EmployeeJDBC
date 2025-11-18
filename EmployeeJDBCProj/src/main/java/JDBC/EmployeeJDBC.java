package JDBC;

import java.sql.*;
import java.util.Scanner;

public class EmployeeJDBC {

    // 🔹 Update JDBC URL to match your database name
    static final String URL = "jdbc:mysql://localhost:3306/Insys";
    static final String USER = "root";
    static final String PASS = "Mkks77129"; // Replace with your MySQL password

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to database
            try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {
                System.out.println("✅ Connected to Insys database.");
                runMenu(con, sc);
            }
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }

    // 🔹 Menu loop
    private static void runMenu(Connection con, Scanner sc) throws SQLException {
        while (true) {
            System.out.println("\n📋 MENU:");
            System.out.println("1. Insert Employee");
            System.out.println("2. View All Employees");
            System.out.println("3. Update Employee Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    insertEmployee(con, sc);
                    break;
                case 2:
                    viewEmployees(con);
                    break;
                case 3:
                    updateEmployee(con, sc);
                    break;
                case 4:
                    deleteEmployee(con, sc);
                    break;
                case 5:
                    System.out.println(" Connection closed. Exiting...");
                    return; // exit the method
                default:
                    System.out.println("❌ Invalid choice. Try again.");
                    break;
            }
        }
    }




    // 🔹 Insert new employee
    private static void insertEmployee(Connection con, Scanner sc) throws SQLException {
        sc.nextLine(); // consume newline
        System.out.print("Enter name: ");
        String name = sc.nextLine();

        System.out.print("Enter address: ");
        String address = sc.nextLine();

        System.out.print("Enter salary: ");
        int salary = sc.nextInt();

        String query = "INSERT INTO Employee (name, address, salary) VALUES (?, ?, ?)";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, name);
            pst.setString(2, address);
            pst.setInt(3, salary);
            int rows = pst.executeUpdate();
            System.out.println("✅ " + rows + " employee(s) inserted.");
        }
    }

    // 🔹 View all employees
    private static void viewEmployees(Connection con) throws SQLException {
        String query = "SELECT * FROM Employee";
        try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(query)) {
            System.out.println("\n📊 Employee Table:");
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("eid") +
                        ", Name: " + rs.getString("name") +
                        ", Address: " + rs.getString("address") +
                        ", Salary: " + rs.getInt("salary"));
            }
        }
    }

    // 🔹 Update employee salary
    private static void updateEmployee(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to update: ");
        int eid = sc.nextInt();

        System.out.print("Enter new salary: ");
        int salary = sc.nextInt();

        String query = "UPDATE Employee SET salary = ? WHERE eid = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, salary);
            pst.setInt(2, eid);
            int rows = pst.executeUpdate();
            System.out.println("✏️ " + rows + " employee(s) updated.");
        }
    }

    // 🔹 Delete an employee
    private static void deleteEmployee(Connection con, Scanner sc) throws SQLException {
        System.out.print("Enter Employee ID to delete: ");
        int eid = sc.nextInt();

        String query = "DELETE FROM Employee WHERE eid = ?";
        try (PreparedStatement pst = con.prepareStatement(query)) {
            pst.setInt(1, eid);
            int rows = pst.executeUpdate();
            System.out.println("🗑️ " + rows + " employee(s) deleted.");
        }
    }
}
