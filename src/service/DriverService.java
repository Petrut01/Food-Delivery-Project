package service;

import model.App;
import model.accounts.Admin;
import model.accounts.Driver;

import java.util.ArrayList;
import java.util.Scanner;

public class DriverService {
    public void Main(App app, Driver driver) {
        Scanner scanner = new Scanner(System.in);
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
                System.out.println("Your Expected salary is "+ driver.getSalary());
            } else if (option == 2) {
                app.getDrivers().remove(driver);
                app.getUsers().remove(driver);
            } else {
                System.out.println("Invalid option");
            }
        }
    }
}
