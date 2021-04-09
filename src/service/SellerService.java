package service;

import model.App;
import model.Order;
import model.accounts.Driver;
import model.accounts.Seller;

import java.util.Scanner;

public class SellerService {
    public void Main(App app, Seller seller) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nLogged in as Seller");
        System.out.println(seller);
        for (; ; ) {
            System.out.println("Select an option");
            System.out.println("1.View total income");
            System.out.println("2.Delete account");
            System.out.println("0. Exit");
            int option = scanner.nextInt();
            if (option == 0) {
                break;
            } else if (option == 1) {
                double total = 0;
                for (Order s : seller.getSales()) {
                    total += s.getTotalPrice();
                }
                System.out.println("Total income: " + total);
            } else if (option == 2) {
                app.getDrivers().remove(seller);
                app.getUsers().remove(seller);
            } else {
                System.out.println("Invalid option");
            }
        }
    }
}
