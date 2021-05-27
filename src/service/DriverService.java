package service;

import model.App;
import model.accounts.Driver;

import java.sql.SQLException;
import java.util.Scanner;

public class DriverService {
    private final AuditService audit = AuditService.getInstance();
    private final DriversDatabase database = DriversDatabase.getDatabaseInstance2();
    private final Scanner scanner = new Scanner(System.in);

    public void Main(App app, Driver driver) throws SQLException {

        audit.write("Login - "+driver.getFullname());
        System.out.println("\nLogged in as Driver");
        System.out.println(driver);
        for (; ; ) {
            System.out.println("Select an option");
            System.out.println("1.View expected salary");
            System.out.println("2.Delete account");
            System.out.println("0. Exit");
            int option = scanner.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) {
                audit.write("Show Salary - " + driver.getFullname());
                System.out.println("Your Expected salary is "+ driver.getSalary());
            } else if (option == 2) {
                audit.write("Delete Account - " + driver.getFullname());
                database.deleteDriver(driver.getID());
                app.getDrivers().remove(driver);
                app.getUsers().remove(driver);
            } else {
                System.out.println("Invalid option");
            }
        }
    }
}
