package service;

import model.App;
import model.Order;
import model.Product;
import model.Restaurant;
import model.accounts.Client;


import java.util.Scanner;

public class ClientService {

    private final AuditService audit = AuditService.getInstance();
    private final Scanner scanner = new Scanner(System.in);

    public void Main(App app, Client client) {

        audit.write("Login - "+client.getFullname());
        System.out.println("\nLogged in as client");
        System.out.println(client);
        for (; ; ) {
            System.out.println("Select an option");
            System.out.println("1. Create order");
            System.out.println("2. Display current orders");
            System.out.println("3. Display all orders");
            System.out.println("4. Account settings");
            System.out.println("0. Exit");

            int option = scanner.nextInt();
            if (option == 0) {
                System.out.println("Exiting...");
                break;
            }
            switch (option) {
                case 1:

                    createOrder(app, client);
                    break;
                case 2:
                    displayOrders(app, client, true);
                    break;
                case 3:
                    displayOrders(app, client, false);
                    break;
                case 4:
                    accountSettings(app, client);
                    break;
                default:
                    break;
            }
        }
    }

    public void createOrder(App app, Client client) {
        Scanner scanner = new Scanner(System.in);
        Order order = new Order(client);
        System.out.println("Order created");
        for (; ; ) {
            System.out.println("Select an option");
            System.out.println("1. Select restaurant");
            System.out.println("2. View restaurants");
            System.out.println("3. Finish order");
            System.out.println("0. Cancel Order");
            int option = Integer.parseInt(scanner.nextLine());
            if (option == 0) {
                return;
            }
            if (option == 3) {
                break;
            }
            if (option == 2) {
                System.out.println("Available restaurants");
                for (Restaurant res : app.getRestaurants()) {
                    System.out.println(res.getName());
                }

            } else if (option == 1) {
                System.out.println("Enter restaurant name");
                String sc = scanner.nextLine();
                Restaurant restaurant = null;
                for (Restaurant res : app.getRestaurants()) {
                    if (res.getName().toLowerCase().equals(sc.toLowerCase())) {
                        restaurant = res;
                    }
                }
                if (restaurant == null) {
                    System.out.println("Restaurant doesn't exist");
                } else {
                    System.out.println("Restaurant " + restaurant.getName() + " selected");
                    for (; ; ) {
                        System.out.println("Select option");
                        System.out.println("1. View menu");
                        System.out.println("2. Add product to your order");
                        System.out.println("3. Back");
                        System.out.println("0. Cancel order");
                        int addOption = Integer.parseInt(scanner.nextLine());
                        if (addOption == 0) {
                            return;
                        } else if (addOption == 1) {
                            System.out.println(restaurant.getMenu());
                        } else if (addOption == 3) {
                            break;
                        } else if (addOption != 2) {
                            System.out.println("Invalid option");
                        } else {
                            System.out.println("Enter product name");
                            String name = scanner.nextLine();
                            Product product = null;
                            for (Product p : restaurant.getMenu()) {
                                if (p.getName().toLowerCase().equals(name.toLowerCase())){
                                    product = p;
                                }
                            }
                            if (product == null) {
                                System.out.println("This product doesn't exist");
                            } else {
                                System.out.println("Enter quantity");
                                int quantity = Integer.parseInt(scanner.nextLine());
                                order.addProduct(product, quantity);
                            }
                        }
                    }
                }
            }
        }
        audit.write("Create order - "+client.getFullname());
        client.addOrder(order);
        app.addOrder(order);
    }

    public void displayOrders(App app, Client client, Boolean active) {
        audit.write("Display Orders- "+client.getFullname());
        int count = 0;
        System.out.println(client.getOrders());
        for (Order order : client.getOrders()) {
            if (!active || order.getStatus().equals("created")) {
                //System.out.println(order);
                count++;
            }
        }
        if (count == 0) {
            if(active)
                System.out.println("No active orders");
            else
                System.out.println("No orders");
        }
    }

    public void accountSettings(App app, Client client){
        Scanner scanner = new Scanner(System.in);
        for(;;){
            System.out.println("Select option");
            System.out.println("1. Show info");
            System.out.println("0. Back");
            int option = scanner.nextInt();
            switch (option){
                case 0:
                    return;
                case 1:
                    System.out.println(client);
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }
    }
}

